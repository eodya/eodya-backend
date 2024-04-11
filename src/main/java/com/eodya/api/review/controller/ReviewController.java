package com.eodya.api.review.controller;

import com.eodya.api.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/review")
public class ReviewController {

    private final ReviewService reviewService;

//    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
//    public ResponseEntity<ReviewIdResponse> createReview(
//            @Login Long loggedInMemberId,
//            @RequestPart("review") @Valid ReviewCreateRequest reviewCreateRequest,
//            @RequestPart(value = "images", required = false) List<MultipartFile> images
//    ) {
//        return ResponseEntity.status(CREATED)
//                .body(reviewService.createReview(reviewCreateRequest, loggedInMemberId, images));
//    }
//
//    @GetMapping
//    public ResponseEntity<ReviewProfileResponse> findReviewsByPlace(
//            @Login Long loggedInMemberId,
//            @Valid @RequestParam Long placeId,
//            Pageable pageable
//    ) {
//        return ResponseEntity.status(OK)
//                .body(reviewService.findReviewListByPlace(placeId, loggedInMemberId, pageable));
//    }
}
