package koast.admin.domain.user.session;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 세션에 저장될 사용자 정보
 * @author jeongdae
 *
 */
@ToString
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserSession implements Serializable {

    private static final long serialVersionUID = -2416233296928075738L;

    // 세션 하이재킹 체크
    private String loginIp;

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

    /**
     * 사용자 상태. USE:사용중, PERMISSION_DENIED:사용중지, EXCEEDED_RETRIES:비밀번호 실패 횟수 초과, UNUSED:휴먼 상태, EXPIRED:사용기간 종료,
     * LOGICAL_DELETE:삭제(화면 비표시), TEMP_PASSWORD:임시 비밀번호, WAITING_APPROVAL:승인대기
     */
    private String status;

    // 사인인 실패 횟수
    private Integer failLoginCount;
    // 일정 기간 동안 미 접속시 잠금 처리 결과 값
    private Boolean lastLoginLockOver;
    // 패스워드 변경 주기 값
    private Boolean passwordChangeTermOver;

    // 마지막 사인인 날짜
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastLoginDate;
}
