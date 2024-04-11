package com.eodya.api.bookmark.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Bookmark", description = "북마크 API")

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/bookmark")
public class BookmarkController {

//    private final BookmarkService bookmarkService;
//
//    @Operation(
//            summary = "북마크 API",
//            description = "해당 장소에 대해서 북마크를 추가하거나 삭제합니다."
//    )

//    @PatchMapping("/{placeId}")
//    public ResponseEntity<Void> updateBookmarkStatus(
//            @Login Long loggedInMemberId,
//            @PathVariable Long placeId,
//            @Valid @RequestBody BookMarkChangeStatusRequest changeStatusRequest
//
//    ) {
//        bookmarkService.updateBookmarkStatus(
//                loggedInMemberId,
//                placeId,
//                changeStatusRequest
//        );
//
//        return ResponseEntity.status(OK)
//                .build();
//    }
}
