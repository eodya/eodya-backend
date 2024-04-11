package com.eodya.api.auth.domain.config;

import com.eodya.api.auth.domain.oauth.OauthProvider;
import org.springframework.core.convert.converter.Converter;

public class OauthProviderConverter implements Converter<String, OauthProvider> {

    @Override
    public OauthProvider convert(String type) {
        return OauthProvider.from(type);
    }
}
