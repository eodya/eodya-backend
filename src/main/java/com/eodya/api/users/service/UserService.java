package com.eodya.api.users.service;

import static com.eodya.api.users.exception.UserExceptionCode.ALREADY_EXIST_NICKNAME;

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

    @Transactional
    public UserLoginResponse login(String token) {
       String oauthId = String.valueOf(socialService.getOAuthId(token));
       Optional<User> findUser = userRepository.findByOAuthId(oauthId);

        User user = findUser.orElseGet(() -> {
            User newUser = User.builder()
                    .nickname("어댜") //todo - name maker 클래스 만들기
                    .OAuthId(oauthId)
                    .OAuthProvider(OAuthProvider.KAKAO)
                    .build();
            return userRepository.save(newUser);
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

        user.setUserNickName(nickName);
        userRepository.save(user);
    }

    public UserInfoResponse getMyInfo(Long userId) {
        User user = userRepository.getUserById(userId);
        return UserInfoResponse.from(user);
    }

}
