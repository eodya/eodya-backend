package com.eodya.api.place.repository;

import com.eodya.api.place.domain.Place;
import com.eodya.api.place.domain.PlaceStatus;
import com.eodya.api.place.domain.PlaceTag;
import com.eodya.api.place.domain.Tag;
import com.eodya.api.place.exception.PlaceException;
import com.eodya.api.place.exception.PlaceExceptionCode;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import static com.eodya.api.place.exception.PlaceExceptionCode.*;

public interface PlaceRepository extends JpaRepository<Place, Long> {

    List<Place> findAllByOrderByBookmarkCountDesc();

    default Place getPlaceById(Long placeId) {
        return findById(placeId).orElseThrow(() -> new PlaceException(PLACE_NOT_FOUNT, placeId));
    }

    @Query("select p from Place p join fetch p.placeTags where p.addressDetail = :addressDetail")
    Optional<Place> findByAddressDetail(@Param("addressDetail") String addressDetail);

}
