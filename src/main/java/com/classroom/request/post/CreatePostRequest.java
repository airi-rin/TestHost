package com.classroom.request.post;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreatePostRequest {

    @NotBlank(message = "Title {validate.notBlank}")
    private String postTitle;

    @NotBlank(message = "Content {validate.notBlank}")
    private String postContent;

    @NotNull(message = "Class {validate.notBlank}")
    private Long classroomId;
}
