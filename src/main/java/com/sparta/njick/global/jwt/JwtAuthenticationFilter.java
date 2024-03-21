package com.sparta.njick.global.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.njick.domain.user.dto.request.SignInRequest;
import com.sparta.njick.domain.user.userDetails.UserDetailsImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Slf4j(topic = "JWT 인증")
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final JwtUtility jwtUtility;

    public JwtAuthenticationFilter(JwtUtility jwtUtility) {
        this.jwtUtility = jwtUtility;
        setFilterProcessesUrl("/api/v1/users/signin");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
        HttpServletResponse response) throws AuthenticationException {
        try {
            SignInRequest requestDto = new ObjectMapper().readValue(request.getInputStream(),
                SignInRequest.class);

            return getAuthenticationManager().authenticate(
                new UsernamePasswordAuthenticationToken(requestDto.getEmail(),
                    requestDto.getPassword(), null));
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new AuthenticationServiceException(e.getMessage());
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
        HttpServletResponse response, FilterChain chain, Authentication authResult)
        throws IOException {
        String email = ((UserDetailsImpl) authResult.getPrincipal()).getUsername();

        String token = jwtUtility.createToken(email);
        response.addHeader(JwtUtility.AUTHORIZATION_HEADER, token);
        response.setStatus(200);
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.print("{");
        out.print("\"status\": \"OK\",");
        out.print("\"message\": \"로그인 성공\"");
        out.print("}");
        out.flush();
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request,
        HttpServletResponse response, AuthenticationException failed) throws IOException {
        response.setStatus(401);
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.print("{");
        out.print("\"status\": \"UNAUTHORIZED\",");
        out.print("\"message\": \"로그인 실패\"");
        out.print("}");
        out.flush();
    }
}
