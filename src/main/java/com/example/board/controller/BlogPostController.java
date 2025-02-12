package com.example.board.controller;

import com.example.board.config.CustomUserDetails;
import com.example.board.dto.BlogDto;
import com.example.board.entity.BlogPost;
import com.example.board.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    public ResponseEntity<?> createPost(@RequestBody BlogDto blogDto ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("contorller 에서 인증객체 SecuriyContextHolder:" + authentication);

        if(authentication == null || !authentication.isAuthenticated()){
            return ResponseEntity.status(401).body("로그인이 안되어있음");
        }
        Object principal = authentication.getPrincipal();
        if(!(principal instanceof CustomUserDetails)) {
            return ResponseEntity.status(401).body("잘못된인증");
        }

        CustomUserDetails userDetails = (CustomUserDetails) principal;
        String email = userDetails.getUsername();

        System.out.println("현재 로그인한 사용자:"+ email);
        BlogPost savePost = blogService.savePost(blogDto.getTitle(),blogDto.getContent(),email);
        return ResponseEntity.ok(savePost);

    }

}
