package koast.admin.service;

import koast.admin.domain.user.dto.UserLoginRequest;
import koast.admin.domain.user.session.UserSession;
import koast.admin.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Transactional(readOnly=true)
    public UserSession findByUserIdAndPolicy(UserLoginRequest userLoginRequest) {
        return userRepository.findByUserIdAndPolicy(userLoginRequest);
    }

    @Transactional
    public int updateLoginUser(UserSession userSession) {
        return userRepository.updateLoginUser(userSession);
    }
}
