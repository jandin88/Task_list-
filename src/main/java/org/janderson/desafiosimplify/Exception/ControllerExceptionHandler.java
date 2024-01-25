package org.janderson.desafiosimplify.Exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandartError> handleValidationExceptions(MethodArgumentNotValidException e,HttpServletRequest request){
        HttpStatus httpStatus=HttpStatus.BAD_REQUEST;
        StandartError error= new StandartError(System.currentTimeMillis(),httpStatus.value(),"Not Found",e.getMessage(),request.getRequestURI());

        return ResponseEntity.status(httpStatus).body(error);
    }
    @ExceptionHandler(TaskElementExist.class)
    public ResponseEntity<StandartError> elementFoundCrest(TaskElementExist e, HttpServletRequest request){
        HttpStatus httpStatus=HttpStatus.CONFLICT;
        StandartError error= new StandartError(System.currentTimeMillis(),httpStatus.value(),"Element Exist",e.getMessage(),request.getRequestURI());
        return ResponseEntity.status(httpStatus).body(error);
    }
}
