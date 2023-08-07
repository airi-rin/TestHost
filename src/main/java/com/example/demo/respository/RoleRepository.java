package com.example.demo.respository;

import com.example.demo.entity.ERole;
import com.example.demo.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleEntity, Integer> {

    RoleEntity findByName(ERole rolename);
}
