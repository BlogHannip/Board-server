package com.example.board.mapper;

import com.example.board.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM users WHERE email = #{email}")
    User findByEmail(String email);
}
