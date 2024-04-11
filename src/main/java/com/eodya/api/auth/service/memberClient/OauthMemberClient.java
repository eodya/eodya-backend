package com.eodya.api.auth.service.memberClient;

import com.eodya.api.auth.domain.oauth.OauthMember;
import com.eodya.api.auth.domain.oauth.OauthProvider;

public interface OauthMemberClient {

    OauthProvider oauthProvider();

    OauthMember fetch(String code);
}

