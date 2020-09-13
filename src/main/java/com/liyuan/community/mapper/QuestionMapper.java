package com.liyuan.community.mapper;

import com.liyuan.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface QuestionMapper {
    @Insert(value = "insert into question (title,description,comment_count,view_count,like_count,creator,tag,gmt_create,gmt_modify) values(#{title},#{description},#{commentCount},#{viewCount},#{likeCount},#{creator},#{tag},#{gmtCreate},#{gmtModify})")
    void create(Question question);
}
