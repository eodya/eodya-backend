package com.eodya.api.place.dto.request;

import com.eodya.api.address.domain.AddressDepth1;
import com.eodya.api.address.domain.AddressDepth2;
import com.eodya.api.place.domain.Place;
import com.eodya.api.place.domain.PlaceStatus;
import com.eodya.api.review.domain.Review;
import com.eodya.api.users.domain.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.locationtech.jts.geom.Point;
import org.springframework.web.multipart.MultipartFile;

@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PlaceCreateRequest {

    @NotNull
    List<MultipartFile> images;

    @NotBlank
    String name;

    @NotBlank
    String addressDetail;

    @NotBlank
    String reviewContent;

    @NotNull
    PlaceStatus placeStatus;

    @NotBlank
    String reviewDate;

    @NotBlank
    String addressDepth1;

    @NotBlank
    String addressDepth2;

    @NotNull
    double x;

    @NotNull
    double y;

    public Place toPlaceEntity(Point point,
                          String image,
                          User user,
                          AddressDepth1 addressDepth1,
                          AddressDepth2 addressDepth2) {

        return Place.builder()
                .point(point)
                .name(name)
                .image(image)
                .addressDetail(addressDetail)
                .user(user)
                .addressDepth1(addressDepth1)
                .addressDepth2(addressDepth2)
                .build();
    }

    public Review toReviewEntity(User user, Place place) {

        return Review.builder()
                .reviewDate(LocalDate.parse(reviewDate))
                .placeStatus(placeStatus)
                .reviewContent(reviewContent)
                .user(user)
                .place(place)
                .build();
    }

    public AddressDepth1 toAddressDepth1Entity(String addressDepth1) {
        return AddressDepth1.builder()
                .name(addressDepth1)
                .build();
    }

    public AddressDepth2 toAddressDepth2Entity(String addressDepth2, AddressDepth1 addressDepth1) {
        return AddressDepth2.builder()
                .name(addressDepth2)
                .addressDepth1(addressDepth1)
                .build();
    }

}
