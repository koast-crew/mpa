drop table if exists role cascade;

-- Role role_key unique 제약 조건 걸어야 함
create table role(
	role_id						integer,
	role_name					varchar(100)							not null,
	role_key					varchar(50)								not null,
	role_target					char(1)									not null,
	role_type					varchar(2)								not null,
    basic						boolean								default false,
    available					boolean								default true,
	description					varchar(256),
    created_date				timestamp with time zone			default now(),
	constraint role_pk primary key (role_id)	
);

comment on table role is 'Role';
comment on column role.role_id is '고유번호';
comment on column role.role_name is 'Role 명';
comment on column role.role_key is 'Role KEY';
comment on column role.role_target is 'Role 타켓. USER : 사용자 사이트, ADMIN : 관리자 사이트';
comment on column role.role_type is 'Role 업무 유형. USER : 사용자, SERVER : 서버, API : api';
comment on column role.basic is '삭제 불가, true : 기본, false : 선택';
comment on column role.available is '사용유무, true : 사용, false : 사용안함';
comment on column role.description is '설명';
comment on column role.created_date is '등록일';