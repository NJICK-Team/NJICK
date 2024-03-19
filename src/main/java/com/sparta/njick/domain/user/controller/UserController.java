package com.sparta.njick.domain.user.controller;

import com.sparta.njick.domain.user.dto.request.SignUpRequest;
import com.sparta.njick.domain.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public void signup(@Valid @RequestBody SignUpRequest request) {
        userService.signup(request);
    }

}
