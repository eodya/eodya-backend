package com.eodya.api.users.service;

import com.eodya.api.users.config.JwtTokenManager;
import com.eodya.api.users.domain.OAuthProvider;
import com.eodya.api.users.domain.User;
import com.eodya.api.users.dto.response.UserLoginResponse;
import com.eodya.api.users.repository.UserRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final SocialService socialService;
    private final JwtTokenManager jwtTokenManager;

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

}
