package com.example.demo.request;

import com.example.demo.entity.EGender;
import com.example.demo.entity.ERole;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
//import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateUserRequest {
    //@NotBlank(message = "username {validator.NotBlank.message}")
    private String username;
    //@NotBlank(message = "password {validator.NotBlank.message}")
    private String password;
    private String email;
    @Enumerated(EnumType.STRING)
    private EGender gender;
    @Enumerated(EnumType.STRING)
    //@NotBlank(message = "role {validator.NotBlank.message}")
    private ERole role;
}
