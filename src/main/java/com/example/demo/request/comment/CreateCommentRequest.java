package com.example.demo.request.comment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateCommentRequest {

    @NotBlank(message = "Content {validate.notBlank}")
    private String content;

    @NotNull(message = "Parent is post ? {validate.notBlank}")
    private Boolean parentIsPost;

    @NotNull(message = "Parent id {validate.notBlank}")
    private Long parentId;

}
