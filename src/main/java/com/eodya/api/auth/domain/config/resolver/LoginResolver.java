package com.eodya.api.auth.domain.config.resolver;

import static com.eodya.api.users.exception.AuthExceptionCode.INVALID_TOKEN;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

import com.eodya.api.auth.domain.token.JwtProvider;
import com.eodya.api.users.exception.AuthException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

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

    private final JwtProvider jwtProvider;
    private final TokenExtractor tokenExtractor;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.withContainingClass(Long.class)
                .hasParameterAnnotation(Login.class);
    }

    @Override
    public Long resolveArgument(
            MethodParameter parameter,
            ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest,
            WebDataBinderFactory binderFactory
    ) {
        String accessToken = tokenExtractor.extractAccessToken(webRequest.getHeader(AUTHORIZATION));
        jwtProvider.validateAccessToken(accessToken);

        return Long.parseLong(jwtProvider.getSubject(accessToken));
    }
}