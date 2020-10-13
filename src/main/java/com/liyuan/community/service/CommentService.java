package com.liyuan.community.service;

import com.liyuan.community.enums.CommentTypeEnum;
import com.liyuan.community.exception.CustomizeErrorCode;
import com.liyuan.community.exception.CustomizeException;
import com.liyuan.community.mapper.CommentMapper;
import com.liyuan.community.mapper.QuestionMapper;
import com.liyuan.community.model.Comment;
import com.liyuan.community.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class CommentService {
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private QuestionMapper questionMapper;
    public void insert(Comment comment){
        if(comment.getParentId() == 0){
            throw new CustomizeException(CustomizeErrorCode.TARGET_NOT_FOUND);
        }
        if(comment.getType() == 0 || CommentTypeEnum.isExist(comment.getType())){
            throw new CustomizeException(CustomizeErrorCode.TYPE_ERROR);
        }
        if(comment.getType() == CommentTypeEnum.COMMENT.getType()){
            Comment dbComment = commentMapper.getByParentId(comment.getParentId());
            if(dbComment == null){
                throw new CustomizeException(CustomizeErrorCode.COMMENT_NOT_FOUND);
            }
        }else{
            Question question = questionMapper.getById(comment.getParentId());
            if(question == null){
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
            questionMapper.updateByIdAndCommentCount(question.getId(),1);
        }
        commentMapper.insert(comment);
    }
}
