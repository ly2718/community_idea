package com.liyuan.community.service;

import com.liyuan.community.dto.PaginationDto;
import com.liyuan.community.dto.QuestionDto;
import com.liyuan.community.exception.CustomizeErrorCode;
import com.liyuan.community.exception.CustomizeException;
import com.liyuan.community.mapper.QuestionMapper;
import com.liyuan.community.mapper.UserMapper;
import com.liyuan.community.model.Question;
import com.liyuan.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private QuestionMapper questionMapper;

    public PaginationDto list(int page, int size) {
        PaginationDto paginationDto = new PaginationDto();
        //获取问题总数
        int totalCount = questionMapper.count();
        //设置页面所需参数
        paginationDto.setPagination(totalCount, page, size);
        //获取数据库查询偏移量
        int offset = size * (paginationDto.getPage() - 1);
        //获取数据库查询结果
        List<Question> questions = questionMapper.list(offset, size);
        List<QuestionDto> questionDtoList = new ArrayList<>();
        if (questions != null) {
            for (Question question : questions) {
                //根据问题发起人ID获取用户信息
                User user = userMapper.findById(question.getCreator());
                QuestionDto questionDto = new QuestionDto();
                //复制字段参数
                BeanUtils.copyProperties(question, questionDto);
                //添加问题发起人信息
                questionDto.setUser(user);
                questionDtoList.add(questionDto);
            }
        }
        paginationDto.setQuestions(questionDtoList);
        return paginationDto;
    }

    public PaginationDto list(long id, int page, int size) {
        PaginationDto paginationDto = new PaginationDto();
        //获取问题总数
        int totalCount = questionMapper.countByUserId(id);
        //设置页面所需参数
        paginationDto.setPagination(totalCount, page, size);
        //获取数据库查询偏移量
        int offset = size * (paginationDto.getPage() - 1);
        //获取数据库查询结果
        List<Question> questions = questionMapper.listByUserId(id, offset, size);
        //根据问题发起人ID获取用户信息
        User user = userMapper.findById(id);
        List<QuestionDto> questionDtoList = new ArrayList<>();
        if (questions != null) {
            for (Question question : questions) {
                QuestionDto questionDto = new QuestionDto();
                //复制字段参数
                BeanUtils.copyProperties(question, questionDto);
                //添加问题发起人信息
                questionDto.setUser(user);
                questionDtoList.add(questionDto);
            }
        }
        paginationDto.setQuestions(questionDtoList);
        return paginationDto;
    }

    public QuestionDto getById(long id) {
        Question question = questionMapper.getById(id);
        if (question == null) {
            throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
        }
        QuestionDto questionDto = new QuestionDto();
        //复制字段参数
        BeanUtils.copyProperties(question, questionDto);
        User user = userMapper.findById(id);
        questionDto.setUser(user);
        return questionDto;
    }

    public void createOrUpdate(Question question) {
        if (question.getId() == 0) {
            //添加
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModify(question.getGmtCreate());
            questionMapper.create(question);
        } else {
            //更新
            question.setGmtModify(System.currentTimeMillis());
            questionMapper.update(question);
        }
    }

    public void incView(long id) {
        questionMapper.updateByIdAndViewCount(id);
    }

}
