package com.example.board.service;

import com.example.board.entity.BlogPost;
import com.example.board.entity.User;
import com.example.board.repository.BlogRepository;
import com.example.board.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
public class BlogService {
    private final BlogRepository blogRepository;
    private final UserRepository userRepository;

    public BlogService (BlogRepository blogRepository ,UserRepository userRepository){
        this.blogRepository = blogRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public BlogPost savePost(String title, String content ,String email) {
        //이메일로 사용자 조회
        User user = userRepository.findByEmail(email).
                orElseThrow(() -> new RuntimeException("사용자를 찾을수 없습니다"));

        //블로그 글생성 및 저장하는 로직
        BlogPost post = new BlogPost(title,content,user);
        return blogRepository.save(post);
    }
}
