package com.classroom.controller;

import com.classroom.request.post.CreatePostRequest;
import com.classroom.request.post.UpdatePostRequest;
import com.classroom.response.post.PostDetailResponse;
import com.classroom.response.post.PostResponse;
import com.classroom.service.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
//@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping
    public ResponseEntity<Page<PostResponse>> getAllPost(@RequestParam(name = "page", defaultValue = "1") int page,
                                                         @RequestParam(name = "size", defaultValue = "10") int size) {
        return postService.getAllPost(page, size);
    }

    @PostMapping
    public ResponseEntity<String> createPost(@RequestBody @Valid CreatePostRequest createPostRequest) {
        return postService.createPost(createPostRequest);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostDetailResponse> readPost(@PathVariable(name = "postId") Long readPostId,
                                                       @RequestParam(name = "page", defaultValue = "1") int page,
                                                       @RequestParam(name = "size", defaultValue = "10") int size) {
        return postService.readPost(readPostId, page, size);
    }

    @PutMapping("/{postId}")
    public ResponseEntity<String> updatePost(@PathVariable(name = "postId") Long updatePostId,
                                             @RequestBody @Valid UpdatePostRequest updatePostRequest) {
        return postService.updatePost(updatePostId, updatePostRequest);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable(name = "postId") Long deletePostId) {
        return postService.deletePost(deletePostId);
    }
}
