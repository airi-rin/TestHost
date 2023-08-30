package com.example.demo.controller;

import com.example.demo.entity.PersonEntity;
import com.example.demo.request.person.CreatePersonRequest;
import com.example.demo.response.person.PersonResponse;
import com.example.demo.service.PersonService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/persons")
@PreAuthorize("hasRole('ADMIN')")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping
    public ResponseEntity<List<PersonResponse>> getAllPerson() {
        return personService.getAllPerson();
    }

    @PostMapping
    public ResponseEntity<PersonResponse> addPerson(@RequestBody @Valid CreatePersonRequest createPersonRequest) {
        return personService.addPerson(createPersonRequest);
    }
}
