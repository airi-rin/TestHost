package com.example.demo.service;

import com.example.demo.auth.AuthService;
import com.example.demo.config.Message;
import com.example.demo.constant.MessageConstant;
import com.example.demo.entity.ClassroomEntity;
import com.example.demo.entity.PersonClassroomEntity;
import com.example.demo.entity.PersonClassroomKey;
import com.example.demo.entity.PersonEntity;
import com.example.demo.request.personClassroom.AddPersonClassroomRequest;
import com.example.demo.response.person.PersonResponse;
import com.example.demo.respository.ClassroomRepository;
import com.example.demo.respository.PersonClassroomRepository;
import com.example.demo.respository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonClassroomService {

    @Autowired
    private PersonClassroomRepository personClassroomRepository;

    @Autowired
    private ClassroomService classroomService;

    @Autowired
    private PersonService personService;

    @Autowired
    private AuthService authService;

    @Transactional
    public ResponseEntity<String> addPersonClassroom(AddPersonClassroomRequest addPersonClassroomRequest) {
        PersonEntity person = personService.getPerson(addPersonClassroomRequest.getPersonId());
        ClassroomEntity classroom = classroomService.getClassroom(addPersonClassroomRequest.getClassroomId());
        PersonClassroomEntity personClassroom = createPersonClassroom(person, classroom);
        personClassroomRepository.save(personClassroom);
        return ResponseEntity.ok(Message.getMessage(MessageConstant.SUCCESS_CREATE, "person in classroom"));
    }

    public PersonClassroomEntity createPersonClassroom(PersonEntity person, ClassroomEntity classroom) {
        PersonClassroomEntity personClassroom = new PersonClassroomEntity();
        personClassroom.setPerson(person);
        personClassroom.setClassroom(classroom);

        PersonClassroomKey key = new PersonClassroomKey();
        key.setClassroomId(classroom.getClassroomId());
        key.setPersonId(person.getPersonId());
        personClassroom.setId(key);

        if (person.getPersonClassroomEntities() == null) {
            List<PersonClassroomEntity> list = new ArrayList<>();
            list.add(personClassroom);
            person.setPersonClassroomEntities(list);
        } else {
            person.getPersonClassroomEntities().add(personClassroom);
        }

        if (classroom.getPersonClassroomEntities() == null) {
            List<PersonClassroomEntity> list = new ArrayList<>();
            list.add(personClassroom);
            classroom.setPersonClassroomEntities(list);
        } else {
            classroom.getPersonClassroomEntities().add(personClassroom);
        }

        return personClassroom;
    }

    public PersonClassroomEntity getPersonClassroom(PersonEntity person, ClassroomEntity classroom) {
        Optional<PersonClassroomEntity> optional = personClassroomRepository.findByPersonAndClassroom(person, classroom);
        if (optional.isEmpty()) {
            throw new IllegalArgumentException(Message.getMessage(MessageConstant.NOT_EXIST, "person in classroom"));
        }
        return optional.get();
    }
}
