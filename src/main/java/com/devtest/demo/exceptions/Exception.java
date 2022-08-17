package com.devtest.demo.exceptions;

import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class Exception {
    @ExceptionHandler({FeignException.FeignClientException.class})
    public ResponseEntity<String> handlerNotFound(FeignException.FeignClientException e){
        return new ResponseEntity<String>("Product ID not found", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({FeignException.NotAcceptable.class})
    public ResponseEntity<String> handlerException(FeignException e){
        return new ResponseEntity<String>("Exception", HttpStatus.NOT_ACCEPTABLE );
    }

    @ExceptionHandler({FeignException.NotFound.class})
    public ResponseEntity<String> handlerNotFound(FeignException e){
        return new ResponseEntity<String>("Product not found", HttpStatus.NOT_FOUND);
    }

}
