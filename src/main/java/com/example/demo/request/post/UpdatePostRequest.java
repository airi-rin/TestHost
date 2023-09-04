package com.example.demo.request.post;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdatePostRequest {

    @NotBlank(message = "Title {validate.notBlank}")
    private String postTitle;

    @NotBlank(message = "Content {validate.notBlank}")
    private String postContent;
}
