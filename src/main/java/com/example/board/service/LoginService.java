package com.example.board.service;

import com.example.board.dto.LoginRequestDto;
import com.example.board.dto.UserDto;
import com.example.board.entity.User;
import com.example.board.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public LoginService(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository =userRepository;
        this.passwordEncoder=passwordEncoder;
    }
    public String login(LoginRequestDto loginRequestDto){
        //유저 조회
        Optional<User> optionalUser =userRepository.findByEmail(loginRequestDto.getEmail());
        if(optionalUser.isPresent()){
            User user = optionalUser.get();

            if(passwordEncoder.matches(loginRequestDto.getPassword(),user.getPassword())){
                return "로그인 잘됫어용";
            } else {
                return "로그인 그지같이 됫어용";
            }
        } else {
            return "요청이 불확실해용.";
        }
    }
}
