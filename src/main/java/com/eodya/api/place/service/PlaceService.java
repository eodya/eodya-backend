package com.eodya.api.place.service;

import com.eodya.api.address.domain.AddressDepth1;
import com.eodya.api.address.domain.AddressDepth2;
import com.eodya.api.common.service.S3Service;
import com.eodya.api.place.domain.Place;
import com.eodya.api.place.domain.PlaceTag;
import com.eodya.api.place.domain.Tag;
import com.eodya.api.place.dto.request.PlaceCreateRequest;
import com.eodya.api.place.exception.PlaceException;
import com.eodya.api.place.repository.AddressDepth1Repository;
import com.eodya.api.place.repository.AddressDepth2Repository;
import com.eodya.api.place.repository.PlaceRepository;
import com.eodya.api.place.repository.PlaceTagRepository;
import com.eodya.api.place.repository.TagRepository;
import com.eodya.api.review.domain.Review;
import com.eodya.api.review.domain.ReviewImage;
import com.eodya.api.review.repository.ReviewImageRepository;
import com.eodya.api.review.repository.ReviewRepository;
import com.eodya.api.users.domain.User;
import com.eodya.api.users.repository.UserRepository;

import java.util.List;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.eodya.api.place.dto.response.PlaceRakingResponse;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.multipart.MultipartFile;

import static com.eodya.api.place.exception.PlaceExceptionCode.ALREADY_EXIST_PLACE;
import static com.eodya.api.place.exception.PlaceExceptionCode.INVALID_PLACE_IMAGE_COUNT;


@Service
@RequiredArgsConstructor
public class PlaceService {

    private static final int MAX_PLACE_IMAGE_COUNT = 2;

    private final S3Service s3Service;
    private final UserRepository userRepository;
    private final PlaceRepository placeRepository;
    private final ReviewImageRepository reviewImageRepository;
    private final AddressDepth1Repository addressDepth1Repository;
    private final AddressDepth2Repository addressDepth2Repository;
    private final ReviewRepository reviewRepository;
    private final PlaceTagRepository placeTagRepository;
    private final TagRepository tagRepository;
    private static GeometryFactory geometryFactory = new GeometryFactory();

    @Transactional
    public void createPlaceAndReview(Long userId, PlaceCreateRequest request) {
        validatePlaceImageCount((request.getImages()));
        User user = userRepository.getUserById(userId);

        Tag tag = tagRepository.findByName(request.getTag())
                .orElseGet(() ->
                {
                    Tag newPlaceTag = request.toTagEntity(request.getTag());
                    return tagRepository.save(newPlaceTag);
                });
        Place place;
        Optional<Place> findPlace = placeRepository.findByAddressDetail(request.getAddressDetail());

        if(findPlace.isPresent()){
            Optional<PlaceTag> placeTag = placeTagRepository.findByPlaceAndTag(findPlace.get(), tag);
            if(placeTag.isPresent()) throw new PlaceException(ALREADY_EXIST_PLACE);
            place = findPlace.get();
        }
        else{
            //이미 등록된 장소가 아니면 등록
            Optional<AddressDepth1> depth1 = addressDepth1Repository.findByName(request.getAddressDepth1());

            AddressDepth1 addressDepth1 = depth1.orElseGet(() -> {
                AddressDepth1 newDepth1 = request.toAddressDepth1Entity(request.getAddressDepth1());
                addressDepth1Repository.save(newDepth1);
                return newDepth1;
            });
            Optional<AddressDepth2> findDepth2 = addressDepth1.getDepth2().stream() //성능저하
                    .filter(depth2 -> depth2.getName().equals(request.getAddressDepth2()))
                    .findFirst();

            AddressDepth2 addressDepth2 = findDepth2.orElseGet(() -> {
                AddressDepth2 newDepth2 = request.toAddressDepth2Entity(request.getAddressDepth2(), addressDepth1);
                addressDepth2Repository.save(newDepth2);
                return newDepth2;
            });

            Point point = pointBuild(request.getX(), request.getY());
            place = request.toPlaceEntity(point, user, addressDepth1, addressDepth2);
            placeRepository.save(place);
        }

        placeTagRepository.save(PlaceTag.builder()
                .tag(tag)
                .place(place)
                .build());

        List<String> images = s3Service.uploadFiles(request.getImages());
        place.setImage(images.get(0));

        Review review = request.toReviewEntity(user, place);
        reviewRepository.save(review);
        for (String imageUrl : images) {
            ReviewImage reviewImage = ReviewImage.builder()
                    .imageUrl(imageUrl)
                    .review(review)
                    .build();
            reviewImageRepository.save(reviewImage);
        }
    }


    public Point pointBuild(double x, double y) {
        Coordinate coord = new Coordinate(x, y);
        return geometryFactory.createPoint(coord);
    }

    public List<PlaceRakingResponse> findPlaceRankingByBookmarks() {
        AtomicLong rank = new AtomicLong(1);

        return placeRepository.findAllByOrderByBookmarkCountDesc().stream()
                .map(place -> PlaceRakingResponse.builder()
                        .rank(rank.getAndIncrement())
                        .placeImage(place.getImage())
                        .bookmarkCount((long) place.getBookmarkCount())
                        .addressDetail(place.getAddressDetail())
                        .rank(rank.getAndIncrement())
                        .build())
                .toList();
    }

    public void validatePlaceImageCount(List<MultipartFile> files) {
        if(files.size() > MAX_PLACE_IMAGE_COUNT || files.size()==0) {
            throw new PlaceException(INVALID_PLACE_IMAGE_COUNT);
        }
    }

}
