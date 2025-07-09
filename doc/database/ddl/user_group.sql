drop table if exists user_group cascade;
drop table if exists user_group_menu cascade;
drop table if exists user_group_role cascade;

create table user_group (
	user_group_id				integer,
	user_group_key				varchar(60)							not null ,
	user_group_name				varchar(100)						not null,
	ancestor					integer								default 0,
	parent						integer								default 1,
	depth						integer								default 1,
	view_order					integer								default 1,
	child_count					integer								default 0,
	basic						boolean								default false,
	available					boolean								default true,
	description					varchar(256),
    modified_date				timestamp with time zone,
	created_date				timestamp with time zone			default now(),
	constraint user_group_pk 	primary key (user_group_id)
);

comment on table user_group is '사용자 그룹';
comment on column user_group.user_group_id is '고유번호';
comment on column user_group.user_group_key is '링크 활용 등을 위한 확장 컬럼';
comment on column user_group.user_group_name is '그룹명';
comment on column user_group.ancestor is '조상 고유번호';
comment on column user_group.parent is '부모 고유번호';
comment on column user_group.depth is '깊이';
comment on column user_group.view_order is '나열 순서';
comment on column user_group.child_count is '자식 존재 개수';
comment on column user_group.basic is '삭제 불가, true : 기본, false : 선택';
comment on column user_group.available is '사용유무, true : 사용, false : 사용안함';
comment on column user_group.description is '설명';
comment on column user_group.modified_date is '수정일';
comment on column user_group.created_date is '등록일';

-- 사용자 그룹별 Role
create table user_group_role (
     user_group_role_id				integer,
     user_group_id					integer 								not null,
     role_id						integer	 								not null,
     created_date				    timestamp with time zone			default now(),
     constraint user_group_role_pk 	primary key (user_group_role_id)
);

comment on table user_group_role is '사용자 그룹별 Role';
comment on column user_group_role.user_group_role_id is '고유번호';
comment on column user_group_role.user_group_id is '사용자 그룹 고유키';
comment on column user_group_role.role_id is 'Role 고유키';
comment on column user_group_role.created_date is '등록일';

        -- 사용자 그룹 권한
create table user_group_menu(
    user_group_menu_id				integer,
    user_group_id					integer 							not null,
    menu_id							integer 							not null,
    previous_depth		            integer								default 0,
    can_all							boolean                             default false,
    can_read					    boolean                             default false,
    can_write						boolean                             default false,
    can_update						boolean                             default false,
    can_delete						boolean                             default false,
    created_date					timestamp with time zone			default now(),
    constraint user_group_menu_pk 	primary key (user_group_menu_id)
);

comment on table user_group_menu is '사용자 그룹 메뉴';
comment on column user_group_menu.user_group_menu_id is '고유번호';
comment on column user_group_menu.user_group_id is '사용자 그룹 고유키';
comment on column user_group_menu.menu_id is '메뉴 고유키';
comment on column user_group_menu.previous_depth is '이전 메뉴 Depth(thymeleaf 때문)';
comment on column user_group_menu.can_all is '메뉴 접근 모든 권한';
comment on column user_group_menu.can_read is '읽기 권한';
comment on column user_group_menu.can_write is '쓰기 권한';
comment on column user_group_menu.can_update is '수정 권한';
comment on column user_group_menu.can_delete is '삭제 권한';
comment on column user_group_menu.created_date is '등록일';