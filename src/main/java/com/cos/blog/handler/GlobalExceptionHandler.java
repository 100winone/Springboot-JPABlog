package com.cos.blog.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice // 모든 Exception이 발생하면 이 클래스로 들어옴, 아래 value에 해당하는 Exception들은 저기로 감
@RestController
public class GlobalExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    public String handleArgumentException(Exception e) {
        return "<h1>" + e.getMessage() + "</h1 >";
    }
}