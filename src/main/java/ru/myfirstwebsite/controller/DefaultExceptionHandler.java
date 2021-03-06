package ru.myfirstwebsite.controller;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.myfirstwebsite.controller.messages.ErrorMessage;

import javax.naming.AuthenticationException;


@ControllerAdvice
public class DefaultExceptionHandler {

    private static final Logger LOG = Logger.getLogger(DefaultExceptionHandler.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> handleNonSuchEntityException(MethodArgumentNotValidException e) {
       LOG.error(e.getMessage(), e);
       return new ResponseEntity<>(new ErrorMessage(e.getMessage()),
               HttpStatus.UNPROCESSABLE_ENTITY);
    }

//    @ExceptionHandler(NoSuchEntityException.class)
//    public ResponseEntity<ErrorMessage> handleNoSuchEntityException(NoSuchEntityException e) {
//        LOG.error(e.getMessage(), e);
//        return new ResponseEntity<>(new ErrorMessage(e.getMessage()),
//                HttpStatus.NOT_FOUND);
//    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorMessage> handleAuthenticationException(AuthenticationException e){
        LOG.error(e.getMessage(), e);
        return new ResponseEntity<>(new ErrorMessage(e.getMessage()), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ErrorMessage> handleNPException(NullPointerException e) {
        LOG.error(e.getMessage(), e);
        return new ResponseEntity<>(new ErrorMessage(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> handleOtherException(Exception e) {
        /* Handles all other exceptions. Status code 500. */
        LOG.error(e.getMessage(), e);
        return new ResponseEntity<>(new ErrorMessage(e.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
