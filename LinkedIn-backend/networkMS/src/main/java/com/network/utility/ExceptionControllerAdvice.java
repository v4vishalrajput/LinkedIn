package com.network.utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.network.exception.LinkedInNetworkException;

@RestControllerAdvice
public class ExceptionControllerAdvice {
    @Autowired
    private Environment environment;
    @ExceptionHandler(value = LinkedInNetworkException.class)
    public ResponseEntity<Object> exception(LinkedInNetworkException exception) {
        return new ResponseEntity<>(environment.getProperty(exception.getMessage()), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> exception(Exception exception) {
        return new ResponseEntity<>(environment.getProperty("General.EXCEPTION_MESSAGE")+exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

