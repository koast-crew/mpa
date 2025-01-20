drop table if exists policy cascade;

-- 운영정책
create table policy (
	policy_id								integer,
	
	user_id_min_length						integer				default 5,
	user_fail_login_count					integer				default 3,
	user_fail_lock_release					varchar(3)			default '30',
	user_last_login_lock					varchar(3)			default '90',
	user_duplication_login					boolean				default false,
	user_login_type							varchar(20)			default 'BASIC',
	user_update_check						boolean				default false,
	user_delete_check						boolean				default false,
	user_delete_type						varchar(20)			default 'PHYSICAL',
	signup_type                             varchar(20)         default 'AUTO',
	
	password_change_term 					varchar(3)			default '30',
	password_min_length						integer				default 8,
	password_max_length						integer				default 32,
	password_eng_upper_count 				integer				default 1,
	password_eng_lower_count 				integer				default 1,
	password_number_count 					integer				default 1,
	password_special_char_count 			integer				default 1,
	password_continuous_char_count 			integer				default 3,
	password_create_type					varchar(20)			default 'BASIC',
	password_create_char					varchar(32)			default '!@#',
	password_exception_char					varchar(10)			default '<>&',
	
	security_session_timeout_check			boolean				default false,
	security_session_timeout				varchar(4)			default '30',
	
	content_cache_version					integer				default 1,
	content_menu_group_root					varchar(60)			default 'AISAR',
	content_user_group_root					varchar(60)			default 'AISAR',

    sar_basemap_type                        varchar(30)         default 'HAEARUM_ENC',
    sar_init_point_type                     varchar(30)         default 'PROCESS',
	sar_display_scope					    varchar(20)			default 'ALL',
    sar_display_status                      varchar(20)			default 'UNRESOLVED',
    sar_layer_scope                         varchar(20)         default 'ALL',
	degree_display_type						varchar(3)			default 'DMS',	
	opendrift_particles_display_type		varchar(6)			default 'NUMBER',
	current_color_type						varchar(11)			default '37,143,255',
	
	user_upload_type						varchar(256)		default '.jpeg,.jpg,.png,.gif,.JPEG,.JPG,.PNG,.GIF,.txt,.zip',
	
	created_date							timestamp with time zone			default now(),
	constraint policy_pk primary key (policy_id)	
);

comment on table policy is '운영정책';
comment on column policy.policy_id is '고유번호';

comment on column policy.user_id_min_length is '사용자 아이디 최소 길이. 기본값 5';
comment on column policy.user_fail_login_count is '사용자 사인인 실패 횟수';
comment on column policy.user_fail_lock_release is '사용자 사인인 실패 잠금 해제 기간';
comment on column policy.user_last_login_lock is '사용자 마지막 사인인으로 부터 잠금 기간';
comment on column policy.user_duplication_login is '중복 사인인 허용 여부. true : 허용, false : 허용안함(기본값)';
comment on column policy.user_login_type is '사용자 사인인 인증 방법. BASIC : 일반(아이디/비밀번호(기본값)), COMPANY : 기업용(사번추가)';
comment on column policy.user_update_check is '사용자 정보 수정시 확인';
comment on column policy.user_delete_check is '사용자 정보 삭제시 확인';
comment on column policy.user_delete_type is '사용자 정보 삭제 방법. LOGICAL : 논리적(기본값), PHYSCIAL : 물리적(DB 삭제)';
comment on column policy.signup_type is '회원 가입 후 승인 방법. AUTO : 승인 없이 사용, APPROVAL : 승인 후 사용';

comment on column policy.password_change_term is '패스워드 변경 주기 기본 30일';
comment on column policy.password_min_length is '패스워드 최소 길이 기본 8';
comment on column policy.password_max_length is '패스워드 최대 길이 기본 32';
comment on column policy.password_eng_upper_count is '패스워드 영문 대문자 개수 기본 1';
comment on column policy.password_eng_lower_count is '패스워드 영문 소문자 개수 기본 1';
comment on column policy.password_number_count is '패스워드 숫자 개수 기본 1';
comment on column policy.password_special_char_count is '패스워드 특수 문자 개수 1';
comment on column policy.password_continuous_char_count is '패스워드 연속문자 제한 개수 3';
comment on column policy.password_create_type is '초기 패스워드 생성 방법. 0 : 사용자 아이디 + 초기문자(기본값), 1 : 초기문자';
comment on column policy.password_create_char is '초기 패스워드 생성 문자열. 엑셀 업로드 등';
comment on column policy.password_exception_char is '패스워드로 사용할수 없는 특수문자(XSS). <,>,&,작은따음표,큰따움표';

comment on column policy.security_session_timeout_check is '보안 세션 타임아웃. true : 사용, false 사용안함(기본값)';
comment on column policy.security_session_timeout is '보안 세션 타임아웃 시간. 기본값 30분';

comment on column policy.content_cache_version is 'css, js 갱신용 cache version.';
comment on column policy.content_menu_group_root is '메뉴 그룹 최상위 그룹명';
comment on column policy.content_user_group_root is '사용자 그룹 최상위 그룹명';

comment on column policy.sar_basemap_type is '배경맵. 해아름맵 전자해도(기본값) : HAEARUM_ENC(Electronic Navigational Chart), 해아름맵 : HAEARUM';
comment on column policy.sar_init_point_type is '로그인 후 이동할 위치. PROCESS : 진행중인 sar 위치, LOCAL : 본인 관할 구역';
comment on column policy.sar_display_scope is 'sar 정보(목록) 표시 방법. ALL : 전체, OWNER : 자신, LOCAL : 관할 경찰청';
comment on column policy.sar_display_status is 'sar 정보(목록) 표시 기본 상태. UNRESOLVED : 미해결, NONE : 아무것도 표시하지 않음';
comment on column policy.sar_layer_scope is 'sar 레이어 표시 방법. ALL : 전체, INCIDENT_ONLY : 사고 지역만';
comment on column policy.opendrift_particles_display_type is 'opendrift 입자 표시 방법. NUMBER(기본값) : 숫자, CIRCLE : 원(포인트)';
comment on column policy.degree_display_type is '위경도 표시 방법. DMS(기본값) : 도분초, DM : 도분, D : 도';
comment on column policy.current_color_type is '해류(고생상도) 색깔. deep blue(4,117,244 : 기본), blue(37,143,255), light blue(138,199,219), white(255,255,255)';

comment on column policy.user_upload_type is '업로딩 가능 확장자. .jpeg,.jpg,.png,.gif,.JPEG,.JPG,.PNG,.GIF,.txt,.zip';
comment on column policy.created_date is '등록일';