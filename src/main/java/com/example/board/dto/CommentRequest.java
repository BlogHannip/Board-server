package com.example.board.dto;


public record CommentRequest(Long postId, String email, String content) {
}