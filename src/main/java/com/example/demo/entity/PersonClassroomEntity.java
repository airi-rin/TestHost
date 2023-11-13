package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "person_classroom")
public class PersonClassroomEntity {

    @EmbeddedId
    PersonClassroomKey id;

    @JoinColumn(name = "person_id")
    @ManyToOne(cascade = CascadeType.ALL)
    @MapsId("personId")
    private PersonEntity person;

    @JoinColumn(name = "classroom_id")
    @ManyToOne(cascade = CascadeType.ALL)
    @MapsId("classroomId")
    private ClassroomEntity classroom;

    @OneToMany(mappedBy = "personClassroom", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<PostEntity> postEntities;

    @OneToMany(mappedBy = "personClassroom", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<CommentEntity> commentEntities;
}
