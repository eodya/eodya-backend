package com.eodya.api.entity;

import com.eodya.api.common.entity.BaseEntity;
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
    private int recommendCount;

    @NotNull
    private int bookmarkCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "place")
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "place")
    private List<PlaceTag> placeTags = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_depth1_id")
    private AddressDepth1 depth1;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_depth2_id")
    private AddressDepth2 depth2;

    public void setUser(User user) {
        this.user = user;
        this.user.getPlaces().add(this);
    }

    @Builder
    private Place(
            Point point,
            String name,
            String image,
            String addressDetail,
            User user,
            AddressDepth1 addressDepth1,
            AddressDepth2 addressDepth2
    ) {
        this.point = point;
        this.name = name;
        this.image = image;
        this.addressDetail = addressDetail;
        this.recommendCount = 0;
        this.bookmarkCount = 0;
        this.depth1 = addressDepth1;
        this.depth2 = addressDepth2;
        setUser(user);
    }
}
