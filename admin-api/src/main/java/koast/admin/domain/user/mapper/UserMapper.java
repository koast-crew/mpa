package koast.admin.domain.user.mapper;

import koast.admin.domain.common.mapper.BaseMapper;
import koast.admin.domain.policy.dto.PolicyRequest;
import koast.admin.domain.policy.dto.PolicyResponse;
import koast.admin.domain.policy.entity.Policy;
import koast.admin.domain.user.dto.UserRequest;
import koast.admin.domain.user.dto.UserResponse;
import koast.admin.domain.user.entity.User;
import org.mapstruct.Mapper;

/**
 * Entity <-> Dto 전환 클래스
 */
@Mapper(componentModel = "spring")
public interface UserMapper extends BaseMapper<UserRequest, User, UserResponse> {}
