package com.example.demo.response.user;

import com.example.demo.entity.EGender;
import com.example.demo.entity.ERole;
import com.example.demo.entity.UserEntity;
import lombok.Data;

@Data
public class UserResponse {

    private Long id;

    private String username;

    private String email;

    private EGender gender;

    private ERole role;

    public static UserResponse init(UserEntity entity) {
        UserResponse response = new UserResponse();
        response.setId(entity.getId());
        response.setUsername(entity.getUsername());
        response.setEmail(entity.getEmail());
        response.setGender(entity.getGender());
        response.setRole(entity.getRole().getRoleName());
        return response;
    }
}
