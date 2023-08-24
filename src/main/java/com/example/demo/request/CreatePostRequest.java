package com.example.demo.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreatePostRequest {

    @NotBlank(message = "Title is not blank")
    private String title;

    @NotBlank(message = "Content is not blank")
    private String content;
}
