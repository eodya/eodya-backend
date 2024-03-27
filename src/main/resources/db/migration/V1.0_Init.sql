CREATE TABLE `users`
(
    `id`             Long         NOT NULL,
    `nickname`       varchar(100) NOT NULL,
    `oauth_id`       varchar(400) NULL,
    `oauth_provider` varchar(100) NOT NULL,
    `created_at`     DateTime     NOT NULL,
    `updated_at`     DateTime     NOT NULL
);

CREATE TABLE `place`
(
    `id`                Long         NOT NULL,
    `user_id`           Long         NULL,
    `address_depth1_id` Long         NOT NULL,
    `address_depth2_id` Long         NOT NULL,
    `point`             POINT        NOT NULL,
    `name`              varchar(255) NOT NULL,
    `image`             varchar(400) NOT NULL,
    `address_detail`    varchar(255) NOT NULL,
    `recommend_count`   INT          NOT NULL,
    `bookmark_count`    INT          NOT NULL,
    `created_at`        DateTime     NOT NULL,
    `updated_at`        DateTime     NOT NULL
);

CREATE TABLE `address_depth1`
(
    `id`         Long         NOT NULL,
    `name`       varchar(100) NOT NULL,
    `created_at` DateTime     NOT NULL,
    `updated_at` DateTime     NOT NULL
);

CREATE TABLE `recommendation`
(
    `id`         Long        NOT NULL,
    `user_id`    Long        NOT NULL,
    `place_id`   Long        NOT NULL,
    `status`     varchar(20) NOT NULL,
    `created_at` DateTime    NOT NULL,
    `updated_at` DateTime    NOT NULL
);

CREATE TABLE `bookmark`
(
    `id`         Long     NOT NULL,
    `place_id`   Long     NOT NULL,
    `user_id`    Long     NOT NULL,
    `status`     Boolean  NOT NULL,
    `created_at` DateTime NOT NULL,
    `updated_at` DateTime NOT NULL
);

CREATE TABLE `review`
(
    `id`             Long          NOT NULL,
    `place_id`       Long          NOT NULL,
    `user_id`        Long          NOT NULL,
    `review_date`    DateTime      NOT NULL,
    `place_status`   varchar(100)  NOT NULL,
    `image`          varchar(400)  NULL,
    `review_content` varchar(1000) NOT NULL,
    `created_at`     DateTime      NOT NULL,
    `updated_at`     DateTime      NOT NULL
);

CREATE TABLE `place_tag`
(
    `id`         Long         NOT NULL,
    `place_id`   Long         NOT NULL,
    `name`       varchar(100) NOT NULL,
    `created_at` DateTime     NOT NULL,
    `updated_at` DateTime     NOT NULL
);

CREATE TABLE `address_depth2`
(
    `id`                Long         NOT NULL,
    `address_depth1_id` Long         NOT NULL,
    `name`              varchar(100) NOT NULL,
    `created_at`        DateTime     NOT NULL,
    `updated_at`        DateTime     NOT NULL
);

ALTER TABLE `users`
    ADD CONSTRAINT `PK_USERS` PRIMARY KEY (
                                           `id`
        );

ALTER TABLE `place`
    ADD CONSTRAINT `PK_PLACE` PRIMARY KEY (
                                           `id`,
                                           `user_id`,
                                           `address_depth1_id`,
                                           `address_depth2_id`
        );

ALTER TABLE `address_depth1`
    ADD CONSTRAINT `PK_ADDRESS_DEPTH1` PRIMARY KEY (
                                                    `id`
        );

ALTER TABLE `recommendation`
    ADD CONSTRAINT `PK_RECOMMENDATION` PRIMARY KEY (
                                                    `id`,
                                                    `user_id`,
                                                    `place_id`
        );

ALTER TABLE `bookmark`
    ADD CONSTRAINT `PK_BOOKMARK` PRIMARY KEY (
                                              `id`,
                                              `place_id`,
                                              `user_id`
        );

ALTER TABLE `review`
    ADD CONSTRAINT `PK_REVIEW` PRIMARY KEY (
                                            `id`,
                                            `place_id`,
                                            `user_id`
        );

ALTER TABLE `place_tag`
    ADD CONSTRAINT `PK_PLACE_TAG` PRIMARY KEY (
                                               `id`,
                                               `place_id`
        );

ALTER TABLE `address_depth2`
    ADD CONSTRAINT `PK_ADDRESS_DEPTH2` PRIMARY KEY (
                                                    `id`,
                                                    `address_depth1_id`
        );

ALTER TABLE `place`
    ADD CONSTRAINT `FK_users_TO_place_1` FOREIGN KEY (
                                                      `user_id`
        )
        REFERENCES `users` (
                            `id`
            );

ALTER TABLE `place`
    ADD CONSTRAINT `FK_address_depth2_TO_place_1` FOREIGN KEY (
                                                               `address_depth1_id`
        )
        REFERENCES `address_depth2` (
                                     `address_depth1_id`
            );

ALTER TABLE `place`
    ADD CONSTRAINT `FK_address_depth2_TO_place_2` FOREIGN KEY (
                                                               `address_depth2_id`
        )
        REFERENCES `address_depth2` (
                                     `id`
            );

ALTER TABLE `recommendation`
    ADD CONSTRAINT `FK_users_TO_recommendation_1` FOREIGN KEY (
                                                               `user_id`
        )
        REFERENCES `users` (
                            `id`
            );

ALTER TABLE `recommendation`
    ADD CONSTRAINT `FK_place_TO_recommendation_1` FOREIGN KEY (
                                                               `place_id`
        )
        REFERENCES `place` (
                            `id`
            );

ALTER TABLE `bookmark`
    ADD CONSTRAINT `FK_place_TO_bookmark_1` FOREIGN KEY (
                                                         `place_id`
        )
        REFERENCES `place` (
                            `id`
            );

ALTER TABLE `bookmark`
    ADD CONSTRAINT `FK_users_TO_bookmark_1` FOREIGN KEY (
                                                         `user_id`
        )
        REFERENCES `users` (
                            `id`
            );

ALTER TABLE `review`
    ADD CONSTRAINT `FK_place_TO_review_1` FOREIGN KEY (
                                                       `place_id`
        )
        REFERENCES `place` (
                            `id`
            );

ALTER TABLE `review`
    ADD CONSTRAINT `FK_users_TO_review_1` FOREIGN KEY (
                                                       `user_id`
        )
        REFERENCES `users` (
                            `id`
            );

ALTER TABLE `place_tag`
    ADD CONSTRAINT `FK_place_TO_place_tag_1` FOREIGN KEY (
                                                          `place_id`
        )
        REFERENCES `place` (
                            `id`
            );

ALTER TABLE `address_depth2`
    ADD CONSTRAINT `FK_address_depth1_TO_address_depth2_1` FOREIGN KEY (
                                                                        `address_depth1_id`
        )
        REFERENCES `address_depth1` (
                                     `id`
            );
