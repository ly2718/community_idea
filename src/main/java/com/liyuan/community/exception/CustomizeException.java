package com.liyuan.community.exception;

public class CustomizeException extends RuntimeException {
    private String message;
    private int code;

    public CustomizeException(CustomizeErrorCodeIf errorCode) {
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }

    @Override
    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }
}
