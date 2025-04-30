package koast.admin.controller;

import jakarta.servlet.http.HttpServletRequest;
import koast.admin.common.response.ApiResponse;
import koast.admin.domain.policy.dto.PolicyResponse;
import koast.admin.service.PolicyService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO
 * 비밀번호 Argon2가 훨씬 빠르고 안전하지만 해싱 하나당 약 64MB 메모리를 사용함, BCrypt 10KB 미만
 * 나는 정말 로그인으로 만들고 싶지 않았다. 그러나 우리 나라는 SignIn/Out/Up을 잘 사용하지 않는다.
 */
@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/login")
public class LoginController {
//    private final BCryptPasswordEncoder bCryptPasswordEncoder;
//    private final LoginService loginService;
    private final PolicyService policyService;

    /**
     * 로그인 페이지
     * @param request
     * @return
     */
    @GetMapping("/login")
    public ApiResponse<PolicyResponse> login(HttpServletRequest request) {
        PolicyResponse policyResponse = policyService.getLatest();
        log.info("@@ policyResponse = {}", policyResponse);

        return ApiResponse.<PolicyResponse>builder().payload(policyResponse).build();
    }
}
