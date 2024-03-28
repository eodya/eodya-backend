package com.eodya.api.users.config;

import static com.eodya.api.users.exception.AuthExceptionCode.INVALID_TOKEN;

import com.eodya.api.users.exception.AuthException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@RequiredArgsConstructor
@Component
public class LoginResolver implements HandlerMethodArgumentResolver {

    private final JwtTokenManager jwtTokenManager;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(Login.class) && Long.class.equals(parameter.getParameterType());
    }

    @Override
    public Object resolveArgument(@NotNull MethodParameter parameter, ModelAndViewContainer modelAndViewContainer, @NotNull NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        final HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        final String token = request.getHeader("Authorization");

        // 토큰 검증
        if (!jwtTokenManager.verifyAuthToken(token)) {
            throw new AuthException(INVALID_TOKEN);
        }

        // 유저 아이디 반환
        final String tokenContents = jwtTokenManager.getUserIdFromAuthToken(token);
        try {
            return Long.parseLong(tokenContents);
        } catch (NumberFormatException e) {
            throw new AuthException(INVALID_TOKEN);
        }
    }
}