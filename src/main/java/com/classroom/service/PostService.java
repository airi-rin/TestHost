package com.classroom.service;

import com.classroom.auth.UserEntity;
import com.classroom.config.Message;
import com.classroom.constant.MessageConstant;
import com.classroom.entity.*;
import com.classroom.request.post.CreatePostRequest;
import com.classroom.request.post.UpdatePostRequest;
import com.classroom.response.post.PostDetailResponse;
import com.classroom.response.post.PostResponse;
import com.classroom.respository.PostRepository;
import com.classroom.auth.AuthService;
import com.example.demo.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private AuthService authService;

    @Autowired
    private ClassroomService classroomService;

    @Autowired
    private PersonClassroomService personClassroomService;

    public ResponseEntity<Page<PostResponse>> getAllPost (int page, int size){
        page --;
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Order.asc("postId")));
        Page<PostEntity> postEntityPage = postRepository.findAll(pageable);
        Page<PostResponse> postResponsePage = new PageImpl<> (
                postEntityPage.getContent().stream()
                        .map(PostResponse::init)
                        .collect(Collectors.toList()),
                pageable, postEntityPage.getTotalElements());
        return new ResponseEntity<>(postResponsePage, HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<String> createPost(CreatePostRequest postRequest) {
        PostEntity postEntity = new PostEntity();

        postEntity.setPostTitle(postRequest.getPostTitle());
        postEntity.setPostContent(postRequest.getPostContent());

        PersonEntity person = authService.getPerson();
        ClassroomEntity classroom = classroomService.getClassroom(postRequest.getClassroomId());
        PersonClassroomEntity personClassroom = personClassroomService.getPersonClassroom(person, classroom);
        postEntity.setPersonClassroom(personClassroom);

        postRepository.save(postEntity);
        return ResponseEntity.ok(Message.getMessage(MessageConstant.SUCCESS_CREATE, "post"));
    }

    public ResponseEntity<PostDetailResponse> readPost(Long postId, int page, int size) {
        PostEntity postEntity = getPostEntity(postId);
        if (!canReadPost(postEntity)) {
            throw new RuntimeException(Message.getMessage(MessageConstant.CAN_NOT_READ, "post"));
        }

        PostDetailResponse postResponse = PostDetailResponse.init(postEntity, page - 1, size);
        return ResponseEntity.ok(postResponse);
    }

    @Transactional
    public ResponseEntity<String> updatePost(Long postId, UpdatePostRequest postRequest) {
        PostEntity postEntity = getPostEntity(postId);
        if (!canChangePost(postEntity)) {
            throw new RuntimeException(Message.getMessage(MessageConstant.CAN_NOT_UPDATE, "post"));
        }

        postEntity.setPostTitle(postRequest.getPostTitle());
        postEntity.setPostContent(postRequest.getPostContent());

        postRepository.save(postEntity);
        return ResponseEntity.ok(Message.getMessage(MessageConstant.SUCCESS_UPDATE, "post"));
    }

    @Transactional
    public ResponseEntity<String> deletePost(Long postId) {
        PostEntity postEntity = getPostEntity(postId);
        if (canChangePost(postEntity)) {
            throw new RuntimeException(Message.getMessage(MessageConstant.CAN_NOT_DELETE, "post"));
        }

        postRepository.delete(postEntity);
        return ResponseEntity.ok(Message.getMessage(MessageConstant.SUCCESS_DELETE, "post"));
    }

    public PostEntity getPostEntity(Long postId) {
        Optional<PostEntity> postEntityOptional = postRepository.findById(postId);
        if (!postEntityOptional.isPresent()) {
            throw new IllegalArgumentException(Message.getMessage(MessageConstant.NOT_EXIST, "post"));
        }

        return postEntityOptional.get();
    }

    private boolean canChangePost(PostEntity postEntity) {
        UserEntity userUpdate = authService.getUser();
        return userUpdate.getId() == postEntity.getPersonClassroom().getPerson().getUser().getId()
                || userUpdate.getRole().getRoleName() == ERole.ADMIN;
    }

    private boolean canReadPost(PostEntity postEntity) {
        //
        return true;
    }
}
