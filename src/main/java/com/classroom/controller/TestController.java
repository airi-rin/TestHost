package com.classroom.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tests")
public class TestController {

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<String> testUser() {
        return ResponseEntity.ok("Role User");
    }

    @GetMapping("/guest")
    public ResponseEntity<String> testGuest() {
        return ResponseEntity.ok("Role Guest");
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> testAdmin() {
        return ResponseEntity.ok("Role Admin");
    }
}
