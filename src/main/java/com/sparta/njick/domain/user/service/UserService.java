package com.sparta.njick.domain.user.service;

import com.sparta.njick.domain.user.dto.request.SignUpRequest;
import com.sparta.njick.domain.user.entity.User;
import com.sparta.njick.domain.user.repository.UserRepository;
import jakarta.persistence.EntityExistsException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void signup(SignUpRequest request) {
        String email = request.getEmail();
        String password = passwordEncoder.encode(request.getPassword());
        String nickname = request.getNickname();

        validateEmailDuplicate(request.getEmail());
        validateNicknameDuplicate(request.getNickname());

        userRepository.save(User.builder()
            .email(email)
            .password(password)
            .nickname(nickname)
            .build());
    }

    private void validateEmailDuplicate(String request) {
        if (userRepository.existsByEmail(request)) {
            throw new EntityExistsException("중복된 사용자가 존재합니다.");
        }
    }

    private void validateNicknameDuplicate(String request) {
        if (userRepository.existsByNickname(request)) {
            throw new EntityExistsException("중복된 닉네임이 존재합니다.");
        }
    }

}
