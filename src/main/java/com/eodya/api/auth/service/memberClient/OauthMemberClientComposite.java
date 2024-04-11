package com.eodya.api.auth.service.memberClient;

import com.eodya.api.auth.domain.oauth.OauthMember;
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
public class OauthMemberClientComposite {

    private final Map<OauthProvider, OauthMemberClient> mapping;

    public OauthMemberClientComposite(Set<OauthMemberClient> clients) {
        mapping = clients.stream()
                .collect(toMap(OauthMemberClient::oauthProvider, identity()));
    }

    public OauthMember fetch(OauthProvider oauthProvider, String authCode) {
        return getClient(oauthProvider).fetch(authCode);
    }

    private OauthMemberClient getClient(final OauthProvider oauthProvider) {
        return Optional.ofNullable(mapping.get(oauthProvider))
                .orElseThrow(() -> new AuthException(AUTH_NOT_FOUND_OAUTH_PROVIDER, oauthProvider.toString()));
    }
}
