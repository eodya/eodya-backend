package com.eodya.api.place.repository;

import com.eodya.api.place.domain.Place;
import com.eodya.api.place.domain.PlaceTag;
import com.eodya.api.place.domain.Tag;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceTagRepository extends JpaRepository<PlaceTag, Long> {

    Optional<PlaceTag> findByPlaceAndTag(Place place, Tag tag);

    List<PlaceTag> findByTag(Tag tag);
}
