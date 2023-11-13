package com.example.demo.response.classroom;

import com.example.demo.entity.ClassroomEntity;
import lombok.Data;

import java.util.Date;

@Data
public class ClassroomResponse {

    private Long classroomId;

    private String classroomTitle;

    private String resource;

    private Date createAt;

    private Date updateAt;

    public static ClassroomResponse init(ClassroomEntity entity) {
        ClassroomResponse response = new ClassroomResponse();
        response.setClassroomId(entity.getClassroomId());
        response.setClassroomTitle(entity.getClassroomTitle());
        response.setResource(entity.getResource());
        response.setCreateAt(entity.getCreateAt());
        response.setUpdateAt(entity.getUpdateAt());
        return response;
    }
}
