package com.example.board.controller;

import com.example.board.entity.ChatRoom;
import com.example.board.service.ChatRoomService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chat")
public class ChatRoomRestController {

    private final ChatRoomService chatRoomService;

    public ChatRoomRestController(ChatRoomService chatRoomService) {
        this.chatRoomService = chatRoomService;
    }

    @PostMapping("/rooms")
    public ResponseEntity<ChatRoom> createRoom(@RequestParam String name){
        return ResponseEntity.ok(chatRoomService.createRoom(name));
    }

    @GetMapping("/rooms")
    public ResponseEntity<List<ChatRoom>> getAllRooms() {
        return ResponseEntity.ok(chatRoomService.getAllRooms());
    }

    @GetMapping("/rooms/{roomId}")
    public ResponseEntity<ChatRoom> getRoom(@PathVariable String roomId){
        return ResponseEntity.ok(chatRoomService.findByRoomId(roomId));
    }
}
