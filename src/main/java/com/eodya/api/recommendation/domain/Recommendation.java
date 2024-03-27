package com.eodya.api.recommendation.domain;

import com.eodya.api.common.entity.BaseEntity;
import com.eodya.api.place.domain.Place;
import com.eodya.api.recommendation.util.RecommendationStatusConverter;
import com.eodya.api.users.domain.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import jakarta.persistence.Convert;


import static com.eodya.api.recommendation.domain.RecommendationStatus.*;

@Getter
@Entity
@Table(name = "recommendation")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Recommendation extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recommendation_id")
    private Long id;

    @NotNull
    @Convert(converter = RecommendationStatusConverter.class)
    @Column(length = 20)
    private RecommendationStatus status = FALSE;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "place_id")
    private Place place;

    public void setUser(User user) {
        this.user = user;
        this.user.getRecommendations().add(this);
    }

    @Builder
    private Recommendation(
            RecommendationStatus status,
            User user,
            Place place
    ) {
        this.status = status;
        this.place = place;
        setUser(user);
    }
}
