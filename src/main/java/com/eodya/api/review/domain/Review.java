package com.eodya.api.review.domain;

import com.eodya.api.common.entity.BaseEntity;
import com.eodya.api.place.domain.Place;
import com.eodya.api.place.domain.PlaceStatus;
import com.eodya.api.users.domain.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
    @Enumerated(EnumType.STRING)
    private PlaceStatus placeStatus;

    @NotNull
    @Column(length = 1000)
    private String reviewContent;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "place_id")
    private Place place;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReviewImage> images = new ArrayList<>();

    public void setPlace(Place place) {
        this.place = place;
        this.place.getReviews().add(this);
    }

    public void setUser(User user) {
        this.user = user;
        this.user.getReviews().add(this);
    }

    @Builder
    private Review(
            LocalDate reviewDate,
            PlaceStatus placeStatus,
            String reviewContent,
            Place place,
            User user
    ) {
        this.reviewDate = reviewDate;
        this.placeStatus = placeStatus;
        this.reviewContent = reviewContent;
        setPlace(place);
        setUser(user);
    }
}
