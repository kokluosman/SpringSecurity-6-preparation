package com.example.springsecuritypresentation.core.result;

public class ErrorDataResult<T> extends DataResult<T>{
    public ErrorDataResult(T data, String message) {
        super(data, false, message);
    }

    public ErrorDataResult(T data) {
        super(false, data);
    }

    public ErrorDataResult(String message) {
        super(null, true, message);
    }

    public ErrorDataResult() {
        super(true, null);
    }
}
