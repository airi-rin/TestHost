package com.example.demo.controller;

import com.example.demo.request.CreatePostRequest;
import com.example.demo.service.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts")
@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
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
}
