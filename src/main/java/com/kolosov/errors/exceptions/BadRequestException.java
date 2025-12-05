package com.kolosov.errors.exceptions;

public class BadRequestException extends AppException {
    public BadRequestException(String msg) {
        super(msg);
    }
}