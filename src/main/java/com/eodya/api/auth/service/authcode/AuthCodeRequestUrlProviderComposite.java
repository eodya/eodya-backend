package com.eodya.api.auth.service.authcode;

import com.eodya.api.auth.domain.oauth.OauthProvider;
import com.eodya.api.auth.exception.AuthException;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static com.eodya.api.auth.exception.AuthExceptionCode.AUTH_NOT_FOUND_OAUTH_PROVIDER;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

@Component
public class AuthCodeRequestUrlProviderComposite {

    private final Map<OauthProvider, AuthCodeRequestUrlProvider> mapping;

    public AuthCodeRequestUrlProviderComposite(Set<AuthCodeRequestUrlProvider> providers) {
        mapping = providers.stream()
                .collect(toMap(AuthCodeRequestUrlProvider::oauthprovider, identity()));
    }

    public String provide(OauthProvider oauthProvider) {
        return getProvider(oauthProvider).provideUrl();
    }

    private AuthCodeRequestUrlProvider getProvider(OauthProvider oauthProvider) {
        return Optional.ofNullable(mapping.get(oauthProvider))
                .orElseThrow(() -> new AuthException(AUTH_NOT_FOUND_OAUTH_PROVIDER, oauthProvider.toString()));
    }
}
