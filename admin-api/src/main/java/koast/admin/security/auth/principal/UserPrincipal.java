package koast.admin.security.auth.principal;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;

/**
 * Spring Security 인증 정보로 사용될 사용자 Principal 클래스
 * @author jeongdae
 */
@ToString
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserPrincipal implements UserDetails {

    private static final long serialVersionUID = 2707776980773193089L;

    // 세션 하이재킹 체크용
    private String loginIp;

    // 고유 사용자 ID
    private String userId;

    private Integer userGroupId;
    private String userGroupName;
    private String userName;
    private String password;

    /**
     * 사용자 상태. USE:사용중, PERMISSION_DENIED:사용중지, EXCEEDED_RETRIES:비밀번호 실패 횟수 초과,
     * UNUSED:휴먼 상태, EXPIRED:사용기간 종료, LOGICAL_DELETE:삭제(화면 비표시), TEMP_PASSWORD:임시 비밀번호, WAITING_APPROVAL:승인대기
     */
    private String status;

    private Integer failLoginCount;
    private Boolean lastLoginLockOver;
    private Boolean passwordChangeTermOver;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastLoginDate;

    // Spring Security 권한 정보
    private Collection<? extends GrantedAuthority> authorities = Collections.emptyList();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getUsername() {
        return userId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !"EXCEEDED_RETRIES".equals(status);
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return "USE".equals(status) || "TEMP_PASSWORD".equals(status);
    }
}
