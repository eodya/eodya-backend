package com.eodya.api.place.repository;

import com.eodya.api.place.domain.Place;
import com.eodya.api.place.exception.PlaceException;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import static com.eodya.api.place.exception.PlaceExceptionCode.*;

public interface PlaceRepository extends JpaRepository<Place, Long> {

    List<Place> findAll(Sort sort);

    @Query("select p from Place p join fetch p.placeTags where p.addressDetail = :addressDetail")
    Optional<Place> findByAddressDetail(@Param("addressDetail") String addressDetail);

    @Query("select p from Place p where p.id in :ids")
    List<Place> findByPlaceIds(@Param("ids") List<Long> placeIds);

    @Query("SELECT p FROM Place p WHERE p.addressDetail LIKE CONCAT('%', :address, '%')")
    Page<Place> findByAddressContaining(@Param("address") String address, Pageable pageable);

    @Query("SELECT COUNT(b.id) FROM Bookmark b WHERE b.place = :place AND b.status = 'TRUE'")
    Long countBookmarksByPlace(@Param("place") Place place);

    default Place getPlaceById(Long placeId) {
        return findById(placeId).orElseThrow(() -> new PlaceException(PLACE_NOT_FOUNT, placeId));
    }
}
