package com.eodya.api.users.domain;

import com.eodya.api.bookmark.domain.Bookmark;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

public class UserBookmark {

    @OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    private List<Bookmark> userBookmark = new ArrayList<>();

    public void addUserBookmark(Bookmark bookmark) {
        userBookmark.add(bookmark);
    }
}
