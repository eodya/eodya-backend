package com.eodya.api.bookmark.service;

import com.eodya.api.bookmark.domain.Bookmark;
import com.eodya.api.bookmark.domain.BookmarkStatus;
import com.eodya.api.bookmark.dto.request.BookMarkChangeStatusRequest;
import com.eodya.api.bookmark.repository.BookmarkRepository;
import com.eodya.api.place.domain.Place;
import com.eodya.api.place.repository.PlaceRepository;
import com.eodya.api.users.domain.User;
import com.eodya.api.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BookmarkService {

    private final BookmarkRepository bookmarkRepository;
    private final UserRepository userRepository;
    private final PlaceRepository placeRepository;

    @Transactional
    public void updateBookmarkStatus(
            Long userId,
            Long placeId,
            BookMarkChangeStatusRequest changeStatusRequest
    ) {
        User user = userRepository.getUserById(userId);
        Place place = placeRepository.getPlaceById(placeId);

        Bookmark bookmark = bookmarkRepository.findByPlaceId(placeId)
                .orElseGet(() -> Bookmark.builder()
                        .place(place)
                        .user(user)
                        .build());

        BookmarkStatus bookmarkStatus = changeStatusRequest.isCurrentStatus() ? BookmarkStatus.TRUE : BookmarkStatus.FALSE;
        bookmark.updateStatus(bookmarkStatus);

        bookmarkRepository.save(bookmark);
    }

}
