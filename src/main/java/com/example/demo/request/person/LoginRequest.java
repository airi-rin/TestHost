package com.example.demo.request.person;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginRequest {

    @NotBlank(message = "Username {validate.notBlank}")
    private String username;

    @NotBlank(message = "Password {validate.notBlank}")
    private String password;
}
