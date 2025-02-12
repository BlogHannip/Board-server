package com.example.board.service;

import com.example.board.entity.BlogPost;
import com.example.board.repository.BlogRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchBlogService {

    private final BlogRepository blogRepository;

    public SearchBlogService(BlogRepository blogRepository){
        this.blogRepository =blogRepository;
    }

    public List<BlogPost> getBlogsByUser(String email) {
        return blogRepository.findByUser_Email(email);
    }
}
