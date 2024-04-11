package com.eodya.api.place.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/place")
public class PlaceController {
//
//    private final PlaceService placeService;
//
//    @PostMapping(value = "", consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
//            produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Void> createPlaceAndReview(@Login Long userId, @Valid @ModelAttribute PlaceCreateRequest request) {
//
//        placeService.createPlaceAndReview(userId, request);
//        return ResponseEntity.status(NO_CONTENT)
//                .build();
//    }
//
//    @GetMapping("/rankingbybookmarks")
//    public ResponseEntity<List<PlaceRakingResponse>> findPlaceRankingByBookmarks() {
//        return ResponseEntity.status(OK)
//                .body(placeService.findPlaceRankingByBookmarks());
//    }
//
//    @GetMapping("/all")
//    public ResponseEntity<List<PlaceAllByTagResponse>> findAllPlaceByTag(
//            @Login Long userId,
//            @RequestParam String tag
//    ) {
//        return ResponseEntity.status(OK)
//                .body(placeService.findAllPlaceByTag(tag));
//    }
//
//    @PostMapping("/search")
//    public ResponseEntity<PlaceAllByAddressResponse> findAllPlaceByAddress(
//            @Login Long userId, @RequestBody PlaceAllByAddressRequest request,
//            @PageableDefault(sort = "bookmarkCount", direction = Direction.DESC) Pageable pageable) {
//        return ResponseEntity.status(OK)
//                .body(placeService.findAllPlaceByAddress(userId, request, pageable));
//    }
//
//    @GetMapping("/detail/{placeId}")
//    public ResponseEntity<PlaceDetailResponse> getPlaceDetail(
//            @Login Long userId,
//            @PathVariable Long placeId
//    ) {
//        return ResponseEntity.status(OK)
//                .body(placeService.getPlaceDetail(userId, placeId));
//    }
}
