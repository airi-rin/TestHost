package com.example.demo.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginRequest {

    @NotBlank(message = "User name is not blank")
    private String username;

    @NotBlank(message = "Password is not blank")
    private String password;
}
