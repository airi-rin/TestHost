package com.classroom.service;

import com.classroom.config.Message;
import com.classroom.constant.MessageConstant;
import com.classroom.entity.ClassroomEntity;
import com.classroom.entity.PersonEntity;
import com.classroom.request.classroom.CreateClassroomRequest;
import com.classroom.response.classroom.ClassroomResponse;
import com.classroom.respository.ClassroomRepository;
import com.classroom.auth.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ClassroomService {

    @Autowired
    private ClassroomRepository classroomRepository;

    private PersonClassroomService personClassroomService;
    @Autowired
    public ClassroomService(@Lazy PersonClassroomService personClassroomService) {
        this.personClassroomService = personClassroomService;
    }

    @Autowired
    private AuthService authService;

    public ResponseEntity<Page<ClassroomResponse>> getAllClassroom(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by("update_at").descending());

        Page<ClassroomEntity> classroomEntityPage = classroomRepository.findAll(pageable);

        Page<ClassroomResponse> classroomResponsePage = new PageImpl<>(
                classroomEntityPage.get()
                        .map(ClassroomResponse::init)
                        .collect(Collectors.toList()),
                pageable, classroomEntityPage.getTotalElements()
        );

        return ResponseEntity.ok(classroomResponsePage);
    }

    public ResponseEntity<Page<ClassroomResponse>> getClassroomOfPerson(Long personId, int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by("update_at").descending());

        Page<ClassroomEntity> classroomEntityPage = classroomRepository.findByPerson(personId, pageable);

        List<ClassroomResponse> classroomEntityList = classroomEntityPage.stream()
                .map(ClassroomResponse::init)
                .toList();

        Page<ClassroomResponse> classroomResponsePage = new PageImpl<>(classroomEntityList,
                pageable, classroomEntityPage.getTotalElements());

        return ResponseEntity.ok(classroomResponsePage);
    }

    public ResponseEntity<Page<ClassroomResponse>> getMyClassroom(int page, int size) {
        return getClassroomOfPerson(authService.getPerson().getPersonId(), page, size);
    }

    @Transactional
    public ResponseEntity<String> createClassroom(CreateClassroomRequest classroomRequest) {
        ClassroomEntity classroomEntity = new ClassroomEntity();
        classroomEntity.setClassroomTitle(classroomRequest.getClassroomTitle());
        classroomEntity.setResource(classroomRequest.getResource());
        classroomEntity = classroomRepository.save(classroomEntity);

        PersonEntity teacher = authService.getPerson();
        personClassroomService.createPersonClassroom(teacher, classroomEntity);
        classroomRepository.save(classroomEntity);

        return ResponseEntity.ok(Message.getMessage(MessageConstant.SUCCESS_CREATE, "classroom"));
    }

    @Transactional
    public ResponseEntity<String> deleteClassroom(Long classroomId) {
        classroomRepository.deleteById(classroomId);
        return ResponseEntity.ok(Message.getMessage(MessageConstant.SUCCESS_DELETE, "classroom"));
    }

    public ClassroomEntity getClassroom(Long classroomId) {
        Optional<ClassroomEntity> optionalClassroom = classroomRepository.findById(classroomId);
        if (!optionalClassroom.isPresent()) {
            throw new IllegalArgumentException(Message.getMessage(MessageConstant.NOT_EXIST, "classroom"));
        }
        return optionalClassroom.get();
    }
}
