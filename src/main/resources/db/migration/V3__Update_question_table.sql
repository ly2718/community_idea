drop table question;

create table question
(
	id int auto_increment,
	title varchar(50),
	description text,
	comment_count int default 0,
	view_count int default 0,
	like_count int default 0,
	creator int,
	tag varchar(256),
	gmt_create bigint,
	gmt_modify bigint,
	constraint QUESTION_PK
		primary key (id)
);