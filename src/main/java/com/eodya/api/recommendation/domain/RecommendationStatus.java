package com.eodya.api.recommendation.domain;

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

import static com.eodya.api.recommendation.exception.RecommendationExceptionCode.*;

@Getter
@RequiredArgsConstructor
public enum RecommendationStatus {

    TRUE("생성"),
    FALSE("삭제"),
    ;

    @JsonValue
    private final String description;

    @JsonCreator
    public static RecommendationStatus from(String description) throws RecommendationException {
        if (statusMap.containsKey(description)) {
            return statusMap.get(description);
        }
        throw new RecommendationException(RECOMMENDATION_STATUS_NOT_FOUND, description);
    }

    private static final Map<String, RecommendationStatus> statusMap =
            Collections.unmodifiableMap(Arrays.stream(values())
                    .collect(Collectors.toMap(RecommendationStatus::getDescription, Function.identity())));
}
