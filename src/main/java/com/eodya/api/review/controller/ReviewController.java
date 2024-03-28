package com.eodya.api.review.controller;

import com.eodya.api.review.dto.request.ReviewCreateRequest;
import com.eodya.api.review.dto.response.ReviewIdResponse;
import com.eodya.api.review.dto.response.ReviewProfileResponse;
import com.eodya.api.review.service.ReviewService;
import com.eodya.api.users.config.Login;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/review")
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<ReviewIdResponse> createReview(
            @Login Long loggedInMemberId,
            @RequestPart("review") @Valid ReviewCreateRequest reviewCreateRequest,
            @RequestPart(value = "images", required = false) List<MultipartFile> images
    ) {
        return ResponseEntity.status(CREATED)
                .body(reviewService.createReview(reviewCreateRequest, loggedInMemberId, images));
    }

    @GetMapping
    public ResponseEntity<ReviewProfileResponse> findReviewsByPlace(
            @Login Long loggedInMemberId,
            @Valid @RequestParam Long placeId,
            Pageable pageable
    ) {
        return ResponseEntity.status(OK)
                .body(reviewService.findReviewListByPlace(placeId, loggedInMemberId, pageable));
    }
}
