package com.example.demo.service;

import com.example.demo.entity.CommentEntity;
import com.example.demo.entity.PostEntity;
import com.example.demo.request.comment.CreateCommentRequest;
import com.example.demo.response.comment.CommentResponse;
import com.example.demo.respository.CommentRepository;
import com.example.demo.respository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {
    @Autowired
    CommentRepository commentRepository;

    @Autowired
    AuthService authService;

    @Autowired
    PostService postService;

    public List<CommentResponse> getAllComment() {
        List<CommentEntity> commentEntityList = commentRepository.findAll();
        List<CommentResponse> commentResponseList = commentEntityList.stream()
                .map(CommentResponse::init)
                .collect(Collectors.toList());
        return commentResponseList;
    }

    @Transactional
    public String createComment(CreateCommentRequest commentRequest) {
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setContent(commentRequest.getContent());
        commentEntity.setUser(authService.getUser());
        if (commentRequest.getParentIsPost()) {
            commentEntity.setPost(postService.getPostEntity(commentRequest.getParentId()));
        } else {
            //CommentEntity

            //commentEntity.setParentComment();
        }
        return "Create comment sucess";
    }


}
