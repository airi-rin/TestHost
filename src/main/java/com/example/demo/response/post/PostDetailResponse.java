package com.example.demo.response.post;

import com.example.demo.entity.PostEntity;
import com.example.demo.response.comment.CommentResponse;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class PostDetailResponse {

    private PostResponse post;

    private List<CommentResponse> commentList;

    public static PostDetailResponse init(PostEntity entity) {
        PostDetailResponse response = new PostDetailResponse();
        response.setPost(PostResponse.init(entity));
        List<CommentResponse> commentResponseList = entity.getCommentEntities().stream()
                .map(CommentResponse::init)
                .collect(Collectors.toList());
        response.setCommentList(commentResponseList);
        return response;
    }
}
