package com.example.demo.request.post;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreatePostRequest {

    @NotBlank(message = "Title is not blank")
    private String postTitle;

    @NotBlank(message = "Content is not blank")
    private String postContent;
}
