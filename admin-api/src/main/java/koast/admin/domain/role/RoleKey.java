package koast.admin.domain.role;

public enum RoleKey {
	// 관리자 페이지 login
	ADMIN_LOGIN,
	// 관리자 페이지 사용자 관리
	ADMIN_USER_MANAGE,
	// 관리자 페이지 레이어 관리
	ADMIN_LAYER_MANAGE,
	
	// 사용자 페이지 login
	USER_LOGIN,
	// 사용자 페이지 DATA 등록 권한
	USER_DATA_CREATE,
	// 사용자 페이지 DATA 조회 권한
	USER_DATA_READ
}
