package com.classroom.response.person;

import com.classroom.entity.PersonEntity;
import lombok.Data;

@Data
public class PersonSimpleResponse {

    private Long id;

    private String name;

    public static PersonSimpleResponse init(PersonEntity entity) {
        PersonSimpleResponse response = new PersonSimpleResponse();
        response.setId(entity.getPersonId());
        response.setName(entity.getName());
        return response;
    }
}
