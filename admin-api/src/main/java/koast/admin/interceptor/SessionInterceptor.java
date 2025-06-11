package koast.admin.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import koast.admin.common.exception.BusinessException;
import koast.admin.common.response.ApiResponse;
import org.springframework.web.servlet.HandlerInterceptor;

public class SessionInterceptor implements HandlerInterceptor {

    private static final String USER_SESSION_KEY = "USER_SESSION";
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute(USER_SESSION_KEY) == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json;charset=UTF-8");

            ApiResponse<Object> apiResponse = ApiResponse.<Object>builder()
                    .payload(null)
                    .pagination(null)
                    .exception(new BusinessException("AUTH", "SESSION_INVALID", "세션이 존재하지 않거나 만료되었습니다."))
                    .build();

            response.getWriter().write(objectMapper.writeValueAsString(apiResponse));
            return false;
        }

        return true;
    }
}
