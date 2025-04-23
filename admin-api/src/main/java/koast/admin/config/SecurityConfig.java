package koast.admin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
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
