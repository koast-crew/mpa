package koast.admin.config;

import koast.admin.security.auth.exception.CustomAccessDeniedHandler;
import koast.admin.security.auth.exception.CustomAuthenticationEntryPoint;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomAuthenticationEntryPoint entryPoint;
    private final CustomAccessDeniedHandler accessDeniedHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // 인증 실패 시 401 응답(JSON)을 반환하는 핸들러
                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint(entryPoint)
                        // 인가(권한) 실패 시 403 응답(JSON)을 반환하는 핸들러
                        .accessDeniedHandler(accessDeniedHandler)
                )
                // 예외 URL 설정 (정적 리소스 및 /login/* 예외 처리)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login/**", "/css/**", "/js/**", "/images/**").permitAll()
                        .anyRequest().authenticated()
                )
                // 세션 관리 설정
                .sessionManagement(session -> session
                        .invalidSessionUrl("/login/login")
                        .maximumSessions(1)
                        .expiredUrl("/login/login")
                )
                .csrf(csrf -> csrf.disable());

        return http.build();
    }


    @Bean
    public SecurityFilterChain securityFilterChaina(HttpSecurity http) throws Exception {
        http
//                // 모든 요청을 HTTPS로 강제
//                .requiresChannel(channel -> channel
//                        .anyRequest().requiresSecure()
//                )
                // 예외 URL 설정 (정적 리소스 및 /login/* 예외 처리)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login/**", "/css/**", "/js/**", "/images/**").permitAll()
                        .anyRequest().authenticated()
                )
                // 로그인 페이지 설정
                .formLogin(form -> form
                        .loginPage("/login/login")
                        .defaultSuccessUrl("/", true)
                        .permitAll()
                )
                // 로그아웃 설정
                .logout(logout -> logout
                        .logoutUrl("/login/logout")
                        .logoutSuccessUrl("/login/login")
                        .invalidateHttpSession(true) // 로그아웃 시 세션 무효화
                        .deleteCookies("JSESSIONID")
                        .permitAll()
                )
                // 세션 관리 설정
                .sessionManagement(session -> session
                        .invalidSessionUrl("/login/login")
                        .maximumSessions(1)
                        .expiredUrl("/login/login")
                )
                .csrf(csrf -> csrf.disable());

        return http.build();
    }
}
