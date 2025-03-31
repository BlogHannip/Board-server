package com.example.board.service;

import com.example.board.entity.ChatRoom;
import com.example.board.repository.ChatRoomRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;

    public ChatRoomService(ChatRoomRepository chatRoomRepository) {
        this.chatRoomRepository =chatRoomRepository;
    }

    public ChatRoom createRoom(String name) {
        ChatRoom room = new ChatRoom();
        room.setRoomId(UUID.randomUUID().toString()); //UUID 랜덤형식 배정
        room.setName(name);
        return chatRoomRepository.save(room);
    }

    public List<ChatRoom> getAllRooms() {
        return chatRoomRepository.findAll();
    }

    public ChatRoom findByRoomId(String roodId) {
        return chatRoomRepository.findByRoomId(roodId)
                .orElseThrow(()-> new IllegalArgumentException("채팅방이없습니다."));
    }

}
