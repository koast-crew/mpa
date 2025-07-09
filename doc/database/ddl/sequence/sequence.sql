drop sequence if exists policy_seq;
drop sequence if exists role_seq;
drop sequence if exists user_group_seq;
drop sequence if exists user_group_menu_seq;
drop sequence if exists user_group_role_seq;

create sequence policy_seq increment 1 minvalue 1 maxvalue 999999999999 start 2 cache 1;
create sequence role_seq increment 1 minvalue 1 maxvalue 999999999999 start 100 cache 1;
create sequence user_group_seq increment 1 minvalue 1 maxvalue 999999999999 start 100 cache 1;
create sequence user_group_role_seq increment 1 minvalue 1 maxvalue 999999999999 start 1 cache 1;
create sequence user_group_menu_seq increment 1 minvalue 1 maxvalue 999999999999 start 10000 cache 1;