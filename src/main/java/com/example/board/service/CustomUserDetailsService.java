package com.example.board.service;

import com.example.board.config.CustomUserDetails;
import com.example.board.entity.User;
import com.example.board.mapper.UserMapper;
import com.example.board.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserMapper userMapper;

    public CustomUserDetailsService(UserMapper userMapper){
        this.userMapper = userMapper;
    }
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> optionalUser = userMapper.findByEmail(email);

        //유저를 찾을수없을경우 예외처리
        User user =optionalUser.orElseThrow(() ->
                new UsernameNotFoundException("사용자 정보 처리 불가"));

//        String role = user.getEmail() != null ? user.getRole()

        return new CustomUserDetails(user.getEmail(),user.getPassword(),user.getRole());
        //MyBatis로 가져온 사용자 정보 반환
    }
}
