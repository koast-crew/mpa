package koast.admin.domain.policy.mapper;

import koast.admin.domain.policy.dto.PolicyRequest;
import koast.admin.domain.policy.dto.PolicyResponse;
import koast.admin.domain.policy.entity.Policy;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Policy 엔티티를 PolicyResponse DTO로 변환하는 매퍼 인터페이스
 */
@Mapper(componentModel = "spring")
public interface PolicyMapper {

    PolicyMapper INSTANCE = Mappers.getMapper(PolicyMapper.class);

    PolicyResponse toResponse(Policy policy);

    Policy toEntity(PolicyRequest policyRequest);
}
