package com.quind.pruebatecnica.domain.exceptions;

public abstract class BaseException extends RuntimeException {
    private String message;

    public BaseException(String message) {
        super();
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
