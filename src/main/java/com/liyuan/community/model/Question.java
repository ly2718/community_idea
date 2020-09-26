package com.liyuan.community.model;

import lombok.Data;

@Data
public class Question {
    private long id;
    private String title;
    private String description;
    private String tag;
    private long creator;
    private int viewCount;
    private int commentCount;
    private int likeCount;
    private long gmtCreate;
    private long gmtModify;

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", tag='" + tag + '\'' +
                ", creator=" + creator +
                ", viewCount=" + viewCount +
                ", commentCount=" + commentCount +
                ", likeCount=" + likeCount +
                ", gmtCreate=" + gmtCreate +
                ", gmtModify=" + gmtModify +
                '}';
    }
}
