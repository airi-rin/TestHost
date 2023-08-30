package com.example.demo.response.comment;

import com.example.demo.entity.CommentEntity;
import lombok.Data;
import org.springframework.data.domain.*;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class CommentDetailResponse {

    private CommentResponse comment;

    private Page<CommentResponse> commentPage;

    public static CommentDetailResponse init(CommentEntity entity, int page, int size) {
        CommentDetailResponse response = new CommentDetailResponse();
        response.setComment(CommentResponse.init(entity));
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Order.asc("commentId")));
        Page<CommentResponse> commentResponsePage = new PageImpl<>(
                entity.getCommentEntities().stream()
                        .skip(page * size)
                        .limit(size)
                        .map(CommentResponse::init)
                        .collect(Collectors.toList()),
                pageable, entity.getCommentEntities().size());
        response.setCommentPage(commentResponsePage);
        return response;
    }
}
