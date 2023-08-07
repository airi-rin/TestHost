package com.example.demo.request;

import jakarta.persistence.Column;
import lombok.Data;

import java.util.Date;

@Data
public class CreatePostRequest {
    private String title;

    private String content;

    //private Date createAt;
}
