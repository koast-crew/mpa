package koast.admin.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;

public class UserController {


    /**
     * 메서드 단위에서 특정 Role을 체크하는 경우
     */
    @PreAuthorize("hasAuthority('USER_CREATE_ROLE')")
    @PostMapping("/user/create")
    public void createUser() {
    }

}
