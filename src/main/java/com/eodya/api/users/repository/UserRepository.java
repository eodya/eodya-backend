package com.eodya.api.users.repository;

import com.eodya.api.users.domain.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByOAuthId(String oauthId);
}
