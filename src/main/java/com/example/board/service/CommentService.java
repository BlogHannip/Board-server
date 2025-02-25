package com.example.board.service;

import com.example.board.entity.BlogPost;
import com.example.board.entity.Comment;
import com.example.board.entity.User;
import com.example.board.repository.BlogRepository;
import com.example.board.repository.CommentRepository;
import com.example.board.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final BlogRepository blogRepository;
    private final UserRepository userRepository;

    public CommentService(CommentRepository commentRepository , BlogRepository blogRepository, UserRepository userRepository) {
        this.blogRepository =blogRepository;
        this.commentRepository =commentRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public Comment saveComment (Long postId, Long userId, String comment) {
        BlogPost post = blogRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("블로그가 없습니다"));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자가 없습니다"));

        Comment comment1 = new Comment();
        comment1.setContent(comment);
        comment1.setPost(post);
        comment1.setUser(user);

        return commentRepository.save(comment1);
    }

    public List<Comment> getCommentByPost(Long postId) {
        return commentRepository.findByPostId(postId);
    }
}
