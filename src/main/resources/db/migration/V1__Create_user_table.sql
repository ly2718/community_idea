create table USER
(
    ID         INT auto_increment,
    ACCOUNT_ID VARCHAR(100),
    NAME       VARCHAR(50),
    TOKEN      VARCHAR(36),
    GMT_CREATE BIGINT,
    GMT_MODIFY BIGINT,
    BIO        VARCHAR(256),
    constraint USER_PK
        primary key (ID)
);