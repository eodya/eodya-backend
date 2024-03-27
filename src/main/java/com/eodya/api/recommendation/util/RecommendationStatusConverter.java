package com.eodya.api.recommendation.util;

import com.eodya.api.recommendation.domain.RecommendationStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class RecommendationStatusConverter implements AttributeConverter<RecommendationStatus, String> {

    @Override
    public String convertToDatabaseColumn(RecommendationStatus recommendationStatus) {
        return recommendationStatus.getDescription();
    }

    @Override
    public RecommendationStatus convertToEntityAttribute(String status) {
        return RecommendationStatus.from(status);
    }
}
