package com.eodya.api.fixture.domain;

import com.eodya.api.place.domain.Place;
import com.eodya.api.place.domain.PlaceStatus;
import com.eodya.api.review.domain.Review;
import com.eodya.api.review.domain.ReviewImage;
import com.eodya.api.users.domain.User;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.IntStream;

public class ReviewFixture {

    public static Review reviewBuild() {
        User user = UserFixture.userBuild();
        LocalDate reviewDate = LocalDate.now();
        PlaceStatus placeStatus = PlaceStatus.BLOOMING;
        String reviewContent = "테스트 리뷰 내용";
        Place place = PlaceFixture.placeBuild(user);

        return Review.builder()
                .reviewDate(reviewDate)
                .placeStatus(placeStatus)
                .reviewContent(reviewContent)
                .place(place)
                .user(user)
                .build();
    }

    public static List<Review> reviewsBuild(int count) {

        return IntStream.range(0, count)
                .mapToObj(i -> reviewBuild())
                .toList();
    }

    public static ReviewImage reviewImageBuild(Review review) {

        return ReviewImage.builder()
                .imageUrl("http://example.com/image.jpg")
                .review(review)
                .build();
    }

    public static List<ReviewImage> reviewImagesBuild(Review review, int imageCount) {

        return IntStream.range(0, imageCount)
                .mapToObj(i -> ReviewImage.builder()
                        .imageUrl(String.format("http://example.com/image%d.jpg", i))
                        .review(review)
                        .build())
                .toList();
    }
}
