package com.classroom.respository;

import com.classroom.entity.ClassroomEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassroomRepository extends JpaRepository<ClassroomEntity, Long> {

    @Query(value = "select * from classroom c inner join person_classroom pc using(classroom_id) where pc.person_id = :personId", nativeQuery = true)
    Page<ClassroomEntity> findByPerson(Long personId, Pageable pageable);
}
