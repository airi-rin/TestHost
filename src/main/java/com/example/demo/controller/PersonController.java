package com.example.demo.controller;

import com.example.demo.request.person.CreatePersonRequest;
import com.example.demo.service.PersonService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/persons")
@PreAuthorize("hasRole('ADMIN')")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping
    public ResponseEntity getAllPerson() {
        return ResponseEntity.ok(personService.getAllPerson());
    }

    @PostMapping
    public ResponseEntity addPerson(@RequestBody @Valid CreatePersonRequest createPersonRequest) {
        return ResponseEntity.ok(personService.addPerson(createPersonRequest));
    }
}
