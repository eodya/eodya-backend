package com.eodya.api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "place_tag")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PlaceTag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "place_id")
    private Place place;

    public void setPlace(Place place) {
        this.place = place;
        this.place.getPlaceTags().add(this);
    }

    @Builder
    private PlaceTag(String name, Place place) {
        this.name = name;
        setPlace(place);
    }
}
