package com.eodya.api.review.repository;

import com.eodya.api.review.domain.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    Page<Review> findReviewsByPlace(Long placeId, Pageable pageable);
}
