package com.example.demo.request.person;

import com.example.demo.entity.EGender;
import com.example.demo.entity.ERole;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreatePersonRequest {
    @NotBlank(message = "name {validator.NotBlank.message}")
    private String name;

    @Email
    @NotBlank(message = "Email is not blank")
    private String email;

    @NotNull(message = "Gender is not blank")
    @Enumerated(EnumType.STRING)
    private EGender gender;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "role is not blank")
    private ERole role;

    @NotBlank(message = "password {validator.NotBlank.message}")
    private String password;
}
