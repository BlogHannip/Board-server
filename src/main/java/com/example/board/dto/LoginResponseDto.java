package com.example.board.dto;

public class LoginResponseDto {
    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    private String accessToken;

    private String refreshToken;

    private String message;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private String email;

    public LoginResponseDto(String accessToken,String refreshToken, String message,String email){
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.message = message;
        this.email = email;
    }


    public String getMessage() {
        return message;
    }
}
