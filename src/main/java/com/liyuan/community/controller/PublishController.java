package com.liyuan.community.controller;

import com.liyuan.community.mapper.QuestionMapper;
import com.liyuan.community.mapper.UserMapper;
import com.liyuan.community.model.Question;
import com.liyuan.community.model.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;
    private static final String REQUIRED_NOT_NULL = "不能为空";

    @GetMapping("/publish")
    public String publish() {
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(@Param(value = "title") String title,
                            @Param(value = "description") String description,
                            @Param(value = "tag") String tag,
                            HttpServletRequest httpServletRequest,
                            Model model) {
        //数据回显
        model.addAttribute("title", title);
        //数据回显
        model.addAttribute("description", description);
        //数据回显
        model.addAttribute("tag", tag);
        if (title == null || "".equals(title)) {
            model.addAttribute("error", "标题" + REQUIRED_NOT_NULL);
            return "publish";
        }
        if (description == null || "".equals(description)) {
            model.addAttribute("error", "问题补充" + REQUIRED_NOT_NULL);
            return "publish";
        }
        if (tag == null || "".equals(tag)) {
            model.addAttribute("error", "标签" + REQUIRED_NOT_NULL);
            return "publish";
        }
        User user = null;
        Cookie[] cookies = httpServletRequest.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("token".equals(cookie.getName())) {
                    String token = cookie.getValue();
                    user = userMapper.findByToken(token);
                    if (user != null) {
                        httpServletRequest.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
        }
        if (user == null) {
            model.addAttribute("error", "用户未登录");
            return "publish";
        }
        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setCreator(user.getId());
        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtModify(question.getGmtCreate());
        questionMapper.create(question);
        return "redirect:/";
    }

}
