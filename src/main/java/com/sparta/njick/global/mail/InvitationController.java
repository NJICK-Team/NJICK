package com.sparta.njick.global.mail;

import com.sparta.njick.domain.taskState.common.CommonResponseDto;
import com.sparta.njick.domain.user.userDetails.UserDetailsImpl;
import com.sparta.njick.global.mail.dto.CodeValidationRequestDto;
import com.sparta.njick.global.mail.dto.SendMailRequestDTO;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/invitations")
public class InvitationController {
    private final InvitationService emailService;

    @PostMapping
    public ResponseEntity<CommonResponseDto<Object>> sendInvitingEmail(
            @RequestBody SendMailRequestDTO dto,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) throws MessagingException {
        String invitor = userDetails.getUsername();
        String email = dto.email();
        emailService.sendEmail(email, invitor, dto.workspaceId());
        return CommonResponseDto.of(HttpStatus.OK, "초대 메일이 발송되었습니다.", null);
    }

    @PostMapping("/validation")
    public ResponseEntity<CommonResponseDto<Object>> authCodeValidation(
            @RequestBody CodeValidationRequestDto dto
    ) {
        if (emailService.verifyAuthCode(dto.email(), dto.code())) {
            return CommonResponseDto.of(HttpStatus.OK, "유효한 인증코드입니다.", null);
        }

        return CommonResponseDto.of(HttpStatus.BAD_REQUEST, "유효하지 않은 인증코드입니다.", null);
    }
}
