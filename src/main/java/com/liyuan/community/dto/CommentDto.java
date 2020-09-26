package com.liyuan.community.dto;

import lombok.Data;

@Data
public class CommentDto {
    private Long parentId;
    private String comment;
    private int type;
    private String content;
}
