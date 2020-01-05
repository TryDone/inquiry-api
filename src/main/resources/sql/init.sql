--用户表
drop table if exists inquiry_user;
create table inquiry_user
(
  id            varchar(255) not null,
  open_id       varchar(255) default null comment '关联用户标识',
  name          varchar(255) default null comment '姓名',
  sex           varchar(255) default null comment '性别',
  birthday      varchar(255) default null comment '出生日期',
  tel_no        varchar(255) default null comment '联系电话',
  past_history  text comment '既往史',
  relation_type varchar(255) default '本人' comment '绑定关系(父亲、母亲等)',
  relation_id   varchar(255) default null comment '绑定ID',
  primary key (id)
) engine=innodb default charset=utf8;

--症状表
drop table if exists inquiry_symptom;
create table inquiry_symptom
(
  id      varchar(255) not null,
  content varchar(255) default null comment '症状',
  common  char(1)      default '1' comment '是否常见症状:1是,0否',
  type    char(1)      default null comment '症状类型:0症状,1诊断',
  level   varchar(255) default null comment '紧急程度',
  primary key (id)
) engine=innodb default charset=utf8;

--症状节点关系表
drop table if exists inquiry_symptom_ext;
create table inquiry_symptom_ext
(
  id        varchar(255) not null,
  src_id    varchar(255) default null comment '上级症状节点id',
  target_id varchar(255) default null comment '下级症状节点id',
  primary key (id)
) engine=innodb default charset=utf8;

--字典表
drop table if exists inquiry_dict;
create table inquiry_dict
(
  id   varchar(255) not null,
  name varchar(255) default null comment '名称',
  primary key (id)
) engine=innodb default charset=utf8;

--管理后台用户
drop table if exists inquiry_admin_user;
create table inquiry_admin_user
(
  user_id   varchar(255) not null,
  user_type varchar(255) default null,
  user_name varchar(255) not null,
  password  varchar(255) not null,
  primary key (user_id)
) engine=innodb default charset=utf8;
