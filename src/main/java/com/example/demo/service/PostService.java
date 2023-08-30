package com.example.demo.service;

import com.example.demo.auth.AuthService;
import com.example.demo.entity.ERole;
import com.example.demo.entity.PostEntity;
import com.example.demo.auth.UserEntity;
import com.example.demo.request.post.CreatePostRequest;
import com.example.demo.request.post.UpdatePostRequest;
import com.example.demo.response.post.PostDetailResponse;
import com.example.demo.response.post.PostResponse;
import com.example.demo.respository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private AuthService authService;

    public ResponseEntity<Page<PostResponse>> getAllPost (int page, int size){
        page --;
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Order.asc("postId")));
        Page<PostEntity> postEntityPage = postRepository.findAll(pageable);
        Page<PostResponse> postResponsePage = new PageImpl<> (
                postEntityPage.getContent().stream()
                        .map(PostResponse::init)
                        .collect(Collectors.toList()),
                pageable, postEntityPage.getTotalElements());
        ResponseEntity responseEntity = new ResponseEntity<>(postResponsePage, HttpStatus.OK);
        return responseEntity;
    }

    @Transactional
    public ResponseEntity<String> createPost(CreatePostRequest postRequest) {
        PostEntity postEntity = new PostEntity();

        postEntity.setPostTitle(postRequest.getPostTitle());
        postEntity.setPostContent(postRequest.getPostContent());
        postEntity.setPerson(authService.getUser().getPerson());

        postRepository.save(postEntity);
        return ResponseEntity.ok("Create post sucess");
    }

    public ResponseEntity<PostDetailResponse> readPost(Long postId, int page, int size) {
        PostEntity postEntity = getPostEntity(postId);
        if (!canReadPost(postEntity)) {
            throw new RuntimeException("Can not read this post");
        }

        PostDetailResponse postResponse = PostDetailResponse.init(postEntity, page - 1, size);
        return ResponseEntity.ok(postResponse);
    }

    @Transactional
    public ResponseEntity<String> updatePost(Long postId, UpdatePostRequest postRequest) {
        PostEntity postEntity = getPostEntity(postId);
        if (!canChangePost(postEntity)) {
            throw new RuntimeException("Can not update this post");
        }

        postEntity.setPostTitle(postRequest.getPostTitle());
        postEntity.setPostContent(postRequest.getPostContent());

        postRepository.save(postEntity);
        return ResponseEntity.ok("Update post sucess");
    }

    @Transactional
    public ResponseEntity<String> deletePost(Long postId) {
        PostEntity postEntity = getPostEntity(postId);
        if (canChangePost(postEntity)) {
            throw new RuntimeException("Can not delete this post");
        }

        postRepository.delete(postEntity);
        return ResponseEntity.ok("Delete post sucess");
    }

    public PostEntity getPostEntity(Long postId) {
        Optional<PostEntity> postEntityOptional = postRepository.findById(postId);
        if (!postEntityOptional.isPresent()) {
            throw new IllegalArgumentException("Not exist this post");
        }

        return postEntityOptional.get();
    }

    private boolean canChangePost(PostEntity postEntity) {
        UserEntity userUpdate = authService.getUser();
        if (userUpdate.getId() != postEntity.getPerson().getUser().getId()
                && userUpdate.getRole().getRoleName() != ERole.ADMIN) {
            return false;
        }
        return true;
    }

    private boolean canReadPost(PostEntity postEntity) {
        //
        return true;
    }
}
