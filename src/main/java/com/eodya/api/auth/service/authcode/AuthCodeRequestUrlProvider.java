package com.eodya.api.auth.service.authcode;

import com.eodya.api.auth.domain.oauth.OauthProvider;

public interface AuthCodeRequestUrlProvider {

    OauthProvider oauthprovider();

    String provideUrl();
}
