package com.eodya.api.review.domain;

import com.eodya.api.common.entity.BaseEntity;
import com.eodya.api.place.domain.Place;
import com.eodya.api.place.domain.PlaceStatus;
import com.eodya.api.place.util.PlaceStatusConverter;
import com.eodya.api.users.domain.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "review")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "place_id")
    private Place place;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @NotNull
    private LocalDate reviewDate;

    @NotNull
    @Convert(converter = PlaceStatusConverter.class)
    @Column(name = "place_status", length = 50)
    private PlaceStatus placeStatus;

    @NotNull
    @Column(length = 1000)
    private String reviewContent;

    @Builder
    private Review(
            Place place,
            User user,
            LocalDate reviewDate,
            PlaceStatus placeStatus,
            String reviewContent
    ) {
        this.place = place;
        this.user = user;
        this.reviewDate = reviewDate;
        this.placeStatus = placeStatus;
        this.reviewContent = reviewContent;
    }
}
