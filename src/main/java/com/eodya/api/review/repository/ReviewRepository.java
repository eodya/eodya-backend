package com.eodya.api.review.repository;

import com.eodya.api.review.domain.Review;
import com.eodya.api.review.exception.ReviewException;
import org.springframework.data.jpa.repository.JpaRepository;

import static com.eodya.api.review.exception.ReviewExceptionCode.*;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
