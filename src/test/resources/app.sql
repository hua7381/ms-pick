-- 建表语句

-- 书籍
drop table t_demo_book;
create table t_demo_book (
    id varchar2(32) default sys_guid() primary key,
    create_time date default sysdate,
    update_time date default sysdate,

    create_user varchar2(100),
    name varchar2(100),
    description varchar2(1000),
    publish_time date,
    type_id varchar2(32),
    dept_no varchar2(20),
    status varchar2(20),
    available int,
    read_num int default 0
);
create index i_demo_book_1 on t_demo_book(create_time);
create index i_demo_book_2 on t_demo_book(update_time);


-- 书籍类别
drop table t_demo_book_type;
create table t_demo_book_type (
    id varchar2(32) default sys_guid() primary key,
    create_time date default sysdate,
    update_time date default sysdate,

    name varchar2(100)
);
insert into t_demo_book_type (name) values ('人文类');
insert into t_demo_book_type (name) values ('科技类');
insert into t_demo_book_type (name) values ('历史类');


-- 部门
drop table t_demo_dept;
create table t_demo_dept (
    id varchar2(32) default sys_guid() primary key,
    create_time date default sysdate,
    update_time date default sysdate,

    no varchar2(20),
    pno varchar2(20),
    name varchar2(100)
);
insert into t_demo_dept (no, pno, name) values ('0100', null, '部门A');
insert into t_demo_dept (no, pno, name) values ('0101', '0100', '部门A1');
insert into t_demo_dept (no, pno, name) values ('0102', '0100', '部门A2');
insert into t_demo_dept (no, pno, name) values ('0200', null, '部门B');
insert into t_demo_dept (no, pno, name) values ('0201', '0200', '部门B1');
insert into t_demo_dept (no, pno, name) values ('0202', '0200', '部门B2');
create index i_demo_dept_1 on t_demo_dept(no);