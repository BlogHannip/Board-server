package com.example.board.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry){
        registry.addMapping("/api/**") //허용할 경로
                .allowedOrigins("http://localhost:5173") //허용할 클라이언트 주소
                .allowedMethods("GET","POST","PUT","DELETE","OPTIONS" ,"PATCH") //허용할 메소드,
                .allowedHeaders("Authorization","Content-Type","Cookie")
                .allowCredentials(true); //쿠키허용
    }
}
