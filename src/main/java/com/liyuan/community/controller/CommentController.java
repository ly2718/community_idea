package com.liyuan.community.controller;

import com.liyuan.community.dto.CommentDto;
import com.liyuan.community.dto.ResultDto;
import com.liyuan.community.exception.CustomizeErrorCode;
import com.liyuan.community.model.Comment;
import com.liyuan.community.model.User;
import com.liyuan.community.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;

    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    @ResponseBody
    public Object post(@RequestBody CommentDto commentDto, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if(user == null){
            return ResultDto.errorOf(CustomizeErrorCode.NO_LOGIN);
        }
        Comment comment = new Comment();
        comment.setParentId(commentDto.getParentId());
        comment.setContent(commentDto.getContent());
        comment.setType(commentDto.getType());
        comment.setLikeCount(0l);
        comment.setCommentator(user.getId());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModify(comment.getGmtCreate());
        commentService.insert(comment);
        return ResultDto.okOf();
    }

}
