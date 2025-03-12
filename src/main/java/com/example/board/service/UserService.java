package com.example.board.service;

import com.example.board.dto.UserDto;
import com.example.board.dto.UserUpdateRequest;
import com.example.board.entity.User;
import com.example.board.repository.UserRepository;
import jakarta.servlet.http.Cookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

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

    @Transactional
    public User updateUser(String email, UserUpdateRequest request){
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("유저 정보가 없습니다"));

        // 변경할 필드만 업데이트
        if(request.getFirstName() != null) user.setFirstName(request.getFirstName());
        if(request.getLastName() != null) user.setLastName(request.getLastName());
        if(request.getPhoneNumber() != null) user.setPhoneNumber(request.getPhoneNumber());
        if(request.getContent() != null) user.setContent(request.getContent());

        return userRepository.save(user);
    }

    @Transactional
    public void softDeleteUser(String email) {
        User user = userRepository.findByEmailIncludingDeleted(email)
                .orElseThrow(() -> new RuntimeException("유저를 찾을 수 없습니다."));

        if(user.getDeletedAt() != null){
            throw new RuntimeException("이미 탈퇴한 유저입니다.");
        }


        //논리 삭제 처리(deleted_at 업데이트)
        user.setDeletedAt(LocalDateTime.now());
        userRepository.save(user);
    }
}
