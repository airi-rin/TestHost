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
    @NotBlank(message = "name {validate.notBlank}")
    private String name;

    @Email
    @NotBlank(message = "Email {validate.notBlank}")
    private String email;

    @NotNull(message = "Gender {validate.notBlank}")
    @Enumerated(EnumType.STRING)
    private EGender gender;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Role {validate.notBlank}")
    private ERole role;

    @NotBlank(message = "Password {validate.notBlank}")
    private String password;
}
