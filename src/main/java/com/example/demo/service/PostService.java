package com.example.demo.service;

import com.example.demo.entity.PostEntity;
import com.example.demo.request.CreatePostRequest;
import com.example.demo.respository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private AuthService authService;

    public List<PostEntity> getAllPost (){
        return postRepository.findAll();
    }

    public PostEntity createPost(CreatePostRequest createPostRequest) {
        PostEntity postEntity = new PostEntity();

        postEntity.setTitle(createPostRequest.getTitle());
        postEntity.setContent(createPostRequest.getContent());
        postEntity.setUser(authService.getUser());
        return postRepository.save(postEntity);
    }
}
