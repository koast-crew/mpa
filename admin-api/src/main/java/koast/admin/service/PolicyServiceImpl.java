package koast.admin.service;

import koast.admin.domain.policy.dto.PolicyResponse;
import koast.admin.domain.policy.mapper.PolicyMapper;
import koast.admin.repository.PolicyRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 운영 정책
 */
@Slf4j
@AllArgsConstructor
@Service
public class PolicyServiceImpl implements PolicyService {

    private final PolicyRepository policyRepository;
    private final PolicyMapper policyMapper;

    @Transactional(readOnly=true)
    public PolicyResponse getLatest() {
        return policyMapper.toDto(policyRepository.getLatest());
    }
}