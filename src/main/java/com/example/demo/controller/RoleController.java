package com.example.demo.controller;

import com.example.demo.entity.RoleEntity;
import com.example.demo.respository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/roles")
@PreAuthorize("hasRole('ADMIN')")
public class RoleController {

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping
    public ResponseEntity getAllRole() {
        return ResponseEntity.ok(roleRepository.findAll());
    }

    @PostMapping
    public ResponseEntity addRole(@RequestBody RoleEntity role) {
        RoleEntity exRole = roleRepository.findByRoleName(role.getRoleName());
        if(exRole == null)
            return ResponseEntity.ok(roleRepository.save(role));
        return ResponseEntity.ok(exRole);
    }
}
