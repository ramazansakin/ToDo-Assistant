package com.sakinramazan.todoassistant.todoservice.interceptor;

import com.sakinramazan.todoassistant.todoservice.exception.InvalidFieldException;
import com.sakinramazan.todoassistant.todoservice.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GenericExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Map> exception(NotFoundException exception) {
        // You can define any other class for better visualization for response
        Map<String, String> response = new HashMap<>();
        response.put("Caused by", exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidFieldException.class)
    public ResponseEntity<Map> exception(InvalidFieldException exception) {
        Map<String, String> response = new HashMap<>();
        response.put("Caused by", exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    // If not found specific exception, use this
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> exception(Exception exception) {
        return new ResponseEntity<>("Generic Exception details : " + exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}