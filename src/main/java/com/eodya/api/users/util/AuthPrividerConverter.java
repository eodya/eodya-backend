package com.eodya.api.users.util;

import com.eodya.api.users.domain.OAuthProvider;
import jakarta.persistence.AttributeConverter;

public class AuthPrividerConverter implements AttributeConverter<OAuthProvider, String> {

    @Override
    public String convertToDatabaseColumn(OAuthProvider oAuthProvider) {
        return oAuthProvider.getDescription();
    }

    @Override
    public OAuthProvider convertToEntityAttribute(String description) {
        return OAuthProvider.from(description);
    }
}
