package com.example.board.service;

import com.example.board.dto.LoginRequestDto;
import com.example.board.entity.User;
import com.example.board.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenService jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public LoginService(UserRepository userRepository, PasswordEncoder passwordEncoder,
                        AuthenticationManager authenticationManager, JwtTokenService jwtTokenProvider) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public String login(LoginRequestDto loginRequestDto) {
        String email = loginRequestDto.getEmail();

        if (email == null) {
            throw new IllegalArgumentException("email is required");
        }
        email = email.trim();

        // 유저 조회
        Optional<User> optionalUser = userRepository.findByEmail(email);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            System.out.println("Received email: " + loginRequestDto.getEmail());
            System.out.println("Found user email: " + user.getEmail());

            if (passwordEncoder.matches(loginRequestDto.getPassword(), user.getPassword())) {

                // 인증되지 않은 사용자인 경우, AuthenticationManager를 통해 인증 절차 수행
                if (SecurityContextHolder.getContext().getAuthentication() == null) {
                    UsernamePasswordAuthenticationToken authenticationToken =
                            new UsernamePasswordAuthenticationToken(user.getEmail(), loginRequestDto.getPassword());
                    Authentication authentication = authenticationManager.authenticate(authenticationToken);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }

                // JWT 토큰 생성
                return jwtTokenProvider.createToken(user.getEmail());
            } else {
                return "로그인 실패: 비밀번호가 일치하지 않습니다.";
            }
        } else {
            return "로그인 실패: 이메일을 찾을 수 없습니다.";
        }
    }
}
