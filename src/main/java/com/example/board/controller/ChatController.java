package com.example.board.controller;

import com.example.board.dto.ChatMessageDto;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class ChatController {


    @MessageMapping("/chat.sendMessage") // 메세시 메핑할 경로
    @SendTo("/topic/public") //보낼 엔드포인트 지정
    public ChatMessageDto sendMessage(ChatMessageDto chatMessageDto) {
        //현재 시간 추가
        chatMessageDto.setTimestamp(new SimpleDateFormat("HH:mm:ss").format(new Date()));
        return chatMessageDto;
    }
}
