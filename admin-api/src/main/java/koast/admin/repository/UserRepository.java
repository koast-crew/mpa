package koast.admin.repository;

import koast.admin.domain.user.dto.UserLoginRequest;
import koast.admin.domain.user.dto.UserResponse;
import koast.admin.domain.user.dto.UserWithGroupAndRolesResponse;
import koast.admin.domain.user.session.UserSession;

public interface UserRepository {
    /**
     * 로그인 사용자 정보를 취득
     * @param userLoginRequest
     * @return
     */
    UserSession findByUserIdAndPolicy(UserLoginRequest userLoginRequest);

    /**
     * 사용자 그룹 및 롤 정보 조회
     * @param userId
     * @return
     */
    UserWithGroupAndRolesResponse findUserWithGroupAndRoles(String userId);

    /**
     * 로그인 사용자 정보 수정
     * @param userSession
     * @return
     */
    int updateLoginUser(UserSession userSession);
}
