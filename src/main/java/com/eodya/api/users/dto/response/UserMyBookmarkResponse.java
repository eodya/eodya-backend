package com.eodya.api.users.dto.response;

import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserMyBookmarkResponse {

    private Long totalBookmarkCount;
    private List<UserBookmarkDetail> bookmarks;
    private boolean hasNext;

    public static UserMyBookmarkResponse from(Long count,
                                              List<UserBookmarkDetail> bookmarks,
                                              boolean hasNext) {
        return UserMyBookmarkResponse.builder()
                .totalBookmarkCount(count)
                .bookmarks(bookmarks)
                .hasNext(hasNext)
                .build();
    }
}
