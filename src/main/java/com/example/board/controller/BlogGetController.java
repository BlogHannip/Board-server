package com.example.board.controller;

import com.example.board.config.CustomUserDetails;
import com.example.board.entity.BlogPost;
import com.example.board.service.BlogService;
import com.example.board.service.SearchBlogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BlogGetController {

    private final SearchBlogService searchBlogService;

    public BlogGetController(SearchBlogService searchBlogService){
        this.searchBlogService =searchBlogService;
    }

    //특정 블로그 리스트 가져오기 (사용자 따라)
    @GetMapping("/my-blogs")
    public ResponseEntity<List<BlogPost>> getMyBlogs (Authentication authentication){
        if(authentication  == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        String email = userDetails.getUsername();

        List<BlogPost> blogPosts = searchBlogService.getBlogsByUser(email); //이메일에 따른 블로그 리스트 가져오기
        return ResponseEntity.ok(blogPosts);
    }
}
