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
                .authorizeHttpRequests(authorize -> authorize
                        .anyRequest().permitAll()  // ✅ 모든 요청을 인증 없이 허용
                )
                .csrf(csrf -> csrf.disable())  // ✅ CSRF 비활성화
                .formLogin(form -> form.disable())  // ✅ 로그인 기능 비활성화
                .httpBasic(httpBasic -> httpBasic.disable());  // ✅ HTTP Basic 인증 비활성화

        return http.build();
    }
}
