package com.example.demo.controller;

import com.example.demo.entity.PostEntity;
import com.example.demo.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    PostService postService;

    @GetMapping
    public ResponseEntity getAllPost() {
        return ResponseEntity.ok(postService.getAllPost());
    }

    @PostMapping
    public ResponseEntity createPost(@RequestBody PostEntity postEntity) {
        return ResponseEntity.ok(postService.createPost(postEntity));
    }
}
