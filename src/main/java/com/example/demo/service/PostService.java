package com.example.demo.service;

import com.example.demo.entity.PostEntity;
import com.example.demo.respository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    @Autowired
    PostRepository postRepository;

    public List<PostEntity> getAllPost (){
        return postRepository.findAll();
    }

    public PostEntity createPost(PostEntity postEntity) {
        return postRepository.save(postEntity);
    }
}
