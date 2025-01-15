package com.example.board.controller;

import com.example.board.dto.UserDto;
import com.example.board.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api")
@CrossOrigin("http://localhost:5173")
@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/register")
    public String register(@Valid UserDto userDto, BindingResult bindingResult , Model model){
        // UserDto 객체에 따라 유효성 검사후 결과값을 BindingResult에 저장
        //model은 result의 값을 담아서 뷰 부분에 전달
        String result =userService.register(userDto,bindingResult);
        model.addAttribute("message",result);
        return "registarionResult"; //src/main/resources/templates/registrationResult.html
    }
}
