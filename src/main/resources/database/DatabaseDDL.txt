

drop table discussion_upload_file;
drop table profile_img;
drop table posts;
drop table topics;
drop table discussion;
drop table member;
drop table category;


create table member
(
    id         number primary key,
    username   varchar(50) unique not null,
    password   varchar(50)        not null,
    email      varchar(50) unique not null,
    joined     timestamp default sysdate,
    post_count number    default 0,
    last_visit timestamp          null,
    profile_img_name varchar(235),
    profile_img_size varchar(45),
    profile_img_type varchar(500),
    profile_img_data blob,
    profile_img_upload_date timestamp
);

CREATE TABLE category
(
    id    number primary key,
    title varchar(200) unique not null
);

create table discussion
(
    id               number primary key,
    title            varchar(200) unique not null,
    description      varchar(200)        null,
    category_id      number references category (id),
    total_post_count number default 0,
    recent_post_id number null,
    recent_post_member_id number null,
    recent_post_topics_title varchar(200) null,
    recent_post_time timestamp null
);

create table topics
(
    id             number primary key,
    title          varchar(200) unique not null,
    writer         number references member (id),
    write_date     TIMESTAMP default sysdate,
    reply_number   number    default 0,
    view_count     number    default 0,
    discussion_id  number references discussion (id),
    recent_post_id number,
    post_count     number
);

create table posts
(
    id                number primary key,
    content           clob   not null,
    writer            number references member (id),
    edited            timestamp default sysdate,
    react_like        number    default 0,
    react_agree       number    default 0,
    react_informative number    default 0,
    react_total_count number    default 0,
    reply_step        number null,
    topic_id          number references topics (id),
    discussion_id     number not null
);

create table discussion_upload_file
(
    id                number primary key,
    discussion_id     number references discussion (id),
    file_name         varchar(235) not null,
    file_size         varchar(45)  not null,
    file_content_type varchar(500) not null,
    file_data         blob         not null,
    uploader          number references member (id)
);

