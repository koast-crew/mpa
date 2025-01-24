package koast.admin.domain.user.entity;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * TODO 해경 갈때는 Thead Safe, 스프링 관리, 캐시 라이브러리 사용하자.
 * 운영 정책
 * @author jeongdae
 *
 */
@ToString
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Policy implements Serializable {

	private static final long serialVersionUID = -1355073331725091081L;

	// 고유번호
    private Long policyId;
	
	// 사용자 아이디 최소 길이. 기본값 5
 	private Integer userIdMinLength;
 	// 사용자 로그인 실패 횟수
 	private Integer userFailLoginCount;
 	// 사용자 로그인 실패 잠금 해제 기간
 	private String userFailLockRelease;
 	// 사용자 마지막 로그인으로 부터 잠금 기간
 	private String userLastLoginLock;
 	// 사용자 중복 로그인 허용 여부. true : 허용, false : 허용안함(기본값)
 	private Boolean userDuplicationLogin;
 	// 사용자 로그인 인증 방법. BASIC : 일반(아이디/비밀번호(기본값)), COMPANY : 기업용(사번추가)
 	private String userLoginType;
 	// 사용자 정보 수정시 확인
 	private Boolean userUpdateCheck;
 	// 사용자 정보 삭제시 확인
 	private Boolean userDeleteCheck;
 	// 사용자 정보 삭제 방법. LOGICAL : 논리적(기본값), PHYSICAL : 물리적(DB 삭제)
 	private String userDeleteType;
	// 회원 가입 후 승인 방법. AUTO : 승인 없이 사용, APPROVAL : 승인 후 사용
	private String signupType;
	
	// 패스워드 변경 주기 기본 30일
 	private String passwordChangeTerm;
 	// 패스워드 최소 길이 기본 8
 	private Integer passwordMinLength;
 	// 패스워드 최대 길이 기본 32
 	private Integer passwordMaxLength;
 	// 패스워드 영문 대문자 개수 기본 1
 	private Integer passwordEngUpperCount;
 	// 패스워드 영문 소문자 개수 기본 1
 	private Integer passwordEngLowerCount;
 	// 패스워드 숫자 개수 기본 1
 	private Integer passwordNumberCount;
 	// 패스워드 특수 문자 개수 1
 	private Integer passwordSpecialCharCount;
 	// 패스워드 연속문자 제한 개수 3
 	private Integer passwordContinuousCharCount;
 	// 초기 패스워드 생성 방법. BASIC : 사용자 아이디 + 초기문자(기본값), 1 : 초기문자
 	private String passwordCreateType;
 	// 초기 패스워드 생성 문자열. 엑셀 업로드 등
 	private String passwordCreateChar;
 	// 패스워드로 사용할수 없는 특수문자(XSS). <,>,&,작은따음표,큰따움표
 	private String passwordExceptionChar;
 	
 	// 보안 세션 타임아웃. true : 사용, false 미사용(기본값)
  	private Boolean securitySessionTimeoutCheck;
  	// 보안 세션 타임아웃 시간. 30분
  	private String securitySessionTimeout;
 	
 	// css, js 갱신용 cache version.
 	private Integer contentCacheVersion;
 	// 메뉴 그룹 최상위 그룹명
 	private String contentMenuGroupRoot;
 	// 사용자 그룹 최상위 그룹명
	private String contentUserGroupRoot;

	//  배경맵. 해아름맵 전자해도(기본값) : HAEARUM_ENC(Electronic Navigational Chart), 해아름맵 : HAEARUM
	private String sarBasemapType;
	// 로그인 후 이동할 위치. PROCESS : 진행중인 sar 위치, LOCAL : 본인 관할 구역
	private String sarInitPointType;
	// sar 정보(목록) 표시 방법. ALL : 전체, OWNER : 자신, LOCAL : 관할 경찰청
 	private String sarDisplayScope;
	// sar 정보(목록) 표시 기본 상태. UNRESOLVED : 미해결, NONE : 아무것도 표시하지 않음
	private String sarDisplayStatus;
	// sar 레이어 표시 방법. ALL : 전체, INCIDENT_ONLY : 사고 지역만
	private String sarLayerScope;
 	// opendrift 입자 표시 방법. NUMBER(기본값) : 숫자, CIRCLE : 원(포인트)
 	private String opendriftParticlesDisplayType;
 	// 위경도 표시 방법. DMS(기본값) : 도분초, DM : 도분, D : 도
 	private String degreeDisplayType;
 	// 해류(고생상도) 색깔. deep blue(기본), blue(4,117,244), blue(37,143,255), light blue(138,199,219), white(255,255,255) 
 	private String currentColorType;
 	
 	
 	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDate;
}
