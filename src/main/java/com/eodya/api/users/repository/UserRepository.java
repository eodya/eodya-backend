package com.eodya.api.users.repository;

import com.eodya.api.users.domain.User;
import com.eodya.api.users.exception.UserException;
import org.springframework.data.jpa.repository.JpaRepository;

import static com.eodya.api.users.exception.UserExceptionCode.USER_NOT_FOUND;

public interface UserRepository extends JpaRepository<User, Long> {

    default User getUserById(Long userId) {
        return findById(userId).orElseThrow(() -> new UserException(USER_NOT_FOUND, userId));
    }
}
