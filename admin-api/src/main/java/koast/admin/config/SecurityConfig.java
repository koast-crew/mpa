package koast.admin.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import koast.admin.domain.role.RoleKey;
import koast.admin.security.auth.filter.JsonUsernamePasswordAuthenticationFilter;
import koast.admin.security.auth.handler.CustomAuthenticationFailureHandler;
import koast.admin.security.auth.handler.CustomAuthenticationSuccessHandler;
import koast.admin.security.auth.exception.CustomAccessDeniedHandler;
import koast.admin.security.auth.exception.CustomAuthenticationEntryPoint;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomAuthenticationEntryPoint entryPoint;
    private final CustomAccessDeniedHandler accessDeniedHandler;
    private final CustomAuthenticationSuccessHandler successHandler;
    private final CustomAuthenticationFailureHandler failureHandler;
    private final ObjectMapper objectMapper;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, AuthenticationConfiguration authenticationConfiguration) throws Exception {

        // client에서 보내는 /login/process 요청을 spring controller가 아닌 security filter chain에서 커스텀 필터 처리
        JsonUsernamePasswordAuthenticationFilter jsonFilter = new JsonUsernamePasswordAuthenticationFilter(objectMapper);
        jsonFilter.setAuthenticationManager(authenticationManager(authenticationConfiguration));
        jsonFilter.setAuthenticationSuccessHandler(successHandler);
        jsonFilter.setAuthenticationFailureHandler(failureHandler);
        jsonFilter.setFilterProcessesUrl("/login/process");

        http
                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint(entryPoint)
                        .accessDeniedHandler(accessDeniedHandler)
                )
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login/**", "/css/**", "/js/**", "/images/**").permitAll()
// 추가적인 권한 체크가 필요한 경우
//                        .requestMatchers("/user/create/**").hasAuthority("USER_CREATE_ROLE")
                        // 관리자 페이지 접근 권한이 있는 사용자 그룹만 접근 가능
                        .anyRequest().hasAuthority(RoleKey.ADMIN_LOGIN.name())
                )
                .addFilterAt(jsonFilter, JsonUsernamePasswordAuthenticationFilter.class)
                .sessionManagement(session -> session
                        .maximumSessions(1)
                )
                .csrf(csrf -> csrf.disable());

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
