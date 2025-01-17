package com.example.board.controller;

import com.example.board.dto.UserDto;
import com.example.board.repository.UserRepository;
import com.example.board.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api")
@CrossOrigin("http://localhost:5173")
@RestController
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/register") //@RequestBody 자바 객체로 conversion (HttpMessageConverter)
    public ResponseEntity<?> register(@Valid @RequestBody UserDto userDto ,BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bindingResult.getAllErrors());
        }

        String result = userService.register(userDto); //binding 리절트는 처리 x
        if (result.equals("회원가입 성공")){
            return ResponseEntity.status(201).body(result);
        } else {
            return ResponseEntity.status(400).body(result);
        }
    }
}
