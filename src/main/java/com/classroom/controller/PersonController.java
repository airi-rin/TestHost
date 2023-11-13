package com.classroom.controller;

import com.classroom.request.person.ChangePasswordRequest;
import com.classroom.request.person.CreatePersonRequest;
import com.classroom.response.person.PersonResponse;
import com.classroom.service.PersonService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/persons")
//@PreAuthorize("hasRole('ADMIN')")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping
    public ResponseEntity<Page<PersonResponse>> getAllPerson(@RequestParam(name = "page", defaultValue = "1") int page,
                                                             @RequestParam(name = "size", defaultValue = "10") int size) {
        return personService.getAllPerson(page, size);
    }

    @PostMapping
    public ResponseEntity<PersonResponse> addPerson(@RequestBody @Valid CreatePersonRequest createPersonRequest) {
        return personService.addPerson(createPersonRequest);
    }

    @PutMapping("/change-password")
    public ResponseEntity<String> changePassword(@Valid @RequestBody ChangePasswordRequest changePasswordRequest) {
        return personService.changePassword(changePasswordRequest);
    }

    @GetMapping("/classroom/{id}")
    public ResponseEntity<Page<PersonResponse>> getPersonInClassroom(@PathVariable(name = "id") Long classroomId,
                                                                     @RequestParam(name = "page", defaultValue = "1") int page,
                                                                     @RequestParam(name = "size", defaultValue = "10") int size) {
        return personService.getPersonInClassroom(classroomId, page, size);
    }
}
