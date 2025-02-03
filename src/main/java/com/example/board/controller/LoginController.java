package com.example.board.controller;

import com.example.board.dto.LoginRequestDto;
import com.example.board.dto.LoginResponseDto;
import com.example.board.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:5173")
public class LoginController {

    private final LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    // 로그인 요청 처리
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto loginRequestDto) {
        System.out.println("Post /api/login 호출");
        System.out.println("요청 데이터: " + loginRequestDto);

        String jwtToken = loginService.login(loginRequestDto);

        if (jwtToken.startsWith("로그인 실패")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new LoginResponseDto(null, jwtToken)); // 로그인 실패 시 메시지 반환
        } else {
            // 로그인 성공 시 JWT 토큰을 헤더에 포함시켜 반환
            ResponseCookie cookie = ResponseCookie.from("jwt",jwtToken)
                    .httpOnly(true) //js에서 접근 불가능
                    .secure(false) //Https에서만 전송 , 지금은 로컬이라 false
                    .path("/") //전체 도메인에서 접근
                    .maxAge(60*60) // 쿠키 유효시간 설정
                    .sameSite("Lax") // Cors문제 방지
                    .build();

            LoginResponseDto responseDto = new LoginResponseDto(loginRequestDto.getEmail(),"로그인 성공");

            return ResponseEntity.ok()
                    .header("Set-Cookie",cookie.toString()) //쿠키에 응답 추가
                    .body(responseDto);
        }
    }
}
