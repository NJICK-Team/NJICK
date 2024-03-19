package com.sparta.njick.domain.user.repository;

import com.sparta.njick.domain.user.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String request);

    boolean existsByNickname(String request);
}
