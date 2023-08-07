package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/user")
    public ResponseEntity testUser() {
        return ResponseEntity.ok("Role User");
    }

    @PostMapping("/guest")
    public ResponseEntity testGuest() {
        return ResponseEntity.ok("Role Guest");
    }

    @GetMapping("/admin")
    public ResponseEntity testAdmin() {
        return ResponseEntity.ok("Role Admin");
    }
}
