package com.example.board.controller;

import com.example.board.dto.LoginRequestDto;
import com.example.board.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
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

        String loginResponse = loginService.login(loginRequestDto);

        if (loginResponse.startsWith("로그인 실패")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(loginResponse); // 로그인 실패 시 메시지 반환
        } else {
            // 로그인 성공 시 JWT 토큰을 헤더에 포함시켜 반환
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + loginResponse); // JWT 토큰을 Authorization 헤더에 포함

            return ResponseEntity.ok().headers(headers).body("로그인 성공!");
        }
    }
}
