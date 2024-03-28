package com.eodya.api.review.controller;

import com.eodya.api.review.domain.Review;
import com.eodya.api.review.dto.request.ReviewCreateRequest;
import com.eodya.api.review.dto.response.ReviewIdResponse;
import com.eodya.api.review.service.ReviewService;
import com.eodya.api.users.config.Login;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/review")
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    public ResponseEntity<ReviewIdResponse> createReview(
            @Login Long loggedInMemberId,
            @Valid @RequestBody ReviewCreateRequest reviewCreateRequest
    ) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(reviewService.createReview(reviewCreateRequest, loggedInMemberId));
    }
}
