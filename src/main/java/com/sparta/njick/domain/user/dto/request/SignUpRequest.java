package com.sparta.njick.domain.user.dto.request;

import com.sparta.njick.global.jpa.BaseAuditing;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class SignUpRequest extends BaseAuditing {

    @NotBlank
    @Email(message = "이메일 형식을 지켜주세요.")
    private String email;

    @NotBlank
    @Pattern(
        regexp = "^(?=.*[a-zA-Z])(?=.*\\W)(?=.*\\d).{8,15}$",
        message = "비밀번호는 최소 8자 이상, 15자 이하로 알파벳과 특수문자, 숫자로 구성되어야 합니다."
    )
    private String password;

    @NotBlank
    @Pattern(
        regexp = "^(?!\\d+$)[a-zA-Z가-힣\\d]{2,10}$",
        message = "닉네임은 2~10자로 구성되어야 하며, 숫자로만 구성될 수 없습니다."
    )
    private String nickname;


}
