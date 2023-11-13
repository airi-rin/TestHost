package com.example.demo.response.post;

import com.example.demo.entity.PostEntity;
import com.example.demo.response.person.PersonSimpleResponse;
import lombok.Data;

import java.util.Date;

@Data
public class PostResponse {

    private Long postId;

    private String postTitle;

    private String postContent;

    private Integer commentNumber;

    private Date createAt;

    private Date updateAt;

    private PersonSimpleResponse person;

    public static PostResponse init(PostEntity entity) {
        PostResponse response = new PostResponse();
        response.setPostId(entity.getPostId());
        response.setPostTitle(entity.getPostTitle());
        response.setPostContent(entity.getPostContent());
        response.setCommentNumber(entity.getCommentEntities().size());
        response.setCreateAt(entity.getCreateAt());
        response.setUpdateAt(entity.getUpdateAt());
        response.setPerson(PersonSimpleResponse.init(entity.getPersonClassroom().getPerson()));
        return response;
    }
}
