package com.liyuan.community.exception;

public class CustomizeException extends RuntimeException{
    private String message;

    public CustomizeException(CustomizeErrorCodeIf errorCode) {
        this.message = errorCode.getMessage();
    }

    public CustomizeException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
