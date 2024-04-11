package com.eodya.api.place.util;

import com.eodya.api.place.domain.PlaceStatus;
import jakarta.persistence.AttributeConverter;

public class PlaceStatusConverter implements AttributeConverter<PlaceStatus, String> {

    @Override
    public String convertToDatabaseColumn(PlaceStatus placeStatus) {
        return placeStatus.getDescription();
    }

    @Override
    public PlaceStatus convertToEntityAttribute(String description) {
        return PlaceStatus.from(description);
    }
}
