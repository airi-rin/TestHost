package com.classroom.respository;

import com.classroom.entity.PersonEntity;
import com.classroom.entity.ClassroomEntity;
import com.classroom.entity.PersonClassroomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonClassroomRepository extends JpaRepository<PersonClassroomEntity, Long> {

    Optional<PersonClassroomEntity> findByPersonAndClassroom(PersonEntity personId, ClassroomEntity classroomId);

}
