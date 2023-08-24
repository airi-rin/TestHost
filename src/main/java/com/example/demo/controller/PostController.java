package com.example.demo.controller;

import com.example.demo.request.post.CreatePostRequest;
import com.example.demo.request.post.UpdatePostRequest;
import com.example.demo.service.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts")
//@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping
    public ResponseEntity getAllPost() {
        return ResponseEntity.ok(postService.getAllPost());
    }

    @PostMapping
    public ResponseEntity createPost(@RequestBody @Valid CreatePostRequest createPostRequest) {
        return ResponseEntity.ok(postService.createPost(createPostRequest));
    }

    @GetMapping("/{postId}")
    public ResponseEntity readPost(@PathVariable(name = "postId") Long readPostId) {
        return ResponseEntity.ok(postService.readPost(readPostId));
    }

    @PutMapping("/{postId}")
    public ResponseEntity updatePost(@PathVariable(name = "postId") Long updatePostId,
                                     @RequestBody @Valid UpdatePostRequest updatePostRequest) {
        return ResponseEntity.ok(postService.updatePost(updatePostId, updatePostRequest));
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity deletePost(@PathVariable(name = "postId") Long deletePostId) {
        return ResponseEntity.ok(postService.deletePost(deletePostId));
    }
}
