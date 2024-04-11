package com.eodya.api.review.domain;

import com.eodya.api.common.entity.BaseEntity;
import com.eodya.api.place.domain.Place;
import com.eodya.api.place.domain.PlaceStatus;
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
    private LocalDate reviewDate;

    @NotNull
    @Convert(converter = PlaceStatus.class)
    @Column(name = "place_status")
    private PlaceStatus placeStatus;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "place_id")
    private Place place;

    @NotNull
    @Column(length = 1000)
    private String reviewContent;

    public void setPlace(Place place) {
        this.place = place;
        this.place.getReviews().add(this);
    }

    @Builder
    private Review(
            LocalDate reviewDate,
            PlaceStatus placeStatus,
            String reviewContent,
            Place place
    ) {
        this.reviewDate = reviewDate;
        this.placeStatus = placeStatus;
        this.reviewContent = reviewContent;
        setPlace(place);
    }
}
