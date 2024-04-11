package com.eodya.api.users.service;

import static com.eodya.api.users.exception.UserExceptionCode.*;

import com.eodya.api.auth.domain.config.property.JwtProperties;
import com.eodya.api.auth.domain.token.AuthTokens;
import com.eodya.api.auth.domain.token.JwtProvider;
import com.eodya.api.auth.domain.token.RefreshToken;
import com.eodya.api.auth.repository.RedisRepository;
import com.eodya.api.auth.service.OauthService;
import com.eodya.api.users.domain.User;
import com.eodya.api.users.dto.request.AuthenticatedUserResponse;
import com.eodya.api.users.dto.request.UserCreateRequest;
import com.eodya.api.users.exception.UserException;
import com.eodya.api.users.repository.UserRepository;

import java.time.LocalDateTime;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private static final String REFRESH_TOKEN_KEY = "refresh_token";

    private final UserRepository userRepository;
    private final RedisRepository redisRepository;
    private final JwtProvider jwtProvider;
    private final JwtProperties jwtProperties;

    public AuthenticatedUserResponse createMember(UserCreateRequest userCreateRequest) {
        validateIsDuplicatedUserInfo(userCreateRequest);

        User user = userCreateRequest.toEntity();
        User savedUser = userRepository.save(user);

        AuthTokens loginTokens = jwtProvider.createLoginToken(String.valueOf(savedUser.getId()));

        RefreshToken refreshToken = RefreshToken.builder()
                .token(loginTokens.getRefreshToken())
                .userId(savedUser.getId())
                .createdAt(LocalDateTime.now())
                .build();

        redisRepository.saveHash(
                REFRESH_TOKEN_KEY,
                refreshToken.getToken(),
                refreshToken,
                jwtProperties.getRefreshTokenExpirationTime()
        );

        return AuthenticatedUserResponse.of(savedUser, loginTokens);
    }

    private void validateIsDuplicatedUserInfo(UserCreateRequest memberCreateRequest) {
        final String nickname = memberCreateRequest.getNickname();
        final Long oauthId = memberCreateRequest.getOauthId();

        if (userRepository.existsByNicknameOrOauthId(nickname, oauthId)) {
            throw new UserException(USER_IS_EXISTED, nickname, oauthId);
        }
    }

//
//    private final UserRepository userRepository;
//    private final SocialService socialService;
//    private final JwtTokenManager jwtTokenManager;
//    private final BookmarkRepository bookmarkRepository;
//    private final ReviewRepository reviewRepository;
//    private final String SERVICE_NAME = "어댜";
//
//    @Transactional
//    public UserLoginResponse login(String token) {
//       Long oauthId = Long.valueOf(socialService.getOAuthId(token));
//       Optional<User> findUser = userRepository.findByOauthId(oauthId);
//
//        User user = findUser.orElseGet(() -> {
//            User newUser = User.builder()
//                    .nickname(SERVICE_NAME)
//                    .oauthId(oauthId)
//                    .oauthProvider(OauthProvider.KAKAO)
//                    .build();
//            userRepository.save(newUser);
//            newUser.changeNickName(SERVICE_NAME+newUser.getId());
//            return newUser;
//        });
//
//       String accessToken = jwtTokenManager.createAccessToken(user.getId());
//
//       return UserLoginResponse.builder()
//               .token(accessToken)
//               .userId(user.getId())
//               .nickname(user.getNickname())
//               .build();
//    }
//
//    @Transactional
//    public void updateNickName(Long userId, String nickName) {
//        User user = userRepository.getUserById(userId);
//        userRepository.findByNickname(nickName)
//                .ifPresent(e -> {
//                    throw new UserException(ALREADY_EXIST_NICKNAME);
//                });
//
//        user.changeNickName(nickName);
//        userRepository.save(user);
//    }
//
//    public UserInfoResponse getMyInfo(Long userId) {
//        User user = userRepository.getUserById(userId);
//        return UserInfoResponse.from(user);
//    }

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
