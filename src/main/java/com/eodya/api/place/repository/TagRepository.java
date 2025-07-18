package com.eodya.api.place.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amazonaws.services.cloudformation.model.Tag;

public interface TagRepository extends JpaRepository<Tag, Long> {

    Optional<Tag> findByName(String tagName);
}
