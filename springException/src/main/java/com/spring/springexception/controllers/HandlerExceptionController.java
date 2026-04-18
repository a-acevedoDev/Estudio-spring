package com.spring.springexception.controllers;

import com.spring.springexception.Exceptions.UserNotFoundException;
import com.spring.springexception.models.Error;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class HandlerExceptionController {

    @ExceptionHandler({ArithmeticException.class})
    public ResponseEntity<Error> divisionByZero(Exception e) {
        Error error = new Error();
        error.setError("Error division por 0");
        error.setMessage(e.getMessage());
        error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        error.setDate(new Date());
        return ResponseEntity.internalServerError().body(error);
        //return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).body(error);
        //Otra manera de hacer el return ResponseEntity.
    }

    @ExceptionHandler({NoResourceFoundException.class})
    //NoResourceFoundException es la nueva manera de manejar error 404. Antes NoHandlerFoundException.
    public ResponseEntity<Error> notFoundEx(Exception e) {
        Error error = new Error();
        error.setDate(new Date());
        error.setError("Api no encontrada");
        error.setMessage(e.getMessage());
        error.setStatus(HttpStatus.NOT_FOUND.value());
        return ResponseEntity.status(404).body(error);
    }

    @ExceptionHandler({NumberFormatException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, Object> numberFormatException(Exception e) {
        Map<String, Object> error = new HashMap<>();
        error.put("date", new Date());
        error.put("error", "Error de formato numerico.");
        error.put("message", e.getMessage());
        error.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        return error;
    }

    @ExceptionHandler({
            NullPointerException.class,
            HttpMessageNotWritableException.class,
            UserNotFoundException.class,
            NoSuchElementException.class
            //Doble control de excepciones. Obligatorio usar {}.
    })
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, Object> nullPointerException(Exception e) {
        Map<String, Object> error = new HashMap<>();
        error.put("date", new Date());
        error.put("error", "El usuario o rol no exite o es nulo.");
        error.put("message", e.getMessage());
        error.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        return error;
    }
}
