package com.eodya.api.place.controller;

import static org.springframework.http.HttpStatus.NO_CONTENT;

import com.eodya.api.place.dto.request.PlaceAllByAddressRequest;
import com.eodya.api.place.dto.request.PlaceCreateRequest;
import com.eodya.api.place.dto.response.PlaceAllByAddressResponse;
import com.eodya.api.place.dto.response.PlaceAllByTagResponse;
import com.eodya.api.place.dto.response.PlaceDetailResponse;
import com.eodya.api.place.service.PlaceService;
import com.eodya.api.users.config.Login;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eodya.api.place.dto.response.PlaceRakingResponse;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

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
