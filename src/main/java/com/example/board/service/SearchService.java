package com.example.board.service;

import com.example.board.entity.User;
import com.example.board.mapper.UserMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SearchService {
    private final UserMapper userMapper;

    public SearchService (UserMapper userMapper){
        this.userMapper = userMapper;
    }

    public User getUserByEmail(String email){
        return userMapper.findAllByEmail(email);
    }
}
