package com.example.board.controller;

import com.example.board.dto.BlogDto;
import com.example.board.entity.BlogPost;
import com.example.board.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/posts")
public class BlogPostController {

    private final BlogService blogService;

    public BlogPostController (BlogService blogService){
        this.blogService =blogService;
    }


    @PostMapping
    public ResponseEntity<?> createPost(@RequestBody BlogDto blogDto , Principal principal) {
        if(principal == null){
            return ResponseEntity.status(401).body("로그인 안되어있음");
        }
        System.out.println("현재 로그인한 사용자:"+ principal.getName());
        String email = principal.getName();

        BlogPost savePost  = blogService.savePost(blogDto.getTitle(),blogDto.getContent(),email);
        return ResponseEntity.ok(savePost);
    }

}
