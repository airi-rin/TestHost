package com.example.demo.controller;

import com.example.demo.entity.RoleEntity;
import com.example.demo.respository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    RoleRepository roleRepository;

    @GetMapping
    public ResponseEntity getAllRole() {
        return ResponseEntity.ok(roleRepository.findAll());
    }

    @PostMapping
    public ResponseEntity addRole(@RequestBody RoleEntity role) {
        RoleEntity exRole = roleRepository.findByName(role.getName());
        if(exRole == null)
            return ResponseEntity.ok(roleRepository.save(role));
        return ResponseEntity.ok(exRole);
    }
}
