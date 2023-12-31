package com.classroom.entity;

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
@Table(name = "post")
@EntityListeners(AuditingEntityListener.class)
public class PostEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long postId;

    @Column(name = "post_title", nullable = false)
    private String postTitle;

    @Column(name = "post_content", nullable = false)
    private String postContent;

    @ManyToOne
    @JoinColumns({
        @JoinColumn(name = "person_id", referencedColumnName = "person_id"),
        @JoinColumn(name = "classroom_id", referencedColumnName = "classroom_id")
    })
    private PersonClassroomEntity personClassroom;

    @OneToMany(mappedBy = "post")
    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<CommentEntity> commentEntities;

    @Column(name = "create_at", nullable = false)
    @CreatedDate
    private Date createAt;

    @Column(name = "update_at", nullable = false)
    @LastModifiedDate
    private Date updateAt;

}
