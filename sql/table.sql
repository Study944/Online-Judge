-- 创建库
create database if not exists interview_platform;

-- 切换库
use interview_platform;

-- 用户表
create table if not exists user
(
    id            bigint auto_increment comment 'id' primary key,
    user_account  varchar(256)                           not null comment '账号',
    user_password varchar(512)                           not null comment '密码',
    user_name     varchar(256)                           null comment '用户昵称',
    user_profile  varchar(512)                           null comment '用户简介',
    user_role     varchar(256) default 'user'            not null comment '用户角色：user/admin/ban',
    edit_time     datetime     default CURRENT_TIMESTAMP not null comment '编辑时间',
    create_time   datetime     default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time   datetime     default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    is_delete     tinyint      default 0                 not null comment '是否删除',
    index idx_Id (id)
) comment '用户' collate = utf8mb4_unicode_ci;

-- 题目表
create table if not exists question
(
    id                  bigint auto_increment comment 'id' primary key,
    question_name       varchar(256)                           not null comment '题目名称',
    question_content    text                                   not null comment '题目描述',
    question_answer     text                                   not null comment '题目答案',
    question_difficulty tinyint default 1 comment '题目难度：easy-1/medium-2/hard-3',
    question_type       varchar(256) comment '题目类型：队列/栈/树',
    question_cases      text                                   not null comment '判题数据',
    question_config     text                                   not null comment '题目配置',
    submission_count    int          default 0 comment '提交次数',
    accepted_count      int          default 0 comment '通过次数',
    create_user         tinyint                                not null comment '创建用户id',
    edit_time           datetime     default CURRENT_TIMESTAMP not null comment '编辑时间',
    create_time         datetime     default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time         datetime     default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    is_delete           tinyint      default 0                 not null comment '是否删除',
    index idx_Id (id)
) comment '题目' collate = utf8mb4_unicode_ci;

-- 新增题目实例questionExample
alter table question
    add column question_example text comment '题目实例';

-- 题目提交表
create table if not exists submission
(
    id          bigint auto_increment comment 'id' primary key,
    question_id bigint                             not null comment '题目id',
    user_name     bigint                             not null comment '用户id',
    code        text                               not null comment '提交代码',
    language    varchar(256)                       not null comment '提交语言',
    state       varchar(256) comment '题目状态：执行中/通过/超时/错误',
    judge_info  text comment '判题信息：执行内存、执行时间',
    create_time datetime default CURRENT_TIMESTAMP not null comment '提交时间',
    index idx_Id (id)
) comment '题目提交记录' collate = utf8mb4_unicode_ci;

-- 修改state为tinyint
alter table submission
    modify column state tinyint default 0;
-- 修改state为tinyint
alter table submission
    modify column language tinyint ;
-- 修改用户ID为用户名
alter table submission
    modify column user_name varchar(256);