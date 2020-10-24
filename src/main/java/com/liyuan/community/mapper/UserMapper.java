package com.liyuan.community.mapper;

import com.liyuan.community.model.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@Mapper
public interface UserMapper {
    @Insert("insert into user (name,account_id,token,gmt_create,gmt_modify,avatar_url) values(#{name},#{accountId},#{token},#{gmtCreate},#{gmtModify},#{avatarUrl})")
    void insert(User user);

    @Select("select * from user where token = #{token}")
    User findByToken(@Param(value = "token") String token);

    @Select("select * from user where id = #{id}")
    User findById(@Param(value = "id") long id);

    @Select("select * from user where account_Id = #{accountId}")
    User findByAccountId(@Param(value = "accountId") String accountId);

    @Update("update user set name = #{name}, token = #{token}, gmt_modify = #{gmtModify}, avatar_url = #{avatarUrl} where id = #{id}")
    void update(User dbUser);

    @Select("<script> " +
            "select * from user where id in" +
            "<foreach item='item' index='index' collection='userIds' open='(' separator=',' close=')'> " +
            "   #{item} " +
            "</foreach>" +
            "</script> "
    )
    List<User> selectByIds(@Param(value = "userIds") List<Long> userIds);
}
