package com.example.demo.request.comment;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateCommentRequest {

    @NotBlank(message = "Content is not blank")
    private String content;

    @NotBlank(message = "Parent is post ? is not blank")
    private Boolean parentIsPost;

    @NotBlank(message = "Parent id is not blank")
    private Long parentId;

}
