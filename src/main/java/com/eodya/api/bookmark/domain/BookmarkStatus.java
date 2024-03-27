package com.eodya.api.bookmark.domain;

import com.eodya.api.bookmark.exception.BookmarkException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.eodya.api.bookmark.exception.BookmarkExceptionCode.*;

@Getter
@RequiredArgsConstructor
public enum BookmarkStatus {

    TRUE("생성"),
    FALSE("삭제"),
    ;

    @JsonValue
    private final String description;

    @JsonCreator
    public static BookmarkStatus from(String description) throws BookmarkException {
        if (statusMap.containsKey(description)) {
            return statusMap.get(description);
        }
        throw new BookmarkException(BOOKMARK_STATUS_NOT_FOUND, description);
    }

    private static final Map<String, BookmarkStatus> statusMap =
            Collections.unmodifiableMap(Stream.of(values())
                    .collect(Collectors.toMap(BookmarkStatus::getDescription, Function.identity())));
}
