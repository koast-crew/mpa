package koast.admin.security.auth.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import koast.admin.common.exception.BusinessException;
import koast.admin.common.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Spring Security 인증 실패 시 JSON 응답을 반환하는 핸들러
 * - 비밀번호 틀림, ID 없음, JSON 형식 오류 등 AuthenticationException 처리
 * - ApiResponse 포맷으로 클라이언트에 통일된 에러 응답 제공
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    private final ObjectMapper objectMapper;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
        log.warn("Authentication failed: {}", exception.getMessage());

        ApiResponse<Object> apiResponse = ApiResponse.<Object>builder()
                .payload(null)
                .pagination(null)
                .exception(new BusinessException("AUTH", "AUTHENTICATION_FAILED", exception.getMessage()))
                .build();

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(apiResponse));
    }
}
