package koast.admin.service.impl.policy;

import koast.admin.domain.policy.entity.Policy;

public interface PolicyService {
    /**
     * 최근 정책 조회
     * @return
     */
    Policy getLatest();
}
