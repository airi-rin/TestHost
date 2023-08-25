package com.example.demo.controller;

import com.example.demo.request.comment.CreateCommentRequest;
import com.example.demo.request.comment.UpdateCommentRequest;
import com.example.demo.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    CommentService commentService;

    @GetMapping
    public ResponseEntity getAllComment() {
        return ResponseEntity.ok(commentService.getAllComment());
    }

    @PostMapping
    public ResponseEntity createComment(@RequestBody CreateCommentRequest createCommentRequest) {
        return ResponseEntity.ok(commentService.createComment(createCommentRequest));
    }

    @GetMapping("/{commentId}")
    public ResponseEntity readComment(@PathVariable(name = "commentId") Long readCommentId) {
        return ResponseEntity.ok(commentService.readComment(readCommentId));
    }

    @PutMapping("/{commentId}")
    public ResponseEntity updateComment(@PathVariable(name = "commentId") Long updateCommentId,
                                        @RequestBody UpdateCommentRequest updateCommentRequest) {
        return ResponseEntity.ok(commentService.updateComment(updateCommentId, updateCommentRequest));
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity deleteComment(@PathVariable(name = "commentId") Long deleteCommentId) {
        return ResponseEntity.ok(commentService.deleteComment(deleteCommentId));
    }
}
