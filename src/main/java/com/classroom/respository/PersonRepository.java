package com.classroom.respository;

import com.classroom.entity.PersonEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<PersonEntity, Long> {

    @Query(value = "select * from person p inner join person_classroom pc using(person_id) where pc.classroom_id = :classroomId", nativeQuery = true)
    Page<PersonEntity> findByClassroom(Long classroomId, Pageable pageable);
}
