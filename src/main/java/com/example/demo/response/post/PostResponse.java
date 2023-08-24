package com.example.demo.response.post;

import com.example.demo.entity.PostEntity;
import com.example.demo.response.user.UserSimpleResponse;
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

    private UserSimpleResponse user;

    public static PostResponse init(PostEntity entity) {
        PostResponse response = new PostResponse();
        response.setPostId(entity.getPostId());
        response.setPostTitle(entity.getPostTitle());
        response.setPostContent(entity.getPostContent());
        response.setCommentNumber(entity.getCommentEntities().size());
        response.setCreateAt(entity.getCreateAt());
        response.setUpdateAt(entity.getUpdateAt());
        response.setUser(UserSimpleResponse.init(entity.getUser()));
        return response;
    }
}
