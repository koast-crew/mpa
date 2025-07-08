package koast.admin.domain.user.dto;

import koast.admin.common.paging.BaseSearch;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

public class UserRequest extends BaseSearch implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 4422775693721872764L;

    /******** 화면 오류 표시용 ********/
    private String message;
    private String errorCode;

    // 일정 기간 동안 미 접속시 잠금 처리(예 3개월 90일)
    private String lastLoginLock;
    // 아이디 중복 확인 hidden 값
    private String duplicationValue;

    // 아이디 중복 조회 결과 값
    private boolean exists;

    /****** validator ********/
    private String methodMode;

    /********** Policy ************/
    // 사용자 사인인 실패 잠금 해제 기간
    private String userFailLockRelease;

    /********** DB 사용 *************/
    // 고유번호
    private String userId;
    // 사용자 그룹 고유번호
    private Integer userGroupId;
    // 사용자 그룹명(화면용)
    private String userGroupName;
    // 이름
    private String userName;
    // 비밀번호
    private String password;
    // 비밀번호 확인
    private String passwordConfirm;

    // 핸드폰 번호1
    private String mobilePhone1;
    // 핸드폰 번호2
    private String mobilePhone2;
    // 핸드폰 번호3
    private String mobilePhone3;
    // 핸드폰 번호
    private String mobilePhone;
    // 이메일
    private String email;
    // 이메일1
    private String email1;
    // 이메일2
    private String email2;
    // 변경할 이메일
    private String newEmail;
    // 메신저 아이디
    private String messenger;
    // 우편번호
    private String postalCode;
    // 주소
    private String address;
    // 상세주소
    private String addressEtc;
    // 로그인 횟수
    private Long loginCount;
    // 로그인 실패 횟수
    private Integer failLoginCount;
    // 마지막 로그인 비밀번호 변경 날짜
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastPasswordChangeDate;
    // 마지막 로그인 날짜
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastLoginDate;
    // 사용자 상태. USE:사용중, PERMISSION_DENIED:사용중지, EXCEEDED_RETRIES:비밀번호 실패 횟수 초과, UNUSED:휴먼 상태, EXPIRED:사용기간 종료,
    // LOGICAL_DELETE:삭제(화면 비표시), PASSWORD_EXPIRATION:비밀번호 유효기간 만료, TEMP_PASSWORD:임시 비밀번호, WAITING_APPROVAL:승인대기
    private String status;
    // 로그인 타입. BASIC :일반(플랫폼), SOCIAL : 소셜 로그인(구글, 페이스북, 네이버, 카카오)
    private String loginType;
    // 회원가입 타입. BASIC :일반(플랫폼), SOCIAL : 소셜 로그인(구글, 페이스북, 네이버, 카카오)
    private String signupType;
    // 현재 사용자 상태값
    private String dbStatus;
    // 새로운 비밀번호
    private String newPassword;
    // 새로운 비밀번호 확인
    private String newPasswordConfirm;
    // 패스워드 변경 주기
    private String passwordChangeTerm;
    // 패스워드 변경 주기 값
    private Boolean passwordChangeTermOver;
    // 임시 비밀번호
    private String tempPassword;
    // 일정 기간 동안 미 접속시 잠금 처리 결과 값
    private Boolean lastLoginLockOver;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime modifiedDate;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdDate;
}

