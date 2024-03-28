package com.eodya.api.place.repository;

import com.eodya.api.place.domain.Place;
import com.eodya.api.place.exception.PlaceException;
import com.eodya.api.place.exception.PlaceExceptionCode;
import org.springframework.data.jpa.repository.JpaRepository;

import static com.eodya.api.place.exception.PlaceExceptionCode.*;

public interface PlaceRepository extends JpaRepository<Place, Long> {
    default Place getPlaceById(Long placeId) {
        return findById(placeId).orElseThrow(() -> new PlaceException(PLACE_NOT_FOUNT, placeId));
    }
}
