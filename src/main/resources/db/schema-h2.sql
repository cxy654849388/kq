create table if not exists TBL_QUESTIONS_ANSWERS
(
    ID           BIGINT auto_increment,
    QUESTIONS_ID INT          not null,
    GROUP_ID     BIGINT       not null,
    QUESTIONS    VARCHAR(200) not null,
    ANSWERS      VARCHAR(200) not null,
    constraint TBL_QUESTIONS_ANSWERS_PK
        primary key (ID)
);

comment on table TBL_QUESTIONS_ANSWERS is 'Q群问答表';

comment on column TBL_QUESTIONS_ANSWERS.ID is '主键';

comment on column TBL_QUESTIONS_ANSWERS.QUESTIONS_ID is '问题ID';

comment on column TBL_QUESTIONS_ANSWERS.GROUP_ID is '群号';

comment on column TBL_QUESTIONS_ANSWERS.QUESTIONS is '问题';

comment on column TBL_QUESTIONS_ANSWERS.ANSWERS is '答案';

