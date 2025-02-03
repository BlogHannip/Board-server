package com.example.board.controller;

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
public class LogoutController {

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response){

        new SecurityContextLogoutHandler().logout(request,response,SecurityContextHolder.getContext().getAuthentication());

        ResponseCookie cookie = ResponseCookie.from("jwt","")
                .httpOnly(true)
                .secure(false) //로컬에서는 false , 배포시에는 true
                .path("/")
                .maxAge(0)
                .sameSite("Lax")
                .build();

    response.addHeader(HttpHeaders.SET_COOKIE ,cookie.toString());

        return ResponseEntity.ok("로그아웃 성공");
    }
}
