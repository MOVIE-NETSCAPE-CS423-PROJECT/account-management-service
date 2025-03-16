package com.movienetscape.accountmanagementservice.util.exception;

public class DuplicateException extends AccountServiceException {
    public DuplicateException(String message) {
        super(message);
    }
}
