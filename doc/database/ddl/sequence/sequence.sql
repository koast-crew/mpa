drop sequence if exists policy_seq;

create sequence policy_seq increment 1 minvalue 1 maxvalue 999999999999 start 2 cache 1;
