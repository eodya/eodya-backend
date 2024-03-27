package com.eodya.api.bookmark.controller;

import com.eodya.api.bookmark.dto.request.BookMarkChangeStatusRequest;
import com.eodya.api.bookmark.service.BookmarkService;
import com.eodya.api.users.config.Login;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/bookmark")
public class BookMarkController {

    private final BookmarkService bookmarkService;

    @PatchMapping("/{placeId}")
    public ResponseEntity<Void> updateBookmarkStatus(
            @Login Long loggedInMemberId,
            @PathVariable Long placeId,
            @Valid @RequestBody BookMarkChangeStatusRequest changeStatusRequest

    ) {
        bookmarkService.updateBookmarkStatus(
                placeId,
                changeStatusRequest
        );

        return ResponseEntity.status(NO_CONTENT)
                .build();
    }
}
