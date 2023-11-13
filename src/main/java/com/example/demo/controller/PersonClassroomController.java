package com.example.demo.controller;

import com.example.demo.request.personClassroom.AddPersonClassroomRequest;
import com.example.demo.response.person.PersonResponse;
import com.example.demo.service.PersonClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/person-classroom")
public class PersonClassroomController {

    @Autowired
    private PersonClassroomService personClassroomService;

    @PostMapping
    public ResponseEntity<String> addPersonClassroom(@RequestBody AddPersonClassroomRequest addPersonClassroomRequest) {
        return personClassroomService.addPersonClassroom(addPersonClassroomRequest);
    }

}
