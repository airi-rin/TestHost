package com.example.demo.response.comment;

import com.example.demo.entity.CommentEntity;
import com.example.demo.response.user.UserSimpleResponse;
import lombok.Data;

import java.util.Date;

@Data
public class CommentResponse {

    private Long commentId;

    private String content;

    private UserSimpleResponse user;

    private Integer commentNumber;

    private Date createAt;

    private Date updateAt;

    public static CommentResponse init (CommentEntity entity) {
        CommentResponse response = new CommentResponse();
        response.setCommentId(entity.getCommentId());
        response.setContent(entity.getContent());
        response.setUser(UserSimpleResponse.init(entity.getUser()));
        response.setCommentNumber(entity.getCommentEntities().size());
        response.setCreateAt(entity.getCreateAt());
        response.setUpdateAt(entity.getUpdateAt());
        return response;
    }

}
