package com.booking.schedule.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class Globalexceptionhandler {

    @ExceptionHandler
    public ResponseEntity<Map<String,String>> Exception(MethodArgumentNotValidException ex)
    {
        Map<String,String> mp= new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error->{
            String fieldname = error.getObjectName();
            String message = error.getDefaultMessage();
            mp.put(fieldname,message);
        });
        return new ResponseEntity<>(mp, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleOtherExceptions(Exception ex) {
        return new ResponseEntity<>("An unexpected error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
