package com.liyuan.community.dto;

import com.liyuan.community.model.User;
import lombok.Data;

@Data
public class QuestionDto {
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
    private User user;

    @Override
    public String toString() {
        return "QuestionDto{" +
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
                ", user=" + user +
                '}';
    }
}
