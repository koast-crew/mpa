// 위치: koast.admin.security.auth.CustomUserDetails.java
package koast.admin.security.auth;

import koast.admin.domain.user.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * User 객체로부터 권한(ROLE_ADMIN, ROLE_USER 등)을 가져와 Spring Security에 제공
 */
public class CustomUserDetails implements UserDetails {

    private final User user;

    public CustomUserDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getUserGroup().getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName())) // "ROLE_ADMIN"
                .collect(Collectors.toList());
    }

    @Override public String getUsername() { return user.getUsername(); }
    @Override public String getPassword() { return user.getPassword(); }
    @Override public boolean isAccountNonExpired() { return true; }
    @Override public boolean isAccountNonLocked() { return true; }
    @Override public boolean isCredentialsNonExpired() { return true; }
    @Override public boolean isEnabled() { return true; }

    public User getUser() { return user; } // 필요 시 getter 제공
}
