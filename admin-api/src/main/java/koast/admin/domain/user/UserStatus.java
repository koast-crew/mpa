package koast.admin.domain.user;

public enum UserStatus {
	// 사용중
    ACTIVE,
	// 사용자 상태가 중지(관리자)
    DISABLED,
	// 사용자 상태가 잠금(비밀번호 실패횟수 초과)
    LOCKED,
	// 사용자 상태가 휴면(로그인 기간)
    DORMANT,
	// 사용자 상태가 만료(사용기간 종료)
    EXPIRED,
	// 사용자 상태가 삭제(화면 비표시)
    DELETED,
	// 사용자 상태가 임시 비밀번호(비밀번호 찾기, 관리자 설정에 의한 임시 비밀번호 발급 시)
    TEMP_PASSWORD,
	// 승인 대기
    PENDING_APPROVAL
}
