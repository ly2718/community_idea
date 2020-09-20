package com.liyuan.community.controller;

import com.liyuan.community.dto.PaginationDto;
import com.liyuan.community.mapper.UserMapper;
import com.liyuan.community.model.User;
import com.liyuan.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {
    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String index(HttpServletRequest httpServletRequest,
                        Model model,
                        @RequestParam(name = "page", defaultValue = "1") int page,
                        @RequestParam(name = "size", defaultValue = "5") int size) {
        PaginationDto paginationDto = questionService.list(page, size);
        model.addAttribute("pagination", paginationDto);
        return "index";
    }

}
