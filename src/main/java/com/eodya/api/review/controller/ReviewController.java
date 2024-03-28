package com.eodya.api.review.controller;

import com.eodya.api.review.dto.request.ReviewCreateRequest;
import com.eodya.api.review.dto.response.ReviewIdResponse;
import com.eodya.api.review.service.ReviewService;
import com.eodya.api.users.config.Login;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(reviewService.createReview(reviewCreateRequest, loggedInMemberId, images));
    }
}
