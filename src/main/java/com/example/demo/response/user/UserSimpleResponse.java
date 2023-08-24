package com.example.demo.response.user;

import com.example.demo.entity.UserEntity;
import lombok.Data;

@Data
public class UserSimpleResponse {

    private Long id;

    private String username;

    public static UserSimpleResponse init(UserEntity entity) {
        UserSimpleResponse response = new UserSimpleResponse();
        response.setId(entity.getId());
        response.setUsername(entity.getUsername());
        return response;
    }
}
