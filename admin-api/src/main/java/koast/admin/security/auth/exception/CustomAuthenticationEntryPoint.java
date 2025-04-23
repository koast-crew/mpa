package koast.admin.security.auth.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import koast.admin.common.exception.BusinessException;
import koast.admin.common.response.ApiResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json;charset=UTF-8");

        ApiResponse<Object> apiResponse = ApiResponse.<Object>builder()
                .status(401)
                .payload(null)
                .pagination(null)
                .exception(new BusinessException("AUTH", "AUTH_REQUIRED", "인증이 필요합니다."))
                .build();

        response.getWriter().write(new ObjectMapper().writeValueAsString(apiResponse));
    }
}
