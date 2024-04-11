package com.eodya.api.users.domain;

import com.eodya.api.auth.domain.oauth.OauthProvider;
import com.eodya.api.common.entity.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @NotNull
    @Column(name = "nickname")
    private String nickname;

    @NotNull
    @Column(unique = true)
    private Long oauthId;

    @NotNull
    @Enumerated(value = EnumType.STRING)
    private OauthProvider oauthProvider;

    @Embedded
    private UserPlace userPlace = new UserPlace();

    @Embedded
    private UserReview userReview = new UserReview();

    @Embedded
    private UserBookmark userBookmark = new UserBookmark();

    @Builder
    private User(
            String nickname,
            Long oauthId,
            OauthProvider oauthProvider
    ) {
        this.nickname = nickname;
        this.oauthId = oauthId;
        this.oauthProvider = oauthProvider;
    }

    public void changeNickName(String nickname) {
        this.nickname = nickname;
    }
}
