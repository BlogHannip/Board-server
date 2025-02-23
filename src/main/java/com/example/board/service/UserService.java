package com.example.board.service;

import com.example.board.dto.UserDto;
import com.example.board.entity.User;
import com.example.board.repository.UserRepository;
import jakarta.servlet.http.Cookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class  UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncdoer;

    @Autowired
    public UserService(UserRepository userRepository , PasswordEncoder passwordEncoder){
        this.userRepository =userRepository;
        this.passwordEncdoer = passwordEncoder;  //생성자로 의존성 주입
    }

    public String register(@Validated UserDto userDto){
        if(userDto.getEmail().isEmpty()){
            throw new IllegalArgumentException("email is null");
        }

        logger.debug("UserDto 정보: {}", userDto);
          String encoded = passwordEncdoer.encode(userDto.getPassword());

          User user = new User();
          user.setEmail(userDto.getEmail());
          user.setPassword(encoded);
          user.setSex(userDto.getSex());
          user.setFirstName(userDto.getFirstName());
          user.setLastName(userDto.getLastName());
          user.setBirthday(userDto.getBirthday());
          user.setPhoneNumber(userDto.getPhoneNumber());
          user.setRole("ROLE_USER");

         logger.debug("User 객체 정보: {}", user);
          userRepository.save(user);

          return "회원가입 성공";
    }
}
