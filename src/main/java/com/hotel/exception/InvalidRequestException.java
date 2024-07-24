package com.hotel.exception;

import org.springframework.http.HttpStatus;

public class InvalidRequestException extends RuntimeException {
    public InvalidRequestException(String message, HttpStatus badRequest) {
        super(message);
    }
}