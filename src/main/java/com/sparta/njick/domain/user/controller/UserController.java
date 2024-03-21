package com.sparta.njick.domain.user.controller;

import com.sparta.njick.domain.taskState.common.CommonResponseDto;
import com.sparta.njick.domain.user.dto.request.NicknameUpdateRequest;
import com.sparta.njick.domain.user.dto.request.PasswordUpdateRequest;
import com.sparta.njick.domain.user.dto.request.SignUpRequest;
import com.sparta.njick.domain.user.service.UserService;
import com.sparta.njick.domain.user.userDetails.UserDetailsImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<CommonResponseDto<Void>> signup(
        @Valid @RequestBody SignUpRequest request) {
        userService.signup(request);
        return CommonResponseDto.of(HttpStatus.OK, "회원가입 성공", null);
    }

    @PostMapping("/logout")
    public ResponseEntity<CommonResponseDto<Void>> logout(HttpServletRequest request,
        HttpServletResponse response) {
        new SecurityContextLogoutHandler().logout(request, response,
            SecurityContextHolder.getContext().getAuthentication());
        return CommonResponseDto.of(HttpStatus.OK, "로그아웃 성공", null);
    }

    @PatchMapping("/updates/password")
    public ResponseEntity<CommonResponseDto<Void>> updatePassword(
        @Valid @RequestBody PasswordUpdateRequest request,
        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        userService.updatePassword(request, userDetails.getUser());
        return CommonResponseDto.of(HttpStatus.OK, "비밀번호 변경 성공", null);
    }

    @PatchMapping("/updates/nickname")
    public ResponseEntity<CommonResponseDto<Void>> updateNickname(
        @Valid @RequestBody NicknameUpdateRequest request,
        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        userService.updateNickname(request, userDetails.getUser());
        return CommonResponseDto.of(HttpStatus.OK, "닉네임 변경 성공", null);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<CommonResponseDto<Void>> delete(
        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        userService.delete(userDetails.getUser());
        return CommonResponseDto.of(HttpStatus.OK, "회원탈퇴 성공", null);
    }
}
