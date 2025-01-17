package com.example.board.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;


import java.util.Date;

@Service
public class JwtTokenProvider {

    @Value("${jwt.secret}")
    private String secretKey; //서명에 사용할키

    @Value("${jwt.expiration}")
    private long expirationTime;

    public String createToken(String email){
        return Jwts.builder()
                .setSubject(email) //토큰 주제 ,사용자정보
                .setIssuedAt(new Date()) //발행시간
                .setExpiration(new Date(System.currentTimeMillis())) //만료시간
                .signWith(SignatureAlgorithm.HS256 ,secretKey)//서명 알고리즘과 시크릿키
                .compact(); //토큰생성
    }
    public String getEmailFromToken(String token){
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJwt(token)
                .getBody()
                .getSubject(); //사용자 이메일 추출
    }

    public boolean isTokenExpired(String token){
        Date expiation = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJwt(token)
                .getBody()
                .getExpiration();
        return expiation.before(new Date());
    }
}
