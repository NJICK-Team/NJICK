package com.sparta.njick.domain.user.service;

import com.sparta.njick.domain.user.dto.request.NicknameUpdateRequest;
import com.sparta.njick.domain.user.dto.request.PasswordUpdateRequest;
import com.sparta.njick.domain.user.dto.request.SignUpRequest;
import com.sparta.njick.domain.user.entity.User;

public interface UserService {

    void signup(SignUpRequest request);

    void updatePassword(PasswordUpdateRequest request, User user);

    void updateNickname(NicknameUpdateRequest request, User user);

    void delete(User user);
}
