package com.liyuan.community.advice;

import com.alibaba.fastjson.JSON;
import com.liyuan.community.dto.ResultDto;
import com.liyuan.community.exception.CustomizeErrorCode;
import com.liyuan.community.exception.CustomizeException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@ControllerAdvice
public class CustomizeExceptionHandler {
    @ExceptionHandler(Exception.class)
    ModelAndView handler(HttpServletRequest request, HttpServletResponse response, Throwable e, Model model) {
        String contentType = request.getContentType();
        if ("application/json".equals(contentType)) {
            ResultDto resultDto = null;
            if (e instanceof CustomizeException) {
                resultDto = ResultDto.errorOf((CustomizeException) e);
            } else {
                resultDto = ResultDto.errorOf(CustomizeErrorCode.SYS_ERROR);
            }
            try {
                response.setContentType("application/json;charset=UTF-8");
                PrintWriter writer = response.getWriter();
                writer.write(JSON.toJSONString(resultDto));
                writer.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            return null;
        } else {
            if (e instanceof CustomizeException) {
                model.addAttribute("message", e.getMessage());
            } else {
                model.addAttribute("message", "Error occur,Try again later");
            }
            return new ModelAndView("error");
        }
    }
}
