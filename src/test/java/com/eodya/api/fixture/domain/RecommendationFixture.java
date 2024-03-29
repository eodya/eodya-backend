package com.eodya.api.fixture.domain;

import com.eodya.api.place.domain.Place;
import com.eodya.api.recommendation.domain.Recommendation;
import com.eodya.api.recommendation.domain.RecommendationStatus;
import com.eodya.api.users.domain.OAuthProvider;
import com.eodya.api.users.domain.User;

import java.util.List;
import java.util.stream.IntStream;

public class RecommendationFixture {

    public static Recommendation recommendationBuilder() {
        User testuser = UserFixture.userBuild();
        Place testPlace = PlaceFixture.placeBuild(testuser);

        return Recommendation.builder()
                .user(testuser)
                .place(testPlace)
                .status(RecommendationStatus.TRUE)
                .build();
    }

    public static List<Recommendation> recommendationsBuilder(int count) {

        List<User> users = UserFixture.usersBuild(count, OAuthProvider.KAKAO);
        List<Place> places = PlaceFixture.placesBuild(count);
        List<Recommendation> recommendations = IntStream.range(0, count)
                .mapToObj(i -> {
                    User user = users.get(i % users.size());
                    Place place = places.get(i % places.size());
                    return Recommendation.builder()
                            .status(RecommendationStatus.TRUE)
                            .user(user)
                            .place(place)
                            .build();
                })
                .toList();

        return recommendations;
    }
}
