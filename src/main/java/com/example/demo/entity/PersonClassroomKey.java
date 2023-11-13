package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Embeddable
@Data
@EqualsAndHashCode
public class PersonClassroomKey implements Serializable {
    @Column(name = "person_id")
    private Long personId;

    @Column(name = "classroom_id")
    private Long classroomId;
}
