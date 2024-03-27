package com.eodya.api.recommendation.util;

import com.eodya.api.recommendation.domain.RecommendationStatus;
import org.springframework.stereotype.Component;
import org.springframework.core.convert.converter.Converter;


@Component
public class RecommendationStatusConverter implements Converter<String, RecommendationStatus> {

    @Override
    public RecommendationStatus convert(String status) {
        return RecommendationStatus.from(status);
    }
}
