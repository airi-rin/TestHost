package com.example.demo.request.classroom;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateClassroomRequest {

    @NotBlank(message = "Classroom title {validate.notBlank}")
    private String classroomTitle;

    @NotBlank(message = "Resource {validate.notBlank}")
    private String resource;

}
