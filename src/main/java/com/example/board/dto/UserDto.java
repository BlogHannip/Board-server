package com.example.board.dto;

import com.example.board.entity.User;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UserDto {

    public User getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @NotEmpty(message = "Name is required")
    private String name;

    @NotEmpty(message = "Password is required")
    @Size(min = 6 , message = "Password must be at laest 6 charactor long")
    private String password;

    @NotNull(message ="Age is required")
    private Integer age;

    @NotEmpty(message = "Sex is required")
    @Pattern(regexp = "^(M|F)$",message = "Sex must be M or F")
    private String sex;

    @NotNull(message = "Phone number is required")
    private Integer phoneNumber;

    public UserDto() {}


}
