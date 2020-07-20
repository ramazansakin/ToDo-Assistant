package com.sakinramazan.todoassistant.gatewayservice.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GenericExceptionHandler {
    // If not found specific exception, use this
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> exception(RuntimeException exception) {
        return new ResponseEntity<>("Generic Exception details : " + exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
