package com.example.board.service;

import com.example.board.dto.UserDto;
import com.example.board.entity.User;
import com.example.board.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.validation.BindingResult;

@Service
public class  UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncdoer;

    @Autowired
    public UserService(UserRepository userRepository , PasswordEncoder passwordEncoder){
        this.userRepository =userRepository;
        this.passwordEncdoer = passwordEncoder;  //생성자로 의존성 주
    }

    public String register(@Validated UserDto userDto,BindingResult bindingREsult){
          if(bindingREsult.hasErrors()){
              return "회원가입 정보가 유효하지 않습니다.";
          }
          String encoded = passwordEncdoer.encode(userDto.getPassword());

          User user = new User();
          user.setName(userDto.getName());
          user.setPassword(encoded);
          user.setAge(userDto.getAge());
          user.setSex(userDto.getSex());
          user.setPhoneNumber(userDto.getPhoneNumber());

          userRepository.save(user);

          return "회원가입 성공";
    }
}
