package com.eodya.api.place.controller;

import static org.springframework.http.HttpStatus.NO_CONTENT;

import com.eodya.api.place.dto.request.PlaceCreateRequest;
import com.eodya.api.place.service.PlaceService;
import com.eodya.api.users.config.Login;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eodya.api.place.dto.response.PlaceRakingResponse;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/place")
public class PlaceController {

    private final PlaceService placeService;

    @PostMapping(value = "", consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createPlaceAndReview(@Login Long userId, @Valid @ModelAttribute PlaceCreateRequest request) {

        placeService.createPlaceAndReview(userId, request);

        return ResponseEntity.status(NO_CONTENT)
                .build();
    }

    @GetMapping("/rankingbybookmarks")
    public ResponseEntity<List<PlaceRakingResponse>> findPlaceRankingByBookmarks() {
        return ResponseEntity.status(OK)
                .body(placeService.findPlaceRankingByBookmarks());
    }
}
