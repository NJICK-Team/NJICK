package com.sparta.njick.domain.user.controller;

import com.sparta.njick.domain.user.dto.request.NicknameUpdateRequest;
import com.sparta.njick.domain.user.dto.request.PasswordUpdateRequest;
import com.sparta.njick.domain.user.dto.request.SignUpRequest;
import com.sparta.njick.domain.user.service.UserService;
import com.sparta.njick.domain.user.userDetails.UserDetailsImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
    public void signup(@Valid @RequestBody SignUpRequest request) {
        userService.signup(request);
    }

    @PostMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        new SecurityContextLogoutHandler().logout(request, response,
            SecurityContextHolder.getContext().getAuthentication());
    }

    @PatchMapping("/updates/password")
    public void updatePassword(@Valid @RequestBody PasswordUpdateRequest request,
        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        userService.updatePassword(request, userDetails.getUser());
    }

    @PatchMapping("/updates/nickname")
    public void updateNickname(@Valid @RequestBody NicknameUpdateRequest request,
        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        userService.updateNickname(request, userDetails.getUser());
    }

    @DeleteMapping("/delete")
    public void delete(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        userService.delete(userDetails.getUser());
    }
}
