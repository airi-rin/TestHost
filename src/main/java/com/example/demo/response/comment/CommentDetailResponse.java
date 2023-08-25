package com.example.demo.response.comment;

import com.example.demo.entity.CommentEntity;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class CommentDetailResponse {

    private CommentResponse comment;

    private List<CommentResponse> commentList;

    public static CommentDetailResponse init(CommentEntity entity) {
        CommentDetailResponse response = new CommentDetailResponse();
        response.setComment(CommentResponse.init(entity));
        List<CommentResponse> commentResponseList = entity.getCommentEntities().stream()
                .map(CommentResponse::init)
                .collect(Collectors.toList());
        response.setCommentList(commentResponseList);
        return response;
    }
}
