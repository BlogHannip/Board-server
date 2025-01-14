package com.example.board.controller;

import com.example.board.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    private final LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping("/login")
    public String login() {
        return "login"; //로그인 성공후 필요한 데이터 반환해주는거 필요
    }

    @GetMapping("/login/error")
    public String loginError(Model model) {
        model.addAttribute("loginError",true);
        return "login"; //실패한거 에러메시지 loginService에서 어떻게 가져올지 얘기 해봐야할듯
    }
}
