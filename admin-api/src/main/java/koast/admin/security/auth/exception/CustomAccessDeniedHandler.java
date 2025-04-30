package koast.admin.security.auth.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import koast.admin.common.exception.BusinessException;
import koast.admin.common.response.ApiResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 인가 체크
 */
@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType("application/json;charset=UTF-8");

        ApiResponse<Object> apiResponse = ApiResponse.<Object>builder()
                .payload(null)
                .pagination(null)
                .exception(new BusinessException("AUTH", "FORBIDDEN", "권한이 없습니다."))
                .build();

        response.getWriter().write(new ObjectMapper().writeValueAsString(apiResponse));
    }
}
