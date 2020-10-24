package com.liyuan.community.mapper;

import com.liyuan.community.model.Comment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CommentMapper {
    @Insert("insert into comment(id, parent_id, type, commentator, like_count, content, gmt_create, gmt_modify) values(id, parentId, type, commentator, likeCount, content, gmtCreate, gmtModify)")
    void insert(Comment comment);

    @Select("select * from comment where parent_id = #{parentId}")
    Comment getByParentId(@Param(value = "parentId") long parentId);

    @Select("select * from comment where parent_id = #{id} and type = #{type}")
    List<Comment> selectByIdAndType(@Param(value = "id") int id, @Param(value = "type") int type);
}
