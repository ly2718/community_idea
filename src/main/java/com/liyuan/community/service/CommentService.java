package com.liyuan.community.service;

import com.liyuan.community.dto.CommentDto;
import com.liyuan.community.enums.CommentTypeEnum;
import com.liyuan.community.exception.CustomizeErrorCode;
import com.liyuan.community.exception.CustomizeException;
import com.liyuan.community.mapper.CommentMapper;
import com.liyuan.community.mapper.QuestionMapper;
import com.liyuan.community.mapper.UserMapper;
import com.liyuan.community.model.Comment;
import com.liyuan.community.model.Question;
import com.liyuan.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Transactional
@Service
public class CommentService {
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;

    public void insert(Comment comment) {
        if (comment.getParentId() == 0) {
            throw new CustomizeException(CustomizeErrorCode.TARGET_NOT_FOUND);
        }
        if (comment.getType() == 0 || CommentTypeEnum.isExist(comment.getType())) {
            throw new CustomizeException(CustomizeErrorCode.TYPE_ERROR);
        }
        if (comment.getType() == CommentTypeEnum.COMMENT.getType()) {
            Comment dbComment = commentMapper.getByParentId(comment.getParentId());
            if (dbComment == null) {
                throw new CustomizeException(CustomizeErrorCode.COMMENT_NOT_FOUND);
            }
        } else {
            Question question = questionMapper.getById(comment.getParentId());
            if (question == null) {
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
            questionMapper.updateByIdAndCommentCount(question.getId(), 1);
        }
        commentMapper.insert(comment);
    }

    public List<CommentDto> listByQuestionId(int id) {
        List<Comment> comments = commentMapper.selectByIdAndType(id, CommentTypeEnum.QUESTION.getType());
        if (comments.size() == 0) {
            return new ArrayList<>();
        }

        //获取去重评论人
        Set<Long> commentators = comments.stream().map(comment -> comment.getCommentator()).collect(Collectors.toSet());
        ArrayList<Long> userIds = new ArrayList<>();
        userIds.addAll(commentators);
        List<User> users = userMapper.selectByIds(userIds);

        //获取评论人并以id，User映射为Map
        Map<Long, User> userMap = users.stream().collect(Collectors.toMap(user -> user.getId(), user -> user));
        //转换comment为CommentDto
        List<CommentDto> commentDtos = comments.stream().map(comment -> {
            CommentDto commentDto = new CommentDto();
            BeanUtils.copyProperties(comment, commentDto);
            commentDto.setUser(userMap.get(comment.getCommentator()));
            return commentDto;
        }).collect(Collectors.toList());
        return commentDtos;
    }
}
