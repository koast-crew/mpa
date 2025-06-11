package koast.admin.domain.user.auth;

import koast.admin.domain.user.UserStatus;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;

/**
 * Spring Security에서 사용하는 인증된 사용자 정보 객체
 * 기존 UserSession의 기능을 포함하면서 UserDetails를 구현함
 */
@ToString
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserPrincipal implements UserDetails, Serializable {

    private static final long serialVersionUID = -5380927544971093451L;

    // 세션 하이재킹 체크용 IP
    private String loginIp;

    // 사용자 고유 ID
    private String userId;

    // 사용자 그룹
    private Integer userGroupId;
    private String userGroupName;

    // 사용자 이름
    private String userName;

    // 비밀번호
    private String password;

    // 사용자 상태 ACTIVE:사용중, DISABLED:사용중지, LOCKED:비밀번호 실패 횟수 초과, DORMANT:휴먼 상태, EXPIRED:사용기간 종료, DELETED:삭제(화면 비표시), TEMP_PASSWORD:임시 비밀번호, PENDING_APPROVAL:승인대기
    private UserStatus status;

    // 실패 횟수 / 잠금 여부 / 비밀번호 만료 여부
    private Integer failLoginCount;
    private Boolean lastLoginLockOver;
    private Boolean passwordChangeTermOver;

    // 마지막 로그인 일시
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastLoginDate;

    // 권한 목록 (없을 경우 빈 리스트 반환 가능)
    private Collection<? extends GrantedAuthority> authorities;

    /**
     * 사용자 권한 목록 반환
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    // ID 반환
    @Override
    public String getUsername() {
        return userId;
    }

    // 계정 만료 여부 (사용기간 만료 시 false)
    @Override
    public boolean isAccountNonExpired() {
        return !(status == UserStatus.EXPIRED
                || status == UserStatus.PENDING_APPROVAL
                || status == UserStatus.DORMANT);
    }

    // 계정 잠금 여부 (LOCKED 상태면서 잠금 해제 조건 미달 시 false)
    @Override
    public boolean isAccountNonLocked() {
        return status != UserStatus.LOCKED || Boolean.TRUE.equals(lastLoginLockOver);
    }

    // 자격 증명 만료 여부 (비밀번호 주기 초과 시 false)
    @Override
    public boolean isCredentialsNonExpired() {
        return !Boolean.TRUE.equals(passwordChangeTermOver);
    }

    // 계정 활성화 여부 (ACTIVE, TEMP_PASSWORD만 true)
    @Override
    public boolean isEnabled() {
        return status == UserStatus.ACTIVE || status == UserStatus.TEMP_PASSWORD;
    }
}
