package com.example.board.service;

import com.example.board.dto.LoginRequestDto;
import com.example.board.dto.LoginResponseDto;
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

    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        String email = loginRequestDto.getEmail(); //loginRequstDto에서 받아오기

        if (email == null) { //없으면 에러 던져서 이메일 요청
            throw new IllegalArgumentException("email is required");
        }
        email = email.trim(); //이메일 앞뒤 공백 자르기

        // 유저 조회
        Optional<User> optionalUser = userRepository.findByEmail(email); //레포지토리의 find로 데이터베이스에서 이메일로 유저찾기
       //여기서 Optional은 조회 결과가 없는경우 null 대신 비어있는 값을 표현
        if (optionalUser.isPresent()) { // 유저가 존재할경우
            User user = optionalUser.get(); //Optional에 값이 있을경우에 실제 User 객체를 반환. (단, 값이 존재한다고 보장된 상황만).
            System.out.println("Received email: " + loginRequestDto.getEmail());
            System.out.println("Found user email: " + user.getEmail());

            if (passwordEncoder.matches(loginRequestDto.getPassword(), user.getPassword())) {

                // 인증되지 않은 사용자인 경우, AuthenticationManager 통해 인증 절차 수행
                if (SecurityContextHolder.getContext().getAuthentication() == null) {
                    UsernamePasswordAuthenticationToken authenticationToken =
                            new UsernamePasswordAuthenticationToken(user.getEmail(), loginRequestDto.getPassword());
                    Authentication authentication = authenticationManager.authenticate(authenticationToken);
                    // SecurityContext 안에있는 Authentication 이 토큰의 유효성을 검사.
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    //SecurityContextHolder를 통해 사용자의 인증사항들을 수정
                }

                String accessToken = jwtTokenProvider.createAccessToken(user.getEmail());
                String refreshToken = jwtTokenProvider.createRefreshToken(user.getEmail());

                // JWT 토큰 생성
                return new LoginResponseDto(accessToken,refreshToken,"로그인성공",user.getEmail());
            } else {
                return new LoginResponseDto(null,null,"로그인실패:비밀번호가 틀림",null);
            }
        } else {
            return new LoginResponseDto(null,null,"로그인실패: 이메일을 찾을수없음",null);
        }
    }
}
