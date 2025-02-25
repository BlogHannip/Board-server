package com.example.board.service;

import com.example.board.entity.BlogPost;
import com.example.board.entity.Category;
import com.example.board.entity.User;
import com.example.board.repository.BlogRepository;
import com.example.board.repository.CategoryRepository;
import com.example.board.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

@Service
public class BlogService {
    private final BlogRepository blogRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public BlogService (BlogRepository blogRepository ,UserRepository userRepository ,CategoryRepository categoryRepository){
        this.blogRepository = blogRepository;
        this.userRepository = userRepository;
        this.categoryRepository =categoryRepository;
    }

    @Transactional
    public BlogPost savePost(String title, String content , String email , String  categoryName) {
        //이메일로 사용자 조회
        User user = userRepository.findByEmail(email).
                orElseThrow(() -> new RuntimeException("사용자를 찾을수 없습니다"));

        Category category = categoryRepository.findByName(categoryName)
                .orElseThrow(() -> new RuntimeException("카테고리가 없습니다."));

        //블로그 글생성 및 저장하는 로직
        BlogPost post = new BlogPost(title,content,user,category);
        return blogRepository.save(post);
    }

    public void incrementViewCount(Long postId) {
        BlogPost post = blogRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않습니다"));
        post.setViewCount(post.getViewCount() + 1);
        blogRepository.save(post);
    }
}
