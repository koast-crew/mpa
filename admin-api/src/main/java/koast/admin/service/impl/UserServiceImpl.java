package koast.admin.service.impl;

import koast.admin.domain.user.dto.UserLoginRequest;
import koast.admin.domain.user.session.UserSession;
import koast.admin.repository.UserRepository;
import koast.admin.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    /**
     * 로그인 사용자 정보를 취득
     * @param userLoginRequest
     * @return
     */
    @Transactional(readOnly=true)
    public UserSession findByUserIdAndPolicy(UserLoginRequest userLoginRequest) {
        return userRepository.findByUserIdAndPolicy(userLoginRequest);
    }

    /**
     * 로그인 사용자 정보 수정
     * @param userSession
     * @return
     */
    @Transactional
    public int updateLoginUser(UserSession userSession) {
        return userRepository.updateLoginUser(userSession);
    }
}
