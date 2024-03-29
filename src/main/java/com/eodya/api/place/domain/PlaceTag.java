package com.eodya.api.place.domain;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "place_id")
    private Place place;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tag_id")
    private Tag tag;

    public void setPlace(Place place) {
        this.place = place;
        this.place.getPlaceTags().add(this);
    }

    public void setTag(Tag tag) {
        this.tag = tag;
        this.tag.getPlaceTags().add(this);
    }

    @Builder
    private PlaceTag(Place place, Tag tag) {
        setPlace(place);
        setTag(tag);
    }

}
