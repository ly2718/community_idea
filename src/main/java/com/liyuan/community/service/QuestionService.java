package com.liyuan.community.service;

import com.liyuan.community.dto.QuestionDto;
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

    public List<QuestionDto> list() {
        List<Question> questions = questionMapper.list();
        List<QuestionDto> questionDtoList = new ArrayList<>();
        if (questions != null) {
            for (Question question : questions) {
                User user = userMapper.findById(question.getCreator());
                QuestionDto questionDto = new QuestionDto();
                BeanUtils.copyProperties(question, questionDto);
                questionDto.setUser(user);
                questionDtoList.add(questionDto);
            }
        }
        return questionDtoList;
    }
}
