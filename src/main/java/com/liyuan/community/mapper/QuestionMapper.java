package com.liyuan.community.mapper;

import com.liyuan.community.dto.QuestionDto;
import com.liyuan.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface QuestionMapper {
    @Insert(value = "insert into question (title,description,comment_count,view_count,like_count,creator,tag,gmt_create,gmt_modify) values(#{title},#{description},#{commentCount},#{viewCount},#{likeCount},#{creator},#{tag},#{gmtCreate},#{gmtModify})")
    void create(Question question);

    @Select(value = "select * from question")
    List<Question> list();
}
