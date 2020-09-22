package com.liyuan.community.exception;

public enum CustomizeErrorCode implements CustomizeErrorCodeIf{
    QUESTION_NOT_FOUND("This question can not be found");
    @Override
    public String getMessage(){
        return message;
    }

    private String message;

    CustomizeErrorCode(String message) {
        this.message = message;
    }
}
