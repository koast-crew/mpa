package koast.admin.security.auth;

import koast.admin.domain.user.entity.User;
import koast.admin.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

/**
 * 1. 클라이언트가 /login/process 요청
 * 2. AuthenticationFilter → AuthenticationManager 동작. 내부적으로 UserDetailsService.loadUserByUsername() 호출
 * 3. loadUserByUsername(userId) 호출
 * 4. CustomUserDetails 객체 생성. 조회된 사용자 엔티티를 감싸서 반환 (Spring Security가 이해할 수 있는 형태로 변환)
 * 5. 인증 완료 시 SecurityContextHolder에 저장됨. 이후 요청마다 이 사용자 정보가 유지됨 (Principal)
 *
 * Spring Security가 로그인 시 호출하는 사용자 정보 조회 서비스
 */
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        User user = userRepository.findUserWithGroupAndRoles(userId)
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다: " + userId));

        return new CustomUserDetails(user);
    }
}
