create table comment
(
    ID          bigint auto_increment
        primary key,
    PARENT_ID   bigint           not null comment '父类ID',
    TYPE        int              not null comment '父类类型',
    COMMENTATOR int              null comment '评论人ID',
    LIKE_COUNT  bigint default 0 null comment '点赞数',
    CONTENT     varchar(1024)    null,
    GMT_CREATE  bigint           null comment '创建时间',
    GMT_MODIFY  bigint           null comment '修改时间'
);