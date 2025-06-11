package koast.admin.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
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
                        .anyRequest().authenticated()
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
