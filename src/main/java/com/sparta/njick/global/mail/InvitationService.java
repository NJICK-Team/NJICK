package com.sparta.njick.global.mail;

import com.sparta.njick.domain.board.service.BoardService;
import com.sparta.njick.domain.board.service.dto.BoardInfoDTO;
import com.sparta.njick.domain.user.entity.User;
import com.sparta.njick.domain.user.service.UserService;
import com.sparta.njick.global.util.AuthCodeUtil;
import com.sparta.njick.global.util.RedisUtil;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

@Service
@RequiredArgsConstructor
public class InvitationService {
    private final JavaMailSender mailSender;
    private final RedisUtil redisUtil;
    private final UserService userService;
    private final BoardService boardService;

    @Value("${spring.mail.username}")
    private String configEmail;

    private String setContent(
            final String invitor,
            final String urls,
            final String workspace,
            final String code
    ) {
        Context context = new Context();
        TemplateEngine templateEngine = new TemplateEngine();
        ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();

        context.setVariable("urls", urls);
        context.setVariable("invitor", invitor);
        context.setVariable("workspace", workspace);
        context.setVariable("code", code);

        resolver.setPrefix("templates/");
        resolver.setSuffix(".html");
        resolver.setTemplateMode(TemplateMode.HTML);
        resolver.setCharacterEncoding("UTF-8");
        resolver.setCacheable(false);

        templateEngine.setTemplateResolver(resolver);
        return templateEngine.process("mail", context);
    }

    private MimeMessage createEmailForm(
            final User receiver,
            final User invitor,
            final BoardInfoDTO info
    ) throws MessagingException {
        String authCode = AuthCodeUtil.generateAuthCode(10);
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        mimeMessage.addRecipients(MimeMessage.RecipientType.TO, receiver.getEmail());
        mimeMessage.setSubject(generateSubject(receiver, invitor, info));
        mimeMessage.setFrom(configEmail);
        mimeMessage.setText(setContent(
                invitor.getNickname(),
                "http://localhost:8081/boards/" + info.id(),
                info.title(),
                authCode
        ), "UTF-8", "html");

        redisUtil.setDataExpire(receiver.getEmail(), authCode, 2 * 24 * 60 * 60L);
        return mimeMessage;
    }

    private static String generateSubject(User receiver, User invitor, BoardInfoDTO info) {
        return "[초대장] %s 님이 %s 님을 %s 워크스페이스에 초대했습니다.".formatted(
                invitor.getNickname(),
                receiver.getNickname(),
                info.title()
        );
    }

    public void sendEmail(
            final String toEmail,
            final String fromEmail,
            final Long workspaceId
    ) throws MessagingException {
        if (redisUtil.existData(toEmail)) {
            redisUtil.deleteData(toEmail);
        }

        BoardInfoDTO boardInfoDTO = boardService.searchById(workspaceId);
        User receiver = userService.searchByEmail(toEmail);
        User invitor = userService.searchByEmail(fromEmail);
        MimeMessage emailForm = createEmailForm(
                receiver,
                invitor,
                boardInfoDTO
        );

        mailSender.send(emailForm);
    }

    public boolean verifyAuthCode(
            final String email,
            final String authCode
    ) {
        String codeFromRedis = redisUtil.getData(email);

        if (codeFromRedis == null) {
            return false;
        }

        return authCode.equals(codeFromRedis);
    }
}
