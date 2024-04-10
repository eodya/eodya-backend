package com.eodya.api.users.domain;

import com.eodya.api.bookmark.domain.Bookmark;
import com.eodya.api.common.entity.BaseEntity;
import com.eodya.api.recommendation.domain.Recommendation;
import com.eodya.api.review.domain.Review;
import com.eodya.api.place.domain.Place;
import com.eodya.api.users.util.AuthPrividerConverter;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

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
    @Column(name = "oauth_id")
    private String OAuthId;

    @NotNull
    @Convert(converter = AuthPrividerConverter.class)
    @Column(name = "oauth_provider")
    private OAuthProvider OAuthProvider;

    @OneToMany(mappedBy = "user")
    private List<Place> places = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Bookmark> bookmarks = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Recommendation> recommendations = new ArrayList<>();

    @Builder
    private User(
            String nickname,
            String OAuthId,
            OAuthProvider OAuthProvider
    ) {
        this.nickname = nickname;
        this.OAuthId = OAuthId;
        this.OAuthProvider = OAuthProvider;
    }

    public void changeNickName(String nickname) {
        this.nickname = nickname;
    }
}
