package com.example.board.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UserDto {

    @NotEmpty(message = "Name is required")
    private String email;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getBirthday() {
        return birthday;
    }

    public void setBirthday(int birthday) {
        this.birthday = birthday;
    }

    @NotEmpty(message = "At least one character is existing")
    @Size(min =1, message = "Firstname must be at least 1 charactrers long")
    private String firstName;

    @NotEmpty(message = "At least one character is existing")
    @Size(min =1, message = "lastname must be at least 1 charactrers long")
    private String lastName;

    @NotEmpty(message = "none")
    @Size(min=8,message = "not empty")
    private int birthday;

    @NotEmpty(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters long")
    private String password;

    @NotNull(message = "Age is required")
    private Integer age;

    @NotEmpty(message = "Sex is required")
    @Pattern(regexp = "^(M|F)$", message = "Sex must be 'M' or 'F'")
    private String sex;

    @NotEmpty(message = "Phone number is required")
    private String phoneNumber;

    public UserDto() {}

    // Getter & Setter
    public String getEmail() {
        return email;
    }

    public void setEmail(String name) {
        this.email = email;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
