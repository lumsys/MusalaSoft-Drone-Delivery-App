package com.musala.codetest.exception;

import com.musala.codetest.payload.Response.MessageResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

public class ExceptionHelper {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionHandler.class);

    @ExceptionHandler(value = { RuntimeException.class })
    public ResponseEntity<MessageResponse> handleInvalidInputException(RuntimeException ex) {

        logger.error("Invalid Input Exception: ", ex.getMessage());

        System.out.println("exception is "+ ex.getMessage());

        return new ResponseEntity<MessageResponse>(new MessageResponse("failed",ex.getMessage(),java.time.LocalDateTime.now()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = { HttpClientErrorException.Unauthorized.class })

    public ResponseEntity<MessageResponse> handleUnauthorizedException(HttpClientErrorException.Unauthorized ex) {

        logger.error("Unauthorized Exception: ", ex.getMessage());

        return new ResponseEntity<MessageResponse>(new MessageResponse("failed",ex.getMessage(),java.time.LocalDateTime.now()), HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(value = { Exception.class })

    public ResponseEntity<MessageResponse> handleException(Exception ex) {

        logger.error("Exception: ", ex.getMessage());
        return new ResponseEntity<MessageResponse>(new MessageResponse("failed",ex.getMessage(),java.time.LocalDateTime.now()), HttpStatus.INTERNAL_SERVER_ERROR);

    }
}
