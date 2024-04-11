package com.eodya.api.users.dto.request;

import com.eodya.api.auth.domain.oauth.OauthProvider;
import com.eodya.api.users.domain.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserCreateRequest {

    @NotBlank(message = "닉네임은 null이거나 빈 문자열이거나 공백일 수 없음")
    private String nickname;

    @NotNull(message = "oauth id는 null일 수 없음")
    @Positive(message = "oauth id는 값이 없거나 음수일 수 없음")
    private Long oauthId;

    @NotNull(message = "oauth 제공자는 null이거나 빈 문자열이거나 공백일 수 없음")
    private OauthProvider oauthProvider;

    public User toEntity() {
        return User.builder()
                .nickname(nickname)
                .oauthId(oauthId)
                .oauthProvider(oauthProvider)
                .build();
    }
}
