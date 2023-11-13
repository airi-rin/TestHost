package com.classroom.response.post;

import com.classroom.response.comment.CommentResponse;
import com.classroom.entity.CommentEntity;
import com.classroom.entity.PostEntity;
import lombok.Data;
import org.springframework.data.domain.*;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class PostDetailResponse {

    private PostResponse post;

    private Page<CommentResponse> commentPage;

    public static PostDetailResponse init(PostEntity entity, int page, int size) {
        PostDetailResponse response = new PostDetailResponse();
        response.setPost(PostResponse.init(entity));

        List<CommentEntity> commentEntityList = entity.getCommentEntities().stream()
                .filter(commentEntity -> commentEntity.getParentComment() == null)
                .collect(Collectors.toList());

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Order.asc("commentId")));
        Page<CommentResponse> commentResponsePage = new PageImpl<>(
                commentEntityList.stream()
                        .skip(size * page)
                        .limit(size)
                        .map(CommentResponse::init)
                        .collect(Collectors.toList()),
                pageable, commentEntityList.size());
        response.setCommentPage(commentResponsePage);
        return response;
    }
}
