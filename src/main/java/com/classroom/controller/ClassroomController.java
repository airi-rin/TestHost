package com.classroom.controller;

import com.classroom.request.classroom.CreateClassroomRequest;
import com.classroom.response.classroom.ClassroomResponse;
import com.classroom.service.ClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/classrooms")
public class ClassroomController {

    @Autowired
    private ClassroomService classroomService;

    @GetMapping
    public ResponseEntity<Page<ClassroomResponse>> getAllClassroom(@RequestParam(name = "page", defaultValue = "1") int page,
                                                                   @RequestParam(name = "size", defaultValue = "10") int size) {
        return classroomService.getAllClassroom(page, size);
    }

    @GetMapping("/person/{id}")
    public ResponseEntity<Page<ClassroomResponse>> getClassroomOfPerson(@PathVariable(name = "id") Long personId,
                                                                        @RequestParam(name = "page", defaultValue = "1") int page,
                                                                        @RequestParam(name = "size", defaultValue = "10") int size) {
        return classroomService.getClassroomOfPerson(personId, page, size);
    }

    @GetMapping("/my-classroom")
    public ResponseEntity<Page<ClassroomResponse>> getMyClassroom(@RequestParam(name = "page", defaultValue = "1") int page,
                                                                  @RequestParam(name = "size", defaultValue = "10") int size) {
        return classroomService.getMyClassroom(page, size);
    }

    @PostMapping
    public ResponseEntity<String> addClassroom(@RequestBody CreateClassroomRequest createClassroomRequest) {
        return classroomService.createClassroom(createClassroomRequest);
    }

    @DeleteMapping("/{classroomId}")
    public ResponseEntity<String> deleteClassroom(@PathVariable(name = "classroomId") Long classroomId) {
        return classroomService.deleteClassroom(classroomId);
    }
}
