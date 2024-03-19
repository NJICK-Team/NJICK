package com.sparta.njick.domain.user.service;

import com.sparta.njick.domain.user.dto.request.NicknameUpdateRequest;
import com.sparta.njick.domain.user.dto.request.PasswordUpdateRequest;
import com.sparta.njick.domain.user.dto.request.SignUpRequest;
import com.sparta.njick.domain.user.entity.User;
import com.sparta.njick.domain.user.repository.UserRepository;
import jakarta.persistence.EntityExistsException;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void signup(SignUpRequest request) {
        String email = request.getEmail();
        String password = passwordEncoder.encode(request.getPassword());
        String nickname = request.getNickname();

        validateEmailDuplicate(email);
        validateNicknameDuplicate(nickname);

        userRepository.save(User.builder()
            .email(email)
            .password(password)
            .nickname(nickname)
            .build());
    }

    public void updatePassword(PasswordUpdateRequest request, User user) {
        User target = validateUser(user);
        String oldPassword = target.getPassword();
        String newPassword = request.getNewPassword();

        validateNewPassword(newPassword, oldPassword);

        String encodedNewPassword = passwordEncoder.encode(newPassword);
        target.setPassword(encodedNewPassword);
    }

    public void updateNickname(NicknameUpdateRequest request, User user) {
        User target = validateUser(user);
        String newNickname = request.getNewNickname();

        validateNicknameDuplicate(newNickname);

        target.setNickname(newNickname);
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

    private User validateUser(User user) {
        return userRepository.findById(user.getId())
            .orElseThrow(() -> new NoSuchElementException("해당 사용자가 존재하지 않습니다."));
    }

    private void validateNewPassword(String newPassword, String oldPassword) {
        if (passwordEncoder.matches(newPassword, oldPassword)) {
            throw new IllegalArgumentException("현재 비밀번호와 동일합니다.");
        }
    }


}
