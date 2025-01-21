package com.example.board.controller;

import com.example.board.dto.LoginRequestDto;
import com.example.board.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:5173")
public class LoginController {
    private final LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    //로그인 페이지 처리
    @GetMapping("/login")
    public  String login() {
        return "login"; //로그인 성공후 필요한 데이터 반환해주는거 필요
    }

    //요청 처리 post
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto loginRequestDto){
        System.out.println("Post /api/login 호출돰");
        System.out.println("요청 데이터: "+loginRequestDto);
        String loginResponse = loginService.login(loginRequestDto);

        if(loginResponse.equals("로그인 성공")){
            System.out.println("로그인 성공");
            return ResponseEntity.ok("로그인 성공!");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인 실패:" +loginResponse);
        }
    }

    @GetMapping("/login/error")
    public String loginError(Model model) {
        model.addAttribute("loginError",true);
        return "login"; //실패한거 에러메시지 loginService에서 어떻게 가져올지 얘기 해봐야할듯
    }
}
