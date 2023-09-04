package com.example.demo.request.person;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ChangePasswordRequest {

    @NotBlank(message = "Current password {validate.notBlank}")
    private String currentPassword;

    @NotBlank(message = "New password {validate.notBlank}")
    private String newPassword;
}
