package com.eodya.api.users.service;

import static com.eodya.api.users.exception.UserExceptionCode.ALREADY_EXIST_NICKNAME;

import com.eodya.api.bookmark.repository.BookmarkRepository;
import com.eodya.api.review.repository.ReviewRepository;
import com.eodya.api.users.config.JwtTokenManager;
import com.eodya.api.users.domain.OAuthProvider;
import com.eodya.api.users.domain.User;
import com.eodya.api.users.dto.response.UserInfoResponse;
import com.eodya.api.users.dto.response.UserLoginResponse;
import com.eodya.api.users.exception.UserException;
import com.eodya.api.users.repository.UserRepository;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final SocialService socialService;
    private final JwtTokenManager jwtTokenManager;
    private final BookmarkRepository bookmarkRepository;
    private final ReviewRepository reviewRepository;
    private final String SERVICE_NAME = "어댜";

    @Transactional
    public UserLoginResponse login(String token) {
       Long oauthId = Long.valueOf(socialService.getOAuthId(token));
       Optional<User> findUser = userRepository.findByOauthId(oauthId);

        User user = findUser.orElseGet(() -> {
            User newUser = User.builder()
                    .nickname(SERVICE_NAME)
                    .oauthId(oauthId)
                    .oauthProvider(OAuthProvider.KAKAO)
                    .build();
            userRepository.save(newUser);
            newUser.changeNickName(SERVICE_NAME+newUser.getId());
            return newUser;
        });

       String accessToken = jwtTokenManager.createAccessToken(user.getId());

       return UserLoginResponse.builder()
               .token(accessToken)
               .userId(user.getId())
               .nickname(user.getNickname())
               .build();
    }

    @Transactional
    public void updateNickName(Long userId, String nickName) {
        User user = userRepository.getUserById(userId);
        userRepository.findByNickname(nickName)
                .ifPresent(e -> {
                    throw new UserException(ALREADY_EXIST_NICKNAME);
                });

        user.changeNickName(nickName);
        userRepository.save(user);
    }

    public UserInfoResponse getMyInfo(Long userId) {
        User user = userRepository.getUserById(userId);
        return UserInfoResponse.from(user);
    }

//    public UserMyBookmarkResponse getMyBookmarks(Long userId, Pageable pageable) {
//        Page<Bookmark> bookmarks = bookmarkRepository.findByUserIdAndStatus(userId, pageable);
//        boolean hasNext = bookmarks.hasNext();
//
//        List<Place> bookmarkPlaces = bookmarks.stream()
//                                        .map(bookmark -> bookmark.getPlace())
//                                        .toList();
//
//        List<UserBookmarkDetail> details = bookmarkPlaces.stream()
//                .map((place)-> {
//                    PlaceStatus placeStatus = place.getReviews().stream() //가장 최신의 리뷰를 가져옴
//                            .sorted(Comparator.comparing(Review::getReviewDate).reversed())
//                            .findFirst()
//                            .get()
//                            .getPlaceStatus();
//
//                    return UserBookmarkDetail.from(place, placeStatus);
//                }).toList();
//        return UserMyBookmarkResponse.from(bookmarks.getTotalElements(), details, hasNext);
//    }

//    public UserMyReviewsResponse getMyReviews(Long userId, Pageable pageable) {
//        Page<Review> reviews = reviewRepository.findByUserId(userId, pageable);
//        boolean hasNext = reviews.hasNext();
//
//        List<UserReviewDetail> userReviews = reviews.getContent().stream()
//                .map((review -> {
//                    Place place = review.getPlace();
//
//                    PlaceStatus placeStatus = place.getReviews().stream() //가장 최신의 리뷰를 가져옴
//                            .sorted(Comparator.comparing(Review::getReviewDate).reversed())
//                            .findFirst()
//                            .get()
//                            .getPlaceStatus();
//
//                    return UserReviewDetail.from(review.getPlace(), review, placeStatus);
//                })).toList();
//
//        return UserMyReviewsResponse.from(reviews.getTotalElements(), userReviews,hasNext);
//    }
}
