package com.example.demo.request.user;

import com.example.demo.entity.EGender;
import com.example.demo.entity.ERole;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateUserRequest {
    @NotBlank(message = "username {validator.NotBlank.message}")
    private String username;

    @NotBlank(message = "password {validator.NotBlank.message}")
    private String password;

    @Email
    @NotBlank(message = "Email is not blank")
    private String email;

    @NotBlank(message = "Gender is not blank")
    @Enumerated(EnumType.STRING)
    private EGender gender;

    @Enumerated(EnumType.STRING)
    @NotBlank(message = "role is not blank")
    private ERole role;
}
