package koast.admin.security.auth.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import koast.admin.common.response.ApiResponse;
import koast.admin.domain.user.auth.UserPrincipal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 로그인 성공 시 사용자 정보를 세션에 저장하고,
 * JSON 형식으로 응답하는 Spring Security 인증 성공 핸들러
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        log.info("Authentication success for: {}", authentication.getName());

        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

        // ✅ 사용자 정보를 세션에 저장
        request.getSession().setAttribute("USER_SESSION", userPrincipal);

        // ✅ 사용자 정보를 JSON으로 응답
        ApiResponse<Object> apiResponse = ApiResponse.builder()
                .payload(userPrincipal)
                .pagination(null)
                .exception(null)
                .build();

        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(apiResponse));
    }
}
