package com.example.board.controller;

import com.example.board.entity.BlogPost;
import com.example.board.service.BlogService;
import com.example.board.service.SearchBlogService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class BlogCountController {

    private final BlogService blogService;
    private final SearchBlogService searchBlogService;

    public BlogCountController(BlogService blogService , SearchBlogService searchBlogService) {
        this.blogService =blogService;
        this.searchBlogService =searchBlogService;
    }

    @GetMapping("/post/{id}")
    public ResponseEntity<BlogPost> getPost(@PathVariable("id")Long id) {
        blogService.incrementViewCount(id);
        BlogPost post = searchBlogService.getBlogsById(id);
        return ResponseEntity.ok(post);
    }


}
