package com.eodya.api.place.domain;

import com.eodya.api.address.domain.AddressDepth1;
import com.eodya.api.address.domain.AddressDepth2;
import com.eodya.api.common.entity.BaseEntity;
import com.eodya.api.review.domain.Review;
import com.eodya.api.users.domain.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.locationtech.jts.geom.*;

@Getter
@Entity
@Table(name = "place")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Place extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "place_id")
    private Long id;

    @NotNull
    private Point point;

    @NotNull
    private String name;

    @NotNull
    private String image;

    @NotNull
    private String addressDetail;

    @NotNull
    private Integer review_count;

    @NotNull
    private Integer bookmarkCount;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "place")
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "place")
    private List<PlaceTag> placeTags = new ArrayList<>();

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_depth1_id")
    private AddressDepth1 addressDepth1;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_depth2_id")
    private AddressDepth2 addressDepth2;

    public void setImage(String image) {
        this.image = image;
    }

    public int getBookmarkCount() {
        return bookmarkCount;
    }

    public void addBookmarkCount() {
        this.bookmarkCount += 1;
    }

    @Builder
    private Place(
            Point point,
            String name,
            String addressDetail,
            User user,
            AddressDepth1 addressDepth1,
            AddressDepth2 addressDepth2
    ) {
        this.point = point;
        this.name = name;
        this.addressDetail = addressDetail;
        this.user = user;
        this.review_count = 0;
        this.bookmarkCount = 0;
        this.addressDepth1 = addressDepth1;
        this.addressDepth2 = addressDepth2;
    }
}
