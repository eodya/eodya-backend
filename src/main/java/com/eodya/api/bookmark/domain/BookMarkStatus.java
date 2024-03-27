package com.eodya.api.bookmark.domain;

import com.eodya.api.bookmark.exception.BookMarkException;
import com.eodya.api.recommendation.exception.RecommendationException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.eodya.api.bookmark.exception.BookMarkExceptionCode.*;


@Getter
@RequiredArgsConstructor
public enum BookMarkStatus {

    TRUE("생성"),
    FALSE("삭제"),
    ;

    @JsonValue
    private final String description;

    @JsonCreator
    public static BookMarkStatus from(String description) throws BookMarkException {
        if (statusMap.containsKey(description)) {
            return statusMap.get(description);
        }
        throw new RecommendationException(BOOKMARK_STATUS_NOT_FOUND, description);
    }

    private static final Map<String, BookMarkStatus> statusMap =
            Collections.unmodifiableMap(Arrays.stream(values())
                    .collect(Collectors.toMap(BookMarkStatus::getDescription, Function.identity())));
}
