package org.janderson.desafiosimplify.controllers.Exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<StandartError> elementNotfound(NoSuchElementException e, HttpServletRequest request){
        HttpStatus httpStatus=HttpStatus.NOT_FOUND;
        StandartError error= new StandartError(System.currentTimeMillis(),httpStatus.value(),"Not Found",e.getMessage(),request.getRequestURI());

        return ResponseEntity.status(httpStatus).body(error);
    }
}
