package com.eodya.api.entity;

import com.eodya.api.entity.superclass.TimeStamped;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.locationtech.jts.geom.*;

@Getter
@NoArgsConstructor
@Entity
public class Place extends TimeStamped {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "place_id")
    private Long id;

    private Point point;

    private String name;

    private String image;

    private String addressDetail;

    private int recommendCount;

    private int bookmarkCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "place")
    private List<Review> reviews;

    @OneToMany(mappedBy = "place")
    private List<PlaceTag> placeTags;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_depth1_id")
    private AddressDepth1 depth1;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_depth2_id")
    private AddressDepth2 depth2;

    public void setUser(User user) {
        this.user = user;
        user.getPlaces().add(this);
    }

}
