package com.example.board.config;

import com.example.board.service.JwtTokenProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collection;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider){
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws  ServletException,IOException {
        String token = request.getHeader("Authorization");

        if(token != null && token.startsWith("Bearer")) {
            token =token.substring(7); //Bearer 제가

            if(!jwtTokenProvider.isTokenExpired(token)){
                String email = jwtTokenProvider.getEmailFromToken(token); //토큰에서 이메일 추출


                JwtAuthenticationToken authentication = new JwtAuthenticationToken(email,null);
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);

                System.out.println("Authenticated user: " + email);
            }
        }
        filterChain.doFilter(request,response);
    }
}
