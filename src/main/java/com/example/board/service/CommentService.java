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
import java.util.Optional;

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
    public Comment saveComment (Long postId,String email, String comment) {
        System.out.println("🔍 댓글 작성 요청: postId=" + postId + ", email=" + email + ", content=" + comment);

        BlogPost post = blogRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("블로그가 없습니다"));

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> {
                    System.out.println(" DB에서 찾을 수 없는 이메일: " + email);
                    return new IllegalArgumentException("사용자가 없습니다");
                });

        Comment comment1 = new Comment();
        comment1.setContent(comment);
        comment1.setPost(post);
        comment1.setUser(user);

        return commentRepository.save(comment1);
    }

    public List<Comment> getCommentByPost(Long postId) {
        return commentRepository.findByPostId(postId);
    }

    @Transactional
    public Comment updateComment (Long commentId, String newComment){
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("댓글 찾을수없음"));
        comment.setContent(newComment);
        return commentRepository.save(comment);
    }

    @Transactional
    public void deleteComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(()->
                new IllegalArgumentException("사용자 x"));
        commentRepository.delete(comment);
    }
}
