package com.example.demo.request.person;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginRequest {

    @NotBlank(message = "User name {validate.notBlank}")
    private String username;

    @NotBlank(message = "Password {validate.notBlank}")
    private String password;
}
