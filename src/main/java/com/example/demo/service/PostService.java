package com.example.demo.service;

import com.example.demo.entity.ERole;
import com.example.demo.entity.PostEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.request.post.CreatePostRequest;
import com.example.demo.request.post.UpdatePostRequest;
import com.example.demo.response.post.PostResponse;
import com.example.demo.respository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public List<PostResponse> getAllPost (){
        List<PostEntity> postEntityList = postRepository.findAll();
        List<PostResponse> postResponseList = postEntityList.stream()
                .map(PostResponse::init)
                .collect(Collectors.toList());
        return postResponseList;
    }

    @Transactional
    public String createPost(CreatePostRequest postRequest) {
        PostEntity postEntity = new PostEntity();

        postEntity.setPostTitle(postRequest.getPostTitle());
        postEntity.setPostContent(postRequest.getPostContent());
        postEntity.setUser(authService.getUser());

        postRepository.save(postEntity);
        return "Create post sucess";
    }

    public PostResponse readPost(Long postId) {
        PostEntity postEntity = getPostEntity(postId);
        if (!canReadPost(postEntity)) {
            throw new RuntimeException("Can not read this post");
        }

        PostResponse postResponse = PostResponse.init(postEntity);
        return postResponse;
    }

    @Transactional
    public String updatePost(Long postId, UpdatePostRequest postRequest) {
        PostEntity postEntity = getPostEntity(postId);
        if (!canChangePost(postEntity)) {
            throw new RuntimeException("Can not update this post");
        }

        postEntity.setPostTitle(postRequest.getPostTitle());
        postEntity.setPostContent(postRequest.getPostContent());

        postRepository.save(postEntity);
        return "Update post sucess";
    }

    @Transactional
    public String deletePost(Long postId) {
        PostEntity postEntity = getPostEntity(postId);
        if (canChangePost(postEntity)) {
            throw new RuntimeException("Can not delete this post");
        }

        postRepository.delete(postEntity);
        return "Delete post sucess";
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
        if (userUpdate != postEntity.getUser() && userUpdate.getRole().getRoleName() != ERole.ADMIN) {
            return false;
        }
        return true;
    }

    private boolean canReadPost(PostEntity postEntity) {
        //
        return true;
    }
}
