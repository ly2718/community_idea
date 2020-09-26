package com.liyuan.community.mapper;

import com.liyuan.community.model.Question;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface QuestionMapper {
    @Insert(value = "insert into question (title,description,comment_count,view_count,like_count,creator,tag,gmt_create,gmt_modify) values(#{title},#{description},#{commentCount},#{viewCount},#{likeCount},#{creator},#{tag},#{gmtCreate},#{gmtModify})")
    void create(Question question);

    @Select(value = "select * from question limit #{offset},#{size}")
    List<Question> list(@Param(value = "offset") int offset, @Param(value = "size") int size);

    @Select(value = "select count(1) from question")
    int count();

    @Select(value = "select * from question where creator = #{id} limit #{offset},#{size}")
    List<Question> listByUserId(@Param(value = "creator") long id, @Param(value = "offset") int offset, @Param(value = "size") int size);

    @Select(value = "select count(1) from question where creator = #{userId}")
    int countByUserId(@Param(value = "userId") long userId);

    @Select(value = "select * from question where id = #{id}")
    Question getById(@Param(value = "id") long id);

    @Update(value = "update question set title = #{title}, description = #{description}, tag = #{tag}, gmt_modify = #{gmtModify} where id = #{id}")
    void update(Question question);

    @Select(value = "select * from question where id = #{id}")
    Question selectById(@Param(value = "id") long id);

    @Update(value = "update question set view_count = view_count + 1 where id = #{id}")
    void updateByIdAndViewCount(@Param(value = "id") long id);

    @Update(value = "update question set comment_count = comment_count + #{commentCount} where id = #{id}")
    void updateByIdAndCommentCount(@Param(value = "id") long id, @Param(value = "commentCount") int commentCount);
}
