package com.service.account.exceptionhandler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity handleHttpClientErrorException(HttpClientErrorException httpClientErrorException) {
        if (httpClientErrorException.getRawStatusCode() == 404) {
            log.debug("ControllerExceptionHandler : handleHttpClientErrorException : Customer Not Found");
            return new ResponseEntity("Account cannot be created, Customer not found", HttpStatus.NOT_FOUND);
        }
        log.error("ControllerExceptionHandler : handleHttpClientErrorException : exception occurred, {}",
                httpClientErrorException);
        return new ResponseEntity("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity handleNumberFormatException(NumberFormatException numberFormatException) {
        log.debug("ControllerExceptionHandler : handleNumberFormatException : Bad Request, invalid customer id : {}",
                numberFormatException);
        return new ResponseEntity("Bad Request, invalid customer id", HttpStatus.BAD_REQUEST);
    }
}