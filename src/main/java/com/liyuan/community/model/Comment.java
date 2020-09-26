package com.liyuan.community.model;

import lombok.Data;

@Data
public class Comment {
    private long id;
    private long parentId;
    private int type;
    private long commentator;
    private long likeCount;
    private String content;
    private long gmtCreate;
    private long gmtModify;
}
