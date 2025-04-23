package koast.admin.service;

import koast.admin.domain.policy.dto.PolicyResponse;
import koast.admin.domain.policy.entity.Policy;

public interface PolicyService {
    /**
     * 최근 정책 조회
     * @return
     */
    PolicyResponse getLatest();
}
