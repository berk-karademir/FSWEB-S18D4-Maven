package com.workintech.s18d1.exceptions;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
@AllArgsConstructor
public class GlobalExceptionHandler {


    @ExceptionHandler
    public ResponseEntity<BurgerErrorResponse> handleException(BurgerException exception) {
        log.error("'A burger exception' has occured, details: ", exception.getLocalizedMessage());
        BurgerErrorResponse response = new BurgerErrorResponse(exception.getLocalizedMessage());
        return new ResponseEntity<>(response,exception.getHttpStatus());
    }


    @ExceptionHandler
    public ResponseEntity<BurgerErrorResponse> handleException(Exception exception) {
        log.error("'An unexpected Burger exception' has occured, details: ", exception.getLocalizedMessage());
        BurgerErrorResponse response = new BurgerErrorResponse(exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
