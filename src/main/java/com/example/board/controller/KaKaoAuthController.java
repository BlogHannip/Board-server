package com.example.board.controller;

import com.example.board.dto.KakaoUserInfoDto;
import com.example.board.service.KaKaoAuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/oauth/kakao")
public class KaKaoAuthController {

    private final KaKaoAuthService kaKaoAuthService;

    public KaKaoAuthController(KaKaoAuthService kaKaoAuthService){
        this.kaKaoAuthService = kaKaoAuthService;
    }

    //프론트에서 카카오 로그인 버튼 클릭시 , 카카오 로그인 페이지 로 다이렉트
    @GetMapping("/login")
    public ResponseEntity<?> redirectToKakaoLogin() {
        String kakaoAuthUrl = "https://kauth.kakao.com/oauth/authorize" +
                "?client_id=6a641f762b414c6a158a851863141608" +
                "&redirect_uri=http://localhost:8080/oauth/kakao/callback" +
                "&response_type=code";

        return ResponseEntity.ok(kakaoAuthUrl);
    }

    // 카카오에서 인가 코드를 받은 후, 백엔드가 엑세스 토큰 요청
    @GetMapping("/callback")
    public ResponseEntity<?> kakaoLogin(@RequestParam String code, HttpServletResponse response){
        System.out.println("카카오 인가 코드:" +code);

        //카카오에서 엑세스 토큰
        String accessToken = kaKaoAuthService.getKakaoAccessToken(code);
        System.out.println("카카오 엑세스토큰:" +accessToken);

        //카카오 사용자 정보 가져오기
        KakaoUserInfoDto kakaoUserInfoDto = kaKaoAuthService.getKakaoUserInfo(accessToken);
        System.out.println("카카오 엑세스 토큰:" + kakaoUserInfoDto);

        //로그인 또는 회원가입 처리후 JWT 발급
        String jwtToken = kaKaoAuthService.loginOrResister(kakaoUserInfoDto);

        Cookie jwtCookie = new Cookie("accessToken" , jwtToken);
        jwtCookie.setHttpOnly(true);
        jwtCookie.setPath("/");
        jwtCookie.setMaxAge(7*24*60*60);
        response.addCookie(jwtCookie);

        return ResponseEntity.ok("카카오 로그인 성공");
    }
}
