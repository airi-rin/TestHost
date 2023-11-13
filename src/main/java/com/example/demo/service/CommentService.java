package com.example.demo.service;

import com.example.demo.auth.AuthService;
import com.example.demo.auth.UserEntity;
import com.example.demo.config.Message;
import com.example.demo.constant.MessageConstant;
import com.example.demo.entity.*;
import com.example.demo.request.comment.CreateCommentRequest;
import com.example.demo.request.comment.UpdateCommentRequest;
import com.example.demo.response.comment.CommentDetailResponse;
import com.example.demo.response.comment.CommentResponse;
import com.example.demo.respository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private AuthService authService;

    @Autowired
    private PostService postService;

    @Autowired
    private ClassroomService classroomService;

    @Autowired
    private PersonClassroomService personClassroomService;

    public ResponseEntity<Page<CommentResponse>> getAllComment(int page, int size) {
        page --;
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Order.asc("commentId")));
        Page<CommentEntity> commentEntityPage = commentRepository.findAll(pageable);
        Page<CommentResponse> commentResponsePage = new PageImpl<>(
                commentEntityPage.getContent().stream()
                        .map(CommentResponse::init)
                        .collect(Collectors.toList()),
                pageable, commentEntityPage.getTotalElements());
        return ResponseEntity.ok(commentResponsePage);
    }

    @Transactional
    public ResponseEntity<String> createComment(CreateCommentRequest commentRequest) {
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setContent(commentRequest.getContent());

        PersonEntity person = authService.getPerson();
        ClassroomEntity classroom;

        if (commentRequest.getParentIsPost()) {
            PostEntity post = postService.getPostEntity(commentRequest.getParentId());
            commentEntity.setPost(post);
            classroom = post.getPersonClassroom().getClassroom();
        } else {
            Optional<CommentEntity> parentEntityOptional = commentRepository.findById(commentRequest.getParentId());
            if (!parentEntityOptional.isPresent()) {
                throw new IllegalArgumentException(Message.getMessage(MessageConstant.NOT_EXIST, "comment parent"));
            }
            CommentEntity parentEntity = parentEntityOptional.get();
            commentEntity.setParentComment(parentEntity);

            commentEntity.setPost(parentEntity.getPost());
            classroom = parentEntity.getPersonClassroom().getClassroom();
        }

        commentEntity.setPersonClassroom(personClassroomService.getPersonClassroom(person, classroom));

        commentRepository.save(commentEntity);
        return ResponseEntity.ok(Message.getMessage(MessageConstant.SUCCESS_CREATE, "comment"));
    }

    public ResponseEntity<CommentDetailResponse> readComment(Long commentId, int page, int size) {
        CommentEntity commentEntity = getCommentEntity(commentId);
        if(!canReadComment(commentEntity)) {
            throw new RuntimeException(Message.getMessage(MessageConstant.CAN_NOT_READ, "comment"));
        }
        CommentDetailResponse commentDetailResponse = CommentDetailResponse.init(commentEntity, page - 1, size);
        return ResponseEntity.ok(commentDetailResponse);
    }

    @Transactional
    public ResponseEntity<String> updateComment(Long commentId, UpdateCommentRequest commentRequest) {
        CommentEntity commentEntity = getCommentEntity(commentId);
        if (!canChangeComment(commentEntity)) {
            throw new RuntimeException(Message.getMessage(MessageConstant.CAN_NOT_UPDATE, "comment"));
        }

        commentEntity.setContent(commentRequest.getContent());
        commentRepository.save(commentEntity);
        return ResponseEntity.ok(Message.getMessage(MessageConstant.SUCCESS_UPDATE, "comment"));
    }

    @Transactional
    public ResponseEntity<String> deleteComment(Long commentId) {
        CommentEntity commentEntity = getCommentEntity(commentId);
        if(!canChangeComment(commentEntity)) {
            throw new RuntimeException(Message.getMessage(MessageConstant.CAN_NOT_DELETE, "comment"));
        }

        commentRepository.delete(commentEntity);
        return ResponseEntity.ok(Message.getMessage(MessageConstant.SUCCESS_DELETE, "comment"));
    }

    public CommentEntity getCommentEntity(Long commentId) {
        Optional<CommentEntity> commentEntityOptional = commentRepository.findById(commentId);
        if(!commentEntityOptional.isPresent()) {
            throw new IllegalArgumentException(Message.getMessage(MessageConstant.NOT_EXIST, "comment"));
        }
        return commentEntityOptional.get();
    }

    private boolean canReadComment(CommentEntity commentEntity) {
        return true;
    }

    private boolean canChangeComment(CommentEntity commentEntity) {
        UserEntity userUpdate = authService.getUser();
        return userUpdate.getId() == commentEntity.getPersonClassroom().getPerson().getUser().getId() ||
                userUpdate.getId() == commentEntity.getPost().getPersonClassroom().getPerson().getUser().getId() ||
                userUpdate.getRole().getRoleName() != ERole.ADMIN;
    }

}
