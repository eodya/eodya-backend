package com.eodya.api.common.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(
                title = "여다 API 명세서",
                description = "여다 API 명세서입니다.",
                version = "v1"
        )
)

@Configuration
public class SwaggerConfig {


        @Bean
        public GroupedOpenApi userApi() {
                String[] paths = {"/api/v1/user/**"};
                return GroupedOpenApi.builder()
                        .group("유저(User) API")
                        .pathsToMatch(paths)
                        .build();
        }

        @Bean
        public GroupedOpenApi bookmarkApi() {
                String[] paths = {"/api/v1/bookmark/**"};
                return GroupedOpenApi.builder()
                        .group("북마크 API")
                        .pathsToMatch(paths)
                        .build();
        }

        @Bean
        public GroupedOpenApi postApi() {
                String[] paths = {"/api/v1/post/**"};
                return GroupedOpenApi.builder()
                        .group("장소(스팟) API")
                        .pathsToMatch(paths)
                        .build();
        }

        @Bean
        public GroupedOpenApi reviewApi() {
                String[] paths = {"/api/v1/review/**"};
                return GroupedOpenApi.builder()
                        .group("리뷰(후기) API")
                        .pathsToMatch(paths)
                        .build();
        }

        @Bean
        public GroupedOpenApi recommendationApi() {
                String[] paths = {"/api/v1/recommendation/**"};
                return GroupedOpenApi.builder()
                        .group("추천 API")
                        .pathsToMatch(paths)
                        .build();
        }
}
