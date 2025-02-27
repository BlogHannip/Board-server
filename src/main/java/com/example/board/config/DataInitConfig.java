package com.example.board.config;

import com.example.board.DataInit.BlogDummyDataService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration //실행될때 자동실행.
public class DataInitConfig {

    @Bean
    CommandLineRunner initData(BlogDummyDataService blogDummyDataService){
        // CommnadLineRunnder: 스프링 애플리케이션 시작시 해당메서드를 실행한다.
        return args -> {
            int data =1000;
            System.out.println(data + "개 생성시작....");
            blogDummyDataService.generateDummyData(data);
            System.out.println(data + "개 생성완료");
        };
    }
}
