
-- 规则
--------------------------------------------------------
create table t_recognize_reg (
    id varchar2(32) default sys_guid() primary key,
    create_time date default sysdate,
    update_time date default sysdate,
    
    keyword varchar2(20), -- 关键词
    contains varchar2(500), -- 包含词s
    excludes varchar2(500) -- 排除词s
);
create index idx_recognize_reg_1 on t_recognize_reg(create_time);
create index idx_recognize_reg_2 on t_recognize_reg(update_time);
--------------------------------------------------------

begin
delete from t_recognize_reg where 1 = 1;
insert into t_recognize_reg (keyword, contains, excludes) values ('打架', '打架,斗殴,殴打,被打,被人打,打人,肢体冲突', '');
insert into t_recognize_reg (keyword, contains, excludes) values ('堵车', '车@堵,车@塞,车@出入,交通堵塞', '');
end;