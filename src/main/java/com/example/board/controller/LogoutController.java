package com.example.board.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Tag(name = "로그아웃" , description = "사용자 로그아웃")
public class LogoutController {

    @PostMapping("/logout")
    @Operation(summary = "로그아웃 창", description = "사용자 토큰을 제거하고 로그아웃을실행한다.")
    public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response){

        new SecurityContextLogoutHandler().logout(request,response,SecurityContextHolder.getContext().getAuthentication());

        ResponseCookie accessCookie = ResponseCookie.from("accessToken","")
                .httpOnly(true)
                .secure(false) //로컬에서는 false , 배포시에는 true
                .path("/")
                .maxAge(0)
                .sameSite("Lax")
                .build();

        ResponseCookie refreshCookie = ResponseCookie.from("refreshToken","")
                .httpOnly(true)
                .secure(false) //로컬에서는 false , 배포시에는 true
                .path("/")
                .maxAge(0)
                .sameSite("Lax")
                .build();

        response.addHeader(HttpHeaders.SET_COOKIE ,accessCookie.toString());
        response.addHeader(HttpHeaders.SET_COOKIE ,refreshCookie.toString());

        return ResponseEntity.ok("로그아웃 성공");
    }
}
