package com.liyuan.community.controller;

import com.liyuan.community.dto.QuestionDto;
import com.liyuan.community.model.Question;
import com.liyuan.community.model.User;
import com.liyuan.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {
    @Autowired
    private QuestionService questionService;

    private static final String REQUIRED_NOT_EMPTY = "不能为空";

    @GetMapping("/publish/{id}")
    public String edit(@PathVariable(name = "id") int id,Model model) {
        QuestionDto question = questionService.getById(id);
        //数据回显
        model.addAttribute("title", question.getTitle());
        //数据回显
        model.addAttribute("description", question.getDescription());
        //数据回显
        model.addAttribute("tag", question.getTag());
        model.addAttribute("id",question.getId());
        return "publish";
    }


    @GetMapping("/publish")
    public String publish() {
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(@RequestParam(value = "title",required = false) String title,
                            @RequestParam(value = "description",required = false) String description,
                            @RequestParam(value = "tag",required = false) String tag,
                            HttpServletRequest request,
                            Model model) {
        //数据回显
        model.addAttribute("title", title);
        //数据回显
        model.addAttribute("description", description);
        //数据回显
        model.addAttribute("tag", tag);
        if (title == null || "".equals(title)) {
            model.addAttribute("error", "标题" + REQUIRED_NOT_EMPTY);
            return "publish";
        }
        if (description == null || "".equals(description)) {
            model.addAttribute("error", "问题补充" + REQUIRED_NOT_EMPTY);
            return "publish";
        }
        if (tag == null || "".equals(tag)) {
            model.addAttribute("error", "标签" + REQUIRED_NOT_EMPTY);
            return "publish";
        }
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            model.addAttribute("error", "用户未登录");
            return "publish";
        }
        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setCreator(user.getId());
        questionService.createOrUpdate(question);
        return "redirect:/";
    }

}
