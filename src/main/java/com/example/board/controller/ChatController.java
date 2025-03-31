package com.example.board.controller;

import com.example.board.dto.ChatMessageDto;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.messaging.simp.SimpMessagingTemplate;


import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class ChatController {

    private final SimpMessagingTemplate messagingTemplate;

    public ChatController(SimpMessagingTemplate simpMessagingTemplate) {
        this.messagingTemplate = simpMessagingTemplate;
    }

    @MessageMapping("/chat/{roomId}/send") // 메세시 메핑할 경로
 //   @SendTo("/topic/public/rooms/{roomId}") //보낼 엔드포인트 지정 03.24일 채팅방으로 구현하면서 send 정적메서드 삭제
    public void sendMessage(@DestinationVariable String roomId, ChatMessageDto chatMessageDto) {
        //현재 시간 추가
        chatMessageDto.setTimestamp(new SimpleDateFormat("HH:mm:ss").format(new Date()));
        messagingTemplate.convertAndSend("/topic/public/rooms/" + roomId, chatMessageDto);
    }
}
