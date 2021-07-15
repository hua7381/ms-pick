
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
insert into t_recognize_reg (keyword, contains, excludes) values ('首饰', '项链, 戒指', '');
insert into t_recognize_reg (keyword, contains, excludes) values ('现金', '现金, 钱, 元被盗', '');
insert into t_recognize_reg (keyword, contains, excludes) values ('宠物', '犬只, 猫只', '');
insert into t_recognize_reg (keyword, contains, excludes) values ('家畜', '鸡, 鸭, 猪, 牛, 羊', '');
insert into t_recognize_reg (keyword, contains, excludes) values ('盗窃', '门锁被撬, 撬锁, 偷, 盗', '');
insert into t_recognize_reg (keyword, contains, excludes) values ('走失', '走失, 离家出走, 失联, 小孩@不见', '');
insert into t_recognize_reg (keyword, contains, excludes) values ('诈骗', '骗, 冒充, 转账, 盗刷', '');
insert into t_recognize_reg (keyword, contains, excludes) values ('打架', '打架, 斗殴, 殴打, 被打, 被人打, 打人, 肢体冲突', '');
insert into t_recognize_reg (keyword, contains, excludes) values ('网贷', '网贷, 网络贷款', '');
insert into t_recognize_reg (keyword, contains, excludes) values ('涉赌', '赌, 扑克, 麻将, 六合彩, 牌九', '');
insert into t_recognize_reg (keyword, contains, excludes) values ('涉黄', '涉黄, 色情服务, 卖淫, 嫖娼, 站街女, 妓, 特殊服务', '');
insert into t_recognize_reg (keyword, contains, excludes) values ('涉毒', '涉毒, 吸毒, 毒品, 毒瘾, 戒毒, 海洛因, 冰毒, 摇头丸, 大麻', '');
insert into t_recognize_reg (keyword, contains, excludes) values ('强奸', '强奸, 强行发生@性关系, 性侵', '');
insert into t_recognize_reg (keyword, contains, excludes) values ('疫情', '疫情, 新冠肺, 病毒', '');
insert into t_recognize_reg (keyword, contains, excludes) values ('噪音', '噪音, 声@扰民', '');
insert into t_recognize_reg (keyword, contains, excludes) values ('堵车', '车@堵, 车@塞, 车@出入, 交通堵塞', '');
insert into t_recognize_reg (keyword, contains, excludes) values ('乱停乱放', '乱停乱放, 乱停', '');
insert into t_recognize_reg (keyword, contains, excludes) values ('轻生', '自杀, 轻生, 遗书', '');
insert into t_recognize_reg (keyword, contains, excludes) values ('涉访', '涉访, 上访', '');
insert into t_recognize_reg (keyword, contains, excludes) values ('火灾', '火灾, 起火, 火势, 着火', '');
insert into t_recognize_reg (keyword, contains, excludes) values ('遗失', '遗留, 遗失', '');
insert into t_recognize_reg (keyword, contains, excludes) values ('老人', '老人, 老年, 老年人', '');
insert into t_recognize_reg (keyword, contains, excludes) values ('撞车', '车@撞, 车@碰', '');
insert into t_recognize_reg (keyword, contains, excludes) values ('受伤', '伤, 出血', '');
insert into t_recognize_reg (keyword, contains, excludes) values ('醉酒', '醉酒, 喝醉', '');
end;
