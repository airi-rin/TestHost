package com.classroom.controller;

import com.classroom.request.personClassroom.AddPersonClassroomRequest;
import com.classroom.service.PersonClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
