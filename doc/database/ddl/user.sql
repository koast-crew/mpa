drop table if exists user_info cascade;

-- 사용자 기본정보
create table user_info (
	user_id						varchar(32),
	user_group_id				integer								not null,
	user_name					varchar(64)							not null,
	password					varchar(512)						not null,
	mobile_phone				varchar(256),
	email						varchar(256),
	messenger_id				varchar(100),
	postal_code					varchar(6),
	address						varchar(256),
	address_etc					varchar(1000),
	status						varchar(30)							default 'ACTIVE',
	login_count					integer								default 0,
	fail_login_count			integer								default 0,
	last_login_date				timestamp with time zone,
	last_password_change_date	timestamp with time zone			default now(),
	modified_date				timestamp with time zone,
	created_date				timestamp with time zone			default now(),
	constraint user_info_pk primary key(user_id)
);

comment on table user_info is '사용자 기본정보';
comment on column user_info.user_id is '아이디';
comment on column user_info.user_group_id is '사용자 그룹 고유번호';
comment on column user_info.user_name is '이름';
comment on column user_info.password is '비밀번호';
comment on column user_info.mobile_phone is '핸드폰';
comment on column user_info.email is '이메일';
comment on column user_info.messenger_id is '메신저 아이디';
comment on column user_info.postal_code is '우편번호';
comment on column user_info.address is '주소';
comment on column user_info.address_etc is '상세주소';
comment on column user_info.status is 
	'사용자 상태. ACTIVE:사용중, DISABLED:사용중지, LOCKED:비밀번호 실패 횟수 초과, DORMANT:휴먼 상태, EXPIRED:사용기간 종료, DELETED:삭제(화면 비표시), TEMP_PASSWORD:임시 비밀번호, PENDING_APPROVAL:승인대기';
comment on column user_info.login_count is '로그인 횟수';
comment on column user_info.fail_login_count is '로그인 실패 횟수';
comment on column user_info.last_login_date is '마지막 로그인 날짜';
comment on column user_info.last_password_change_date is '마지막 로그인 비밀번호 변경 날짜';
comment on column user_info.modified_date is '수정일';
comment on column user_info.created_date is '등록일';

