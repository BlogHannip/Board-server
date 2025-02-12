package com.example.board.config;

import com.example.board.service.JwtTokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenService jwtTokenProvider;

    @Autowired
    public JwtAuthenticationFilter(JwtTokenService jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = null;

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("accessToken")) {
                    token = cookie.getValue(); //엑세스 토큰이있을경우 설정
                    break;
                }
            }
        }

        if (token != null && !jwtTokenProvider.isTokenExpired(token)) { //만료가아니고 토큰이있다면
            String email = jwtTokenProvider.getEmailFromToken(token); //토큰에서 이메일 추출


            JwtAuthenticationToken authentication = new JwtAuthenticationToken(email, null);
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            //인증될경우 SecurityContextHolder에 email을 저장하라

            System.out.println("Authenticated user: " + email);

            System.out.println("SecurityContext 인증 객체 :" +SecurityContextHolder.getContext().getAuthentication());
        }

        filterChain.doFilter(request,response);
   }
}
