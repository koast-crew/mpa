// 위치: koast.admin.security.auth.CustomUserDetails.java
package koast.admin.security.auth;

import koast.admin.domain.user.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * 1. 클라이언트가 /login/process 요청
 * 2. AuthenticationFilter → AuthenticationManager 동작. 내부적으로 UserDetailsService.loadUserByUsername() 호출
 * 3. loadUserByUsername(userId) 호출
 * 4. CustomUserDetails 객체 생성. 조회된 사용자 엔티티를 감싸서 반환 (Spring Security가 이해할 수 있는 형태로 변환)
 * 5. 인증 완료 시 SecurityContextHolder에 저장됨. 이후 요청마다 이 사용자 정보가 유지됨 (Principal)
 *
 * 로그인한 사용자의 정보와 권한을 Spring Security가 인증 및 인가 처리에 사용할 수 있도록 제공
 */
public class CustomUserDetails implements UserDetails {

    private static final long serialVersionUID = -5452586562577340207L;
    private final User user;

    public CustomUserDetails(User user) {
        this.user = user;
    }

    /**
     * 로그인 권한 ADMIN_LOGIN 체크
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getUserGroup().getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getRoleName()))
                .collect(Collectors.toList());
    }

    @Override public String getUsername() { return user.getUserName(); }
    @Override public String getPassword() { return user.getPassword(); }
    @Override public boolean isAccountNonExpired() { return true; }
    @Override public boolean isAccountNonLocked() { return true; }
    @Override public boolean isCredentialsNonExpired() { return true; }
    @Override public boolean isEnabled() { return true; }

    public User getUser() { return user; }
}
