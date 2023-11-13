package com.example.demo.respository;

import com.example.demo.entity.ClassroomEntity;
import com.example.demo.entity.PersonClassroomEntity;
import com.example.demo.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonClassroomRepository extends JpaRepository<PersonClassroomEntity, Long> {

    Optional<PersonClassroomEntity> findByPersonAndClassroom(PersonEntity personId, ClassroomEntity classroomId);

}
