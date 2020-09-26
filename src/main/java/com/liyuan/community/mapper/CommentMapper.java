package com.liyuan.community.mapper;

import com.liyuan.community.model.Comment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface CommentMapper {
    @Insert("insert into comment(id, parent_id, type, commentator, like_count, content, gmt_create, gmt_modify) values(id, parentId, type, commentator, likeCount, content, gmtCreate, gmtModify)")
    void insert(Comment comment);

    @Select("select * from comment where parent_id = #{parentId}")
    Comment getById(@Param(value = "parentId") long parentId);
}
