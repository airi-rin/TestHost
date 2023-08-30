package com.example.demo.service;

import com.example.demo.auth.AuthService;
import com.example.demo.auth.UserEntity;
import com.example.demo.entity.CommentEntity;
import com.example.demo.entity.ERole;
import com.example.demo.entity.PostEntity;
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

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentService {
    @Autowired
    CommentRepository commentRepository;

    @Autowired
    AuthService authService;

    @Autowired
    PostService postService;

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
        commentEntity.setPerson(authService.getUser().getPerson());
        if (commentRequest.getParentIsPost()) {
            commentEntity.setPost(postService.getPostEntity(commentRequest.getParentId()));
        } else {
            Optional<CommentEntity> parentEntityOptional = commentRepository.findById(commentRequest.getParentId());
            if (!parentEntityOptional.isPresent()) {
                throw new IllegalArgumentException("Not exist comment parent");
            }
            CommentEntity parentEntity = parentEntityOptional.get();
            commentEntity.setParentComment(parentEntity);

            commentEntity.setPost(parentEntity.getPost());
        }

        commentRepository.save(commentEntity);
        return ResponseEntity.ok("Create comment sucess");
    }

    public ResponseEntity<CommentDetailResponse> readComment(Long commentId, int page, int size) {
        CommentEntity commentEntity = getCommentEntity(commentId);
        if(!canReadComment(commentEntity)) {
            throw new RuntimeException("Can not read this comment");
        }
        CommentDetailResponse commentDetailResponse = CommentDetailResponse.init(commentEntity, page - 1, size);
        return ResponseEntity.ok(commentDetailResponse);
    }

    public ResponseEntity<String> updateComment(Long commentId, UpdateCommentRequest commentRequest) {
        CommentEntity commentEntity = getCommentEntity(commentId);
        if (!canChangeComment(commentEntity)) {
            throw new RuntimeException("Can not update this comment");
        }

        commentEntity.setContent(commentRequest.getContent());
        commentRepository.save(commentEntity);
        return ResponseEntity.ok("Update comment sucess");
    }

    public ResponseEntity<String> deleteComment(Long commentId) {
        CommentEntity commentEntity = getCommentEntity(commentId);
        if(!canChangeComment(commentEntity)) {
            throw new RuntimeException("Can not delete this comment");
        }

        commentRepository.delete(commentEntity);
        return ResponseEntity.ok("Delete comment sucess");
    }

    public CommentEntity getCommentEntity(Long commentId) {
        Optional<CommentEntity> commentEntityOptional = commentRepository.findById(commentId);
        if(!commentEntityOptional.isPresent()) {
            throw new IllegalArgumentException("Not exist this comment");
        }
        return commentEntityOptional.get();
    }

    private boolean canReadComment(CommentEntity commentEntity) {
        return true;
    }

    private boolean canChangeComment(CommentEntity commentEntity) {
        UserEntity userUpdate = authService.getUser();
        if (userUpdate.getId() != commentEntity.getPerson().getUser().getId() &&
                userUpdate.getId() != commentEntity.getPost().getPerson().getUser().getId() &&
                userUpdate.getRole().getRoleName() != ERole.ADMIN) {
            return false;
        }
        return true;
    }

}
