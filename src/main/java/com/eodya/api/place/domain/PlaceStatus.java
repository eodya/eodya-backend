package com.eodya.api.place.domain;

import com.eodya.api.place.exception.PlaceException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.eodya.api.place.exception.PlaceExceptionCode.*;

@Getter
@RequiredArgsConstructor
public enum PlaceStatus {

    BLOOMING("개화"),
    FULL_BLOOM("만개"),
    NEXT_YEAR("내년에 만나요"),
    ;

    @JsonValue
    private final String description;

    @JsonCreator
    public static PlaceStatus from(String description) throws PlaceException {
        if (statusMap.containsKey(description)) {
            return statusMap.get(description);
        }
        throw new PlaceException(PLACE_NOT_FOUNT, description);
    }

    private static final Map<String, PlaceStatus> statusMap =
            Collections.unmodifiableMap(Stream.of(values())
                    .collect(Collectors.toMap(PlaceStatus::getDescription, Function.identity())));
}
