package com.eodya.api.place.service;

import com.eodya.api.address.domain.AddressDepth1;
import com.eodya.api.address.domain.AddressDepth2;
import com.eodya.api.common.service.S3Service;
import com.eodya.api.place.domain.Place;
import com.eodya.api.place.dto.request.PlaceCreateRequest;
import com.eodya.api.place.repository.AddressDepth1Repository;
import com.eodya.api.place.repository.AddressDepth2Repository;
import com.eodya.api.place.repository.PlaceRepository;
import com.eodya.api.review.domain.Review;
import com.eodya.api.review.domain.ReviewImage;
import com.eodya.api.review.repository.ReviewImageRepository;
import com.eodya.api.review.repository.ReviewRepository;
import com.eodya.api.users.domain.User;
import com.eodya.api.users.repository.UserRepository;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.eodya.api.place.dto.response.PlaceRakingResponse;

import java.util.concurrent.atomic.AtomicLong;

@Service
@RequiredArgsConstructor
public class PlaceService {

    private final S3Service s3Service;
    private final UserRepository userRepository;
    private final PlaceRepository placeRepository;
    private final ReviewImageRepository reviewImageRepository;
    private final AddressDepth1Repository addressDepth1Repository;
    private final AddressDepth2Repository addressDepth2Repository;
    private final ReviewRepository reviewRepository;
    private static GeometryFactory geometryFactory = new GeometryFactory();

    @Transactional
    public void createPlaceAndReview(Long userId, PlaceCreateRequest request) {
        User user = userRepository.getUserById(userId);

        AddressDepth1 addressDepth1 = request.toAddressDepth1Entity(request.getAddressDepth1());
        addressDepth1Repository.save(addressDepth1);

        AddressDepth2 addressDepth2 = request.toAddressDepth2Entity(request.getAddressDepth2(), addressDepth1);
        addressDepth2Repository.save(addressDepth2);

        List<String> images = s3Service.uploadFiles(request.getImages());

        Point point = pointBuild(request.getX(), request.getY());
        Place place = request.toPlaceEntity(point, images.get(0), user, addressDepth1, addressDepth2);
        placeRepository.save(place);

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
}
