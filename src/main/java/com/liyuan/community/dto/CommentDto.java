package com.liyuan.community.dto;

import com.liyuan.community.model.User;
import lombok.Data;

@Data
public class CommentDto {
    private long id;
    private long parentId;
    private int type;
    private long commentator;
    private long likeCount;
    private String content;
    private long gmtCreate;
    private long gmtModify;
    private User user;
}
