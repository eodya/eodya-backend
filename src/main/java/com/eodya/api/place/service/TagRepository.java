package com.eodya.api.place.service;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eodya.api.place.domain.PlaceStatus;

public interface TagRepository extends JpaRepository<Tag, Long> {
    Optional<Tag> findByName(String name);
}
