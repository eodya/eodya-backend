package com.eodya.api.entity;

import com.eodya.api.common.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLRestriction;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SQLRestriction("bookmark_status = true and like_status = true")
@Entity
@Table(name = "users")
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
    @Column(name = "oauth_provider")
    private String OAuthProvider;

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
            String OAuthProvider
    ) {
        this.nickname = nickname;
        this.OAuthId = OAuthId;
        this.OAuthProvider = OAuthProvider;
    }

}
