package com.example.board.controller;

import com.example.board.dto.CommentRequest;
import com.example.board.entity.Comment;
import com.example.board.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService =commentService;
    }

    // 댓글 작성기능
    @PostMapping("/create")
    public ResponseEntity<Comment> createComment(@RequestBody CommentRequest request) {
        System.out.println(request);
        Comment comment = commentService.saveComment(request.postId(),request.email(),request.content());
        return ResponseEntity.ok(comment);
    }


    @GetMapping("/post/{postId}")
    public ResponseEntity<List<Comment>> getCommentByPost(@PathVariable Long postId) {
        List<Comment> comments = commentService.getCommentByPost(postId);

        return ResponseEntity.ok(comments);
    }

    @PutMapping("/update/{commentId}")
    public ResponseEntity<?> updateComment(@PathVariable Long commentId , @RequestBody Map<String, String > requestBody){
        String content = requestBody.get("content");

        if(content == null || content.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("댓글 내용을 입력하세요");
        }

        Comment updatedComment = commentService.updateComment(commentId, content);
        return ResponseEntity.ok(updatedComment);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long commentId){
        commentService.deleteComment(commentId);
        return ResponseEntity.noContent().build(); // 삭제
    }
}
