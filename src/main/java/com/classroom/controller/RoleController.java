package com.classroom.controller;

import com.classroom.respository.RoleRepository;
import com.classroom.entity.RoleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
//@PreAuthorize("hasRole('ADMIN')")
public class RoleController {

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping
    public ResponseEntity<List<RoleEntity>> getAllRole() {
        return ResponseEntity.ok(roleRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<RoleEntity> addRole(@RequestBody RoleEntity role) {
        RoleEntity exRole = roleRepository.findByRoleName(role.getRoleName());
        if(exRole == null)
            return ResponseEntity.ok(roleRepository.save(role));
        return ResponseEntity.ok(exRole);
    }
}
