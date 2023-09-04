package com.example.demo.request.comment;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdateCommentRequest {

    @NotBlank(message = "Content {validate.notBlank}")
    private String content;
}
