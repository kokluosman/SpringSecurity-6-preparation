package com.example.springsecuritypresentation.core.result;

public class DataResult<T> extends Result{

    private final T data;

    public DataResult(T data,boolean success, String message) {
        super(success, message);
        this.data = data;
    }

    public DataResult(boolean success, T data) {
        super(success);
        this.data = data;
    }

    public T getData() {
        return data;
    }
}
