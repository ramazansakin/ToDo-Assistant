package com.sakinramazan.todoassistant.todoservice.interceptor;

import com.sakinramazan.todoassistant.todoservice.exception.InvalidFieldException;
import com.sakinramazan.todoassistant.todoservice.exception.NotAllowedApiException;
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
        Map<String, String> response = prepareResponse(
                exception.getMessage(),
                "Please enter a valid entity",
                HttpStatus.NOT_FOUND.toString());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidFieldException.class)
    public ResponseEntity<Map> exception(InvalidFieldException exception) {
        Map<String, String> response = prepareResponse(
                exception.getMessage(),
                "Please ensure the entity has proper fields and values",
                HttpStatus.BAD_REQUEST.toString());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotAllowedApiException.class)
    public ResponseEntity<Map> exception(NotAllowedApiException exception) {
        Map<String, String> response = prepareResponse(
                exception.getMessage(),
                "Sorry for internal server error",
                HttpStatus.BAD_REQUEST.toString());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    // If not found specific exception, use this
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> exception(Exception exception) {
        return new ResponseEntity<>("Generic Exception details : " + exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private Map<String, String> prepareResponse(String error, String solution, String status) {
        // You can define any other class for better visualization for response
        Map<String, String> response = new HashMap<>();
        response.put("Cause", error);
        response.put("Solution", solution);
        response.put("Status", status);
        return response;
    }

}