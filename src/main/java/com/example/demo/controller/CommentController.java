package com.example.demo.controller;

import com.example.demo.request.comment.CreateCommentRequest;
import com.example.demo.request.comment.UpdateCommentRequest;
import com.example.demo.response.comment.CommentDetailResponse;
import com.example.demo.response.comment.CommentResponse;
import com.example.demo.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comments")
//@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
public class CommentController {

    @Autowired
    CommentService commentService;

    @GetMapping
    public ResponseEntity<Page<CommentResponse>> getAllComment(@RequestParam(name = "page", defaultValue = "1") int page,
                                                               @RequestParam(name = "size", defaultValue = "10") int size) {
        return commentService.getAllComment(page, size);
    }

    @PostMapping
    public ResponseEntity<String> createComment(@Valid @RequestBody CreateCommentRequest createCommentRequest) {
        return commentService.createComment(createCommentRequest);
    }

    @GetMapping("/{commentId}")
    public ResponseEntity<CommentDetailResponse> readComment(@PathVariable(name = "commentId") Long readCommentId,
                                                             @RequestParam(name = "page", defaultValue = "1") int page,
                                                             @RequestParam(name = "size", defaultValue = "10") int size) {
        return commentService.readComment(readCommentId, page, size);
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<String> updateComment(@PathVariable(name = "commentId") Long updateCommentId,
                                                @Valid @RequestBody UpdateCommentRequest updateCommentRequest) {
        return commentService.updateComment(updateCommentId, updateCommentRequest);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable(name = "commentId") Long deleteCommentId) {
        return commentService.deleteComment(deleteCommentId);
    }
}
