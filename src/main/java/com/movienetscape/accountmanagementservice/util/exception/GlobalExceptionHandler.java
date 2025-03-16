package com.movienetscape.accountmanagementservice.util.exception;


import com.movienetscape.accountmanagementservice.dto.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> handleBadRequestExceptions(BadRequestException ex) {

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(ex.getMessage()));
    }


    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleAccountNotFoundException(NotFoundException ex) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(ex.getMessage()));
    }


    @ExceptionHandler( DuplicateException.class)
    public ResponseEntity<ErrorResponse> handleAlreadyExistExceptions(DuplicateException ex) {

        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponse(ex.getMessage()));
    }


    @ExceptionHandler( IllegalOperationException.class)
    public ResponseEntity<ErrorResponse> handleIllegalOperationExceptions(IllegalOperationException ex) {

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(ex.getMessage()));
    }

    @ExceptionHandler( Exception.class)
    public ResponseEntity<String> handleOtherExceptions(Exception ex) {

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }

}
