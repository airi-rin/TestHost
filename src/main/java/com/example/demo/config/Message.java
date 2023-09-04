package com.example.demo.config;

import com.example.demo.constant.MessageConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

public class Message {

    private static MessageSource messageSource;

    public static void init (MessageSource messageSource) {
        Message.messageSource = messageSource;
    }

//    public static String getMessage(String code) {
//        return messageSource.getMessage(code, null, Locale.US);
//    }

    public static String getMessage(String code, Object... args) {

        return messageSource.getMessage(code, args, Locale.US);
    }
}
