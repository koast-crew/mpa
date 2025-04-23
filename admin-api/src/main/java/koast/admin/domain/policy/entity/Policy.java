package koast.admin.domain.policy.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@ToString
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Policy {

    // 고유번호
    private Integer policyId;

    // 사용자 아이디 최소 길이
    private Integer userIdMinLength;
    // 사용자 사인인 실패 횟수
    private Integer userFailLoginCount;
    // 사용자 사인인 실패 잠금 해제 기간
    private Integer userFailLockRelease;
    // 사용자 마지막 사인인으로 부터 잠금 기간
    private Integer userLastLoginLock;
    // 중복 사인인 허용 여부
    private Boolean userDuplicationLogin;
    // 사용자 사인인 인증 방법
    private String userLoginType;
    // 사용자 정보 수정시 확인
    private Boolean userUpdateConfirmation;
    // 사용자 정보 삭제시 확인
    private Boolean userDeleteConfirmation;
    // 사용자 정보 삭제 방법
    private String userDeleteType;
    // 회원 가입 후 승인 방법
    private String loginType;

    // 패스워드 변경 주기
    private Integer passwordChangeTerm;
    // 패스워드 최소 길이
    private Integer passwordMinLength;
    // 패스워드 최대 길이
    private Integer passwordMaxLength;
    // 패스워드 영문 대문자 개수
    private Integer passwordEngUpperCount;
    // 패스워드 영문 소문자 개수
    private Integer passwordEngLowerCount;
    // 패스워드 숫자 개수
    private Integer passwordNumberCount;
    // 패스워드 특수 문자 개수
    private Integer passwordSpecialCharCount;
    // 패스워드 연속문자 제한 개수
    private Integer passwordContinuousCharCount;
    // 초기 패스워드 생성 방법
    private String passwordCreateType;
    // 초기 패스워드 생성 문자열
    private String passwordCreateChar;
    // 패스워드로 사용할수 없는 특수문자
    private String passwordExceptionChar;

    // 보안 세션 타임아웃 사용 여부
    private Boolean securitySessionTimeoutEnabled;
    // 보안 세션 타임아웃 시간
    private String securitySessionTimeout;

    // css, js 갱신용 cache version
    private Integer contentCacheVersion;
    // 메뉴 그룹 최상위 그룹명
    private String contentMenuGroupRoot;
    // 사용자 그룹 최상위 그룹명
    private String contentUserGroupRoot;

    // 배경맵
    private String basemapType;

    // 등록일
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdDate;
}
