package koast.admin.domain.user.dto;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginRequest {
    // 고유번호
    private String userId;
    // 비밀번호
    private String password;

    // 패스워드 변경 주기
    private Integer passwordChangeTerm;
    // 사용자 마지막 사인인으로 부터 잠금 기간
    private Integer userLastLoginLock;
}
