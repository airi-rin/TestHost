package com.example.demo.response.person;

import com.example.demo.entity.EGender;
import com.example.demo.entity.ERole;
import com.example.demo.auth.UserEntity;
import com.example.demo.entity.PersonEntity;
import lombok.Data;

@Data
public class PersonResponse {

    private Long id;

    private String name;

    private String email;

    private EGender gender;

    private ERole role;

    public static PersonResponse init(PersonEntity entity) {
        PersonResponse response = new PersonResponse();
        response.setId(entity.getPersonId());
        response.setName(entity.getName());
        response.setEmail(entity.getEmail());
        response.setGender(entity.getGender());
        response.setRole(entity.getUser().getRole().getRoleName());
        return response;
    }
}
