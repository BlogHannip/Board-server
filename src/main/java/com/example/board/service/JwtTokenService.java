package com.example.board.service;

import com.example.board.dto.LoginRequestDto;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;


import java.util.Date;

@Service
public class JwtTokenService {

    @Value("${jwt.secret}")
    private String secretKey; //서명에 사용할키

    @Value("${jwt.expiration}")
    private long expirationTime;

    public String createToken(String email){
        return Jwts.builder()
                .setSubject(email) //토큰 주제 ,사용자정보
                .setIssuedAt(new Date(System.currentTimeMillis())) //발행시간
                .setExpiration(new Date(System.currentTimeMillis() + 360000)) //만료시간
                .signWith(SignatureAlgorithm.HS256 ,secretKey)//서명 알고리즘과 시크릿키
                .compact(); //토큰생성
    }
    public String getEmailFromToken(String token){
        return Jwts.parserBuilder() //jwt토큰 해석부분
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject(); //사용자 이메일 추출
    }

    public boolean isTokenExpired(String token) {
        try {
            Date expiation = Jwts.parser() //만료기간
                    .setSigningKey(secretKey)
                    .parseClaimsJwt(token)
                    .getBody()
                    .getExpiration();
            return expiation.before(new Date());
        } catch (Exception e ){
            return true;
        }
    }
}
