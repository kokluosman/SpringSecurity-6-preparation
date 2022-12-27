package com.example.springsecuritypresentation.core.exceptions;

import com.example.springsecuritypresentation.core.result.ErrorDataResult;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ErrorDataResult<Object> handleBusinessExceptions(Exception exception) {
        ErrorDataResult<Object> errorDataResult =
                new ErrorDataResult<>(exception.getMessage(), "An Error Occurred");
        return errorDataResult;
    }
}
