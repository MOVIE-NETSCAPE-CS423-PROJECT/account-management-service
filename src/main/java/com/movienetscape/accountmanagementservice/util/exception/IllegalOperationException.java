package com.movienetscape.accountmanagementservice.util.exception;

public class IllegalOperationException extends RuntimeException {

   private String message;

    public IllegalOperationException(String message) {
        super(message);
    }
}
