package com.sakinramazan.userservice.interceptor;

import com.fasterxml.jackson.core.JsonParseException;
import com.sakinramazan.userservice.exception.NotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ValidationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@ControllerAdvice
public class GenericExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({
            ValidationException.class,
            JsonParseException.class,
            NotFoundException.class
    })
    public ResponseEntity<Map> exception(ValidationException exception) {
        Map<String, String> response = prepareResponse(
                exception.getCause().getMessage(),
                "Please enter a valid entity with proper constraints",
                HttpStatus.BAD_REQUEST.toString());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    // Generic validation error handler method
    @Override
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        final List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        Map<String, Set<String>> errorsMap = fieldErrors.stream().collect(
                Collectors.groupingBy(FieldError::getField,
                        Collectors.mapping(FieldError::getDefaultMessage, Collectors.toSet())
                )
        );
        Map<String, String> response = prepareResponse(
                (errorsMap.isEmpty() ? ex.getMessage() : errorsMap.toString()),
                "Please enter a valid entity with proper constraints",
                HttpStatus.BAD_REQUEST.toString());
        return new ResponseEntity<>(response, status);
    }

    // If not found specific exception, use this
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> exception(Exception exception) {
        Map<String, String> response = prepareResponse(
                exception.getMessage(),
                "Sorry for the error, please try again later or contact with IT of bla-bla",
                HttpStatus.INTERNAL_SERVER_ERROR.toString());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
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