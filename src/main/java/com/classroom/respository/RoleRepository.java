package com.classroom.respository;

import com.classroom.entity.ERole;
import com.classroom.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

    RoleEntity findByRoleName(ERole rolename);
}
