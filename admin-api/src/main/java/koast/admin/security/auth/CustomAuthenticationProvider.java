package koast.admin.security.auth;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import koast.admin.domain.policy.dto.PolicyResponse;
import koast.admin.domain.user.UserStatus;
import koast.admin.domain.user.dto.UserLoginRequest;
import koast.admin.domain.user.session.UserSession;
import koast.admin.service.PolicyService;
import koast.admin.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.List;

/**
 * 사용자 인증 처리 Provider
 * 기존 로그인 로직을 Spring Security 구조로 변환하여 처리함
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private final PolicyService policyService;
    private final UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String userId = authentication.getName();
        String password = authentication.getCredentials().toString();
        log.info("📥 [CustomAuthenticationProvider] 로그인 시도 - userId: {}", userId);

        if (userId == null || userId.isBlank() || password == null || password.isBlank()) {
            throw new BadCredentialsException("아이디와 비밀번호를 모두 입력해야 합니다.");
        }

        PolicyResponse policyResponse = policyService.getLatest();
        UserLoginRequest userLoginRequest = UserLoginRequest.builder()
                .userId(userId)
                .passwordChangeTerm(policyResponse.getPasswordChangeTerm())
                .userLastLoginLock(policyResponse.getUserLastLoginLock())
                .build();
        UserSession userSession = userService.findByUserIdAndPolicy(userLoginRequest);

        checkLoginConditions(userSession, policyResponse, password);

        // 로그인 성공 시 실패 횟수 초기화 및 사용자 정보 업데이트
        userSession.setFailLoginCount(0);
        userService.updateLoginUser(userSession);

        // 세션에 사용자 정보 저장
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.setAttribute("USER_SESSION", userSession);

            if (Boolean.TRUE.equals(policyResponse.getSecuritySessionTimeoutEnabled())) {
                session.setMaxInactiveInterval(Integer.parseInt(policyResponse.getSecuritySessionTimeout()) * 60);
            }
        }

        // 패스워드 만료 또는 임시 비밀번호 상태 처리
        if (Boolean.TRUE.equals(userSession.getPasswordChangeTermOver()) || UserStatus.TEMP_PASSWORD.name().equals(userSession.getStatus())) {
            throw new BadCredentialsException("usersession.password.change.required");
        }

        return new UsernamePasswordAuthenticationToken(userId, null, List.of());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }

    /**
     * 사용자 로그인 조건 검증
     */
    private void checkLoginConditions(UserSession userSession, PolicyResponse policyResponse, String password) {
        if (userSession == null) {
            throw new BadCredentialsException("user.session.empty");
        }

        if (!bCryptPasswordEncoder.matches(password, userSession.getPassword())) {
            userSession.setFailLoginCount(userSession.getFailLoginCount() + 1);
            if (userSession.getFailLoginCount() >= policyResponse.getUserFailLoginCount()) {
                userSession.setStatus(UserStatus.DISABLED.name());
            }
            userService.updateLoginUser(userSession);
            throw new BadCredentialsException("usersession.password.invalid");
        }

        if (!(UserStatus.ACTIVE.name().equals(userSession.getStatus()) || UserStatus.TEMP_PASSWORD.name().equals(userSession.getStatus()))) {
            throw new BadCredentialsException("usersession.status.invalid");
        }

        if (userSession.getFailLoginCount() >= policyResponse.getUserFailLoginCount()) {
            throw new BadCredentialsException("usersession.faillogincount.invalid");
        }

        if (Boolean.TRUE.equals(userSession.getLastLoginLockOver())) {
            throw new BadCredentialsException("usersession.lastlogin.invalid");
        }

        // 중복 로그인 제한 정책 적용
        if (!policyResponse.getUserDuplicationLogin()) {
            // TODO: SessionRegistry 기반 세션 만료 처리 필요 시 여기에 추가
        }
    }
}
