package com.liyuan.community.exception;

public enum CustomizeErrorCode implements CustomizeErrorCodeIf{
    QUESTION_NOT_FOUND(2001,"你找的问题不存在"),
    TARGET_NOT_FOUND(2002,"未选中任何问题或评论"),
    NO_LOGIN(2003,"未登录不能进行评论，请先登录"),
    SYS_ERROR(2004,"系统错误，请稍后再试"),
    TYPE_ERROR(2005,"评论类型错误或不存在"),
    COMMENT_NOT_FOUND(2006,"回复的评论不存在"),;
    @Override
    public String getMessage(){
        return message;
    }

    @Override
    public int getCode() {
        return code;
    }

    private String message;

    private int code;

    CustomizeErrorCode(int code, String message) {
        this.message = message;
        this.code = code;
    }
}
