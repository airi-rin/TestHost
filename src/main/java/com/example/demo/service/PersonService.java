package com.example.demo.service;

import com.example.demo.auth.AuthService;
import com.example.demo.auth.UserEntity;
import com.example.demo.auth.UserRepository;
import com.example.demo.config.Message;
import com.example.demo.constant.MessageConstant;
import com.example.demo.entity.PersonEntity;
import com.example.demo.entity.RoleEntity;
import com.example.demo.request.person.ChangePasswordRequest;
import com.example.demo.request.person.CreatePersonRequest;
import com.example.demo.response.person.PersonResponse;
import com.example.demo.respository.PersonRepository;
import com.example.demo.respository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthService authService;

    @Transactional
    public ResponseEntity<PersonResponse> addPerson(CreatePersonRequest createPersonRequest) {
        UserEntity user = new UserEntity();
        user.setUsername(createPersonRequest.getEmail());
        user.setPassword(new BCryptPasswordEncoder().encode(createPersonRequest.getPassword()));
        RoleEntity role = roleRepository.findByRoleName(createPersonRequest.getRole());
        user.setRole(role);

        PersonEntity person = new PersonEntity();
        person.setName(createPersonRequest.getName());
        person.setEmail(createPersonRequest.getEmail());
        person.setGender(createPersonRequest.getGender());
        person.setUser(user);
        user.setPerson(person);

        userRepository.save(user);

        return ResponseEntity.ok(PersonResponse.init(person));
    }

    public ResponseEntity<List<PersonResponse>> getAllPerson() {
        List<PersonEntity> personEntityList = personRepository.findAll();
        List<PersonResponse> personResponseList = personEntityList.stream()
                .map(PersonResponse::init)
                .collect(Collectors.toList());
        return ResponseEntity.ok(personResponseList);
    }

    public ResponseEntity<String> changePassword(ChangePasswordRequest changePasswordRequest) {
        UserEntity userEntity = authService.getUser();
        if (!new BCryptPasswordEncoder().matches(changePasswordRequest.getCurrentPassword(),userEntity.getPassword())) {
            return ResponseEntity.badRequest().body(Message.getMessage(MessageConstant.WRONG, "Current password"));
        }
        userEntity.setPassword(new BCryptPasswordEncoder().encode(changePasswordRequest.getNewPassword()));
        userRepository.save(userEntity);
        return ResponseEntity.ok(Message.getMessage(MessageConstant.SUCCESS_UPDATE, "password"));
    }
}
