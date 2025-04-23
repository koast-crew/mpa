package koast.admin.repository;

import koast.admin.domain.policy.entity.Policy;

public interface PolicyRepository {

    /**
     * 최근 정책 조회
     * @return
     */
    Policy getLatest();
}
