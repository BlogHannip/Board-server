package com.example.board.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        //클라이언트에서 구독할 prefix
        registry.enableSimpleBroker("/topic");
        //클라이언트에서 메세지를 보낼 prefix (경로)
        registry.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry1) {
        //WebSocket 엔드포인트 설정(클라이언트가 연결할 경로)
        registry1
                .addEndpoint("/ws/chat")
                .setAllowedOriginPatterns("*"); //특정 도메인만 허용

    }

}
