package com.example.board.dto;

public class LoginResponseDto {
    private String email;
    private String message;

    public LoginResponseDto(String email, String message){
        this.email = email;
        this.message = message;
    }

    public String getEmail() {
        return email;
    }

    public String getMessage() {
        return message;
    }
}
