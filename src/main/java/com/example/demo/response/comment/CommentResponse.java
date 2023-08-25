package com.example.demo.response.comment;

import com.example.demo.entity.CommentEntity;
import com.example.demo.response.person.PersonSimpleResponse;
import lombok.Data;

import java.util.Date;

@Data
public class CommentResponse {

    private Long commentId;

    private String content;

    private PersonSimpleResponse person;

    private Integer commentNumber;

    private Date createAt;

    private Date updateAt;

    public static CommentResponse init (CommentEntity entity) {
        CommentResponse response = new CommentResponse();
        response.setCommentId(entity.getCommentId());
        response.setContent(entity.getContent());
        response.setPerson(PersonSimpleResponse.init(entity.getPerson()));
        response.setCommentNumber(entity.getCommentEntities().size());
        response.setCreateAt(entity.getCreateAt());
        response.setUpdateAt(entity.getUpdateAt());
        return response;
    }

}
