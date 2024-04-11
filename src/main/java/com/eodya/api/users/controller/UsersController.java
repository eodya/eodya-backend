package com.eodya.api.users.controller;

import static com.eodya.api.users.exception.UserExceptionCode.*;
import static org.springframework.http.HttpHeaders.SET_COOKIE;
import static org.springframework.http.HttpStatus.CREATED;

import com.eodya.api.auth.domain.config.property.JwtProperties;
import com.eodya.api.auth.domain.config.resolver.SignUp;
import com.eodya.api.users.dto.request.AuthenticatedUserResponse;
import com.eodya.api.users.dto.request.UserCreateRequest;
import com.eodya.api.users.exception.UserException;
import com.eodya.api.users.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UsersController {

    private final UserService userService;
    private final JwtProperties jwtProperties;

    @PostMapping
    public ResponseEntity<AuthenticatedUserResponse> createUser(
            @SignUp String oauthSubject,
            @Valid @RequestBody UserCreateRequest userCreateRequest,
            HttpServletResponse httpServletResponse
    ) {
        final String oauthProviderName = userCreateRequest.getOauthProvider().name();
        final String requestOauthSubject = oauthProviderName + userCreateRequest.getOauthId();

        if (!oauthSubject.equals(requestOauthSubject)) {
            throw new UserException(USER_SIGNUP_OAUTH_SUBJECT_INVALID, requestOauthSubject);
        }

        final AuthenticatedUserResponse authenticatedMemberResponse = userService.createMember(userCreateRequest);
        final String refreshToken = authenticatedMemberResponse.getRefreshToken();

        if (refreshToken != null) {
            final ResponseCookie cookie = ResponseCookie.from("refresh-token", refreshToken)
                    .maxAge(jwtProperties.getRefreshTokenExpirationTime())
                    .sameSite("None")
                    .secure(true)
                    .httpOnly(true)
                    .path("/")
                    .build();

            httpServletResponse.addHeader(SET_COOKIE, cookie.toString());
        }

        return ResponseEntity.status(CREATED)
                .body(authenticatedMemberResponse);

    }
}


//    @PostMapping("/login")
//    public ResponseEntity<UserLoginResponse> loginAndSignIn(
//            @Valid @RequestBody UserLoginRequest request
//    ) {
//        UserLoginResponse response = userService.login(request.getToken());
//        return ResponseEntity.ok().body(response);
//    }
//
//    @PatchMapping("/nickname")
//    public ResponseEntity<Void> updateNickName(
//            @Login Long userId,
//            @Valid @RequestBody UserUpdateNickNameRequest request) {
//        userService.updateNickName(userId, request.getNickname());
//        return ResponseEntity.status(NO_CONTENT)
//                .build();
//    }
//
//    @GetMapping("/my/info")
//    public ResponseEntity<UserInfoResponse> getMyInfo(@Login Long userId) {
//        return ResponseEntity.ok().body(userService.getMyInfo(userId));
//    }

//    @GetMapping("/my/bookmarks")
//    public ResponseEntity<UserMyBookmarkResponse> getMyBookmarks(
//            @Login Long userId,
//            @PageableDefault(sort = "updatedAt", direction = Direction.DESC) Pageable pageable) {
//        return ResponseEntity.ok()
//                .body(userService.getMyBookmarks(userId, pageable));
//    }
//
//    @GetMapping("/my/reviews")
//    public ResponseEntity<UserMyReviewsResponse> getMyReviews(
//            @Login Long userId,
//            @PageableDefault(sort = "updatedAt", direction = Direction.DESC) Pageable pageable) {
//        return ResponseEntity.ok()
//                .body(userService.getMyReviews(userId, pageable));
//    }
