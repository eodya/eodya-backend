package com.eodya.api.auth.controller;

import com.eodya.api.auth.domain.config.property.JwtProperties;
import com.eodya.api.auth.domain.oauth.OauthProvider;
import com.eodya.api.auth.dto.response.AccessTokenResponse;
import com.eodya.api.auth.service.OauthService;
import com.eodya.api.users.dto.request.AuthenticatedUserResponse;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

import static org.springframework.http.HttpHeaders.SET_COOKIE;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class OauthController {

    private final OauthService oauthService;
    private final JwtProperties jwtProperties;
    private static final String COOKIE_TOKEN = "refresh-token";

    @GetMapping("/login/{oauthProvider}")
    public ResponseEntity<AuthenticatedUserResponse> login(
            @PathVariable OauthProvider oauthProvider,
            @RequestParam String authCode,
            HttpServletResponse httpServletResponse
    ) {
        AuthenticatedUserResponse authenticatedUserResponse = oauthService.processLoginOrRegistration(
                oauthProvider, authCode);
        String refreshToken = authenticatedUserResponse.getRefreshToken();

        if (refreshToken != null) {
            final ResponseCookie cookie = ResponseCookie.from(COOKIE_TOKEN, refreshToken)
                    .maxAge(jwtProperties.getRefreshTokenExpirationTime())
                    .sameSite("None")
                    .secure(true)
                    .httpOnly(true)
                    .path("/")
                    .build();

            httpServletResponse.addHeader(SET_COOKIE, cookie.toString());
        }

        return ResponseEntity.ok().body(authenticatedUserResponse);
    }

    @PostMapping("/refresh")
    public ResponseEntity<AccessTokenResponse> regenerateAccessToken(
            @CookieValue(COOKIE_TOKEN) String refreshToken,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        AccessTokenResponse regeneratedAccessToken = oauthService.regenerateAccessToken(refreshToken,
                authorizationHeader);

        return ResponseEntity.status(CREATED)
                .body(regeneratedAccessToken);
    }

    @GetMapping("/{oauthProvider}")
    public void redirectAuthCodeRequestUrl(
            @PathVariable OauthProvider oauthProvider,
            HttpServletResponse response
    ) throws IOException {
        String redirectUrl = oauthService.getAuthCodeRequestUrl(oauthProvider);

        response.sendRedirect(redirectUrl);
    }
}
