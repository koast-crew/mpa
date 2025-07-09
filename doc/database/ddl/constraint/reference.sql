--foreign key 설정 : 식별자는 "(테이블명)_fk_(칼럼명)"으로 통일
alter table only user_info add constraint user_info_fk_user_group_id foreign key (user_group_id) references user_group(user_group_id);
alter table only user_group_role add constraint user_group_role_fk_user_group_id foreign key (user_group_id) references user_group(user_group_id);
alter table only user_group_role add constraint user_group_role_fk_role_id foreign key (role_id) references role(role_id);
