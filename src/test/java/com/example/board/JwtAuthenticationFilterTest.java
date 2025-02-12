package com.example.board.config;

import com.example.board.service.JwtTokenService;
import com.example.board.service.CustomUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.io.IOException;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class JwtAuthenticationFilterTest {

    private JwtTokenService jwtTokenService;
    private UserDetailsService userDetailsService;
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @BeforeEach
    void setUp() {
        jwtTokenService = mock(JwtTokenService.class);
        userDetailsService = mock(CustomUserDetailsService.class);
        jwtAuthenticationFilter = new JwtAuthenticationFilter(jwtTokenService, userDetailsService);
    }

    @Test
    void testJwtAuthenticationFilter_SetsSecurityContext() throws ServletException, IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        FilterChain filterChain = mock(FilterChain.class);

        String fakeToken = "fake-jwt-token";
        String email = "test@example.com";
        UserDetails mockUserDetails = mock(UserDetails.class);

        // JWT 토큰을 정상적으로 검증하도록 Mock 설정
        when(jwtTokenService.isTokenExpired(fakeToken)).thenReturn(false);
        when(jwtTokenService.getEmailFromToken(fakeToken)).thenReturn(email);
        when(userDetailsService.loadUserByUsername(email)).thenReturn(mockUserDetails);
        when(request.getCookies()).thenReturn(new jakarta.servlet.http.Cookie[]{new jakarta.servlet.http.Cookie("accessToken", fakeToken)});

        // 필터 실행
        jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);

        // SecurityContextHolder에 UserDetails가 저장되었는지 확인
        assertNotNull(SecurityContextHolder.getContext().getAuthentication());
        assertEquals(mockUserDetails, SecurityContextHolder.getContext().getAuthentication().getPrincipal());

        verify(filterChain, times(1)).doFilter(request, response);
    }
}
