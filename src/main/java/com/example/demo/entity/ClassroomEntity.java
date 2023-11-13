package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "classroom")
@EntityListeners(AuditingEntityListener.class)
public class ClassroomEntity {

    @Id
    @Column(name = "classroom_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long classroomId;

    @Column(name = "classroom_title", nullable = false)
    private String classroomTitle;

    @Column(name = "resources")
    private String resource;

    @OneToMany(mappedBy = "classroom", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<PersonClassroomEntity> personClassroomEntities;

    @Column(name = "create_at", columnDefinition = "date", nullable = false)
    @CreatedDate
    private Date createAt;

    @Column(name = "update_at", columnDefinition = "date", nullable = false)
    @LastModifiedDate
    private Date updateAt;
}
