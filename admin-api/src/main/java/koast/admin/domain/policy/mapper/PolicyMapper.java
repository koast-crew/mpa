package koast.admin.domain.policy.mapper;

import koast.admin.common.mapper.BaseMapper;
import koast.admin.domain.policy.dto.PolicyRequest;
import koast.admin.domain.policy.dto.PolicyResponse;
import koast.admin.domain.policy.entity.Policy;
import org.mapstruct.Mapper;

/**
 * Entity <-> Dto 전환 클래스
 */
@Mapper(componentModel = "spring")
public interface PolicyMapper extends BaseMapper<PolicyRequest, Policy, PolicyResponse> {}
