package com.example.board.service;

import com.example.board.entity.BlogPost;
import com.example.board.repository.BlogRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
public class BlogService {
    private final BlogRepository blogRepository;

    public BlogService (BlogRepository blogRepository){
        this.blogRepository = blogRepository;
    }

    @Transactional
    public BlogPost savePost(String title, String content) {
        BlogPost blogPost = new BlogPost(title,content);
        return blogRepository.save(blogPost);
    }
}
