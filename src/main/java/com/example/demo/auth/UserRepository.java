package com.example.demo.auth;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Propagation;
//import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    //@Transactional(propagation = Propagation.REQUIRES_NEW)
    UserEntity findByUsername(String username);

    UserEntity findById(int id);
}
