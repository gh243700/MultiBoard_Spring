

drop  table discussion_upload_file;
drop table posts;
drop table topics;
drop table discussion;
drop table member;
drop table category;


create table member
(
    id         integer primary key,
    username   varchar(50) unique not null,
    password   varchar(50)        not null,
    email      varchar(50) unique not null,
    joined     timestamp default sysdate(),
    post_count integer    default 0,
    last_visit timestamp          null,
    profile_img_name varchar(235),
    profile_img_size varchar(45),
    profile_img_type varchar(500),
    profile_img_data blob,
    profile_img_upload_date timestamp
);

CREATE TABLE category
(
    id    integer primary key,
    title varchar(200) unique not null
);

create table discussion
(
    id               integer primary key,
    title            varchar(200) unique not null,
    description      varchar(200)        null,
    category_id      integer references category (id),
    total_post_count integer default 0,
    recent_post_id integer null,
    recent_post_member_id integer null,
    recent_post_topics_title varchar(200) null,
    recent_post_time timestamp null
);

create table topics
(
    id             integer primary key,
    title          varchar(200) unique not null,
    writer         integer references member (id),
    write_date     TIMESTAMP default sysdate(),
    reply_number   integer    default 0,
    view_count     integer    default 0,
    discussion_id  integer references discussion (id),
    recent_post_id integer,
    post_count     integer
);

create table posts
(
    id                integer primary key,
    content           text   not null,
    writer            integer references member (id),
    edited            timestamp null,
    react_like        integer    default 0,
    react_agree       integer    default 0,
    react_informative integer    default 0,
    react_total_count integer    default 0,
    reply_step        integer NOT NULL,
    topic_id          integer references topics (id),
    discussion_id     integer not null
);

create table discussion_upload_file
(
    id                integer primary key,
    discussion_id     integer references discussion (id),
    file_name         varchar(235) not null,
    file_size         varchar(45)  not null,
    file_content_type varchar(500) not null,
    file_data         blob         not null,
    uploader          integer references member (id)
);