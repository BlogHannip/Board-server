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

@RestController
@RequestMapping("/api/posts")
public class BlogPostController {

    private final BlogService blogService;

    public BlogPostController (BlogService blogService){
        this.blogService =blogService;
    }


    @PostMapping
    public ResponseEntity<BlogPost> createPost(@RequestBody BlogDto blogDto) {
        BlogPost savePost  = blogService.savePost(blogDto.getTitle(),blogDto.getContent());
        return ResponseEntity.ok(savePost);
    }

}
