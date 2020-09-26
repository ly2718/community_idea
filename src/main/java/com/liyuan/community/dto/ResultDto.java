package com.liyuan.community.dto;

import com.liyuan.community.exception.CustomizeErrorCode;
import com.liyuan.community.exception.CustomizeException;
import lombok.Data;

@Data
public class ResultDto {
    private int code;
    private String message;

    public static ResultDto errorOf(int code, String message) {
        ResultDto resultDto = new ResultDto();
        resultDto.setCode(code);
        resultDto.setMessage(message);
        return resultDto;
    }

    public static ResultDto errorOf(CustomizeErrorCode errorCode) {
        return errorOf(errorCode.getCode(), errorCode.getMessage());
    }

    public static ResultDto errorOf(CustomizeException e) {
        return errorOf(e.getCode(), e.getMessage());
    }

    public static ResultDto okOf() {
        ResultDto resultDto = new ResultDto();
        resultDto.setCode(200);
        resultDto.setMessage("请求成功");
        return resultDto;
    }
}
