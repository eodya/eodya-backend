package com.eodya.api.bookmark.domain;

import com.eodya.api.bookmark.util.BookmarkStatusConverter;
import com.eodya.api.common.entity.BaseEntity;
import com.eodya.api.place.domain.Place;
import com.eodya.api.users.domain.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static com.eodya.api.bookmark.domain.BookmarkStatus.*;

@Getter
@Entity
@Table(name = "bookmark")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Bookmark extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bookmark_id")
    private Long id;

    @NotNull
    @Convert(converter = BookmarkStatusConverter.class)
    @Column(length = 20)
    private BookmarkStatus status = FALSE;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "place_id")
    private Place place;

    public void setUser(User user) {
        this.user = user;
        this.user.getBookmarks().add(this);
    }

    @Builder
    private Bookmark(
            BookmarkStatus status,
            User user,
            Place place
    ) {
        this.status = status;
        this.place = place;
        setUser(user);
    }
}
