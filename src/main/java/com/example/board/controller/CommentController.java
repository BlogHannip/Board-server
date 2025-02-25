package com.example.board.controller;

import com.example.board.entity.Comment;
import com.example.board.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService =commentService;
    }

    // 댓글 작성기능
    @PostMapping("/create")
    public ResponseEntity<Comment> createComment(@RequestParam Long postId ,
                                                 @RequestParam Long userId,
                                                 @RequestParam String content) {
        Comment comment = commentService.saveComment(postId,userId,content);
        return ResponseEntity.ok(comment);
    }


    @GetMapping("/post/{postId}")
    public ResponseEntity<List<Comment>> getCommentByPost(@PathVariable Long postId) {
        List<Comment> comments = commentService.getCommentByPost(postId);

        return ResponseEntity.ok(comments);
    }
}
