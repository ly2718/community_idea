package com.liyuan.community.controller;

import com.liyuan.community.dto.CommentDto;
import com.liyuan.community.dto.QuestionDto;
import com.liyuan.community.mapper.QuestionMapper;
import com.liyuan.community.service.CommentService;
import com.liyuan.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.websocket.server.PathParam;
import java.util.List;

@Controller
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @Autowired
    private CommentService commentService;

    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id") int id, Model model) {
        QuestionDto questionDto = questionService.getById(id);
        List<CommentDto> comments = commentService.listByQuestionId(id);
        questionService.incView(id);
        model.addAttribute("question", questionDto);
        model.addAttribute("comments", comments);
        return "question";
    }


}
