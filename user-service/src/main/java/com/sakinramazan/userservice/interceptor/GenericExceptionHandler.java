package com.sakinramazan.userservice.interceptor;

import com.fasterxml.jackson.core.JsonParseException;
import com.sakinramazan.userservice.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ValidationException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GenericExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Map> exception(NotFoundException exception) {
        Map<String, String> response = prepareResponse(
                exception.getMessage(),
                "Please enter a valid entity");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(JsonParseException.class)
    public ResponseEntity<Map> exception(JsonParseException exception) {
        Map<String, String> response = prepareResponse(
                exception.getCause().getMessage(),
                "Please ensure the entity has proper fields and values");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<Map> exception(ValidationException exception) {
        Map<String, String> response = prepareResponse(
                exception.getCause().getMessage(),
                "Please enter a valid entity with proper constraints");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    // If not found specific exception, use this
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> exception(Exception exception) {
        Map<String, String> response = prepareResponse(
                exception.getMessage(),
                "Sorry for internal server error");
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private Map<String, String> prepareResponse(String error, String solution) {
        // You can define any other class for better visualization for response
        Map<String, String> response = new HashMap<>();
        response.put("Cause", error);
        response.put("Solution", solution);
        return response;
    }

}
