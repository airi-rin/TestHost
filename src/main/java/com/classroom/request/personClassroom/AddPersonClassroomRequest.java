package com.classroom.request.personClassroom;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddPersonClassroomRequest {

    @NotNull(message = "ClassroomId {validate.notBlank}")
    private Long classroomId;

    @NotNull(message = "personId {validate.notBlank}")
    private Long personId;
}
