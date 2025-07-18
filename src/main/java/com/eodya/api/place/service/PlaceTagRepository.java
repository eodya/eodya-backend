package com.eodya.api.place.service;

import com.eodya.api.place.domain.Place;
import com.eodya.api.place.domain.PlaceStatus;
import com.eodya.api.place.domain.PlaceTag;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface PlaceTagRepository extends JpaRepository<PlaceTag, Long> {
    Optional<PlaceTag> findByPlaceAndTag(Place place, PlaceTag tag);
    List<PlaceTag> findByTag(PlaceStatus tag);
}
