package koast.admin.service.impl.policy;

import koast.admin.domain.policy.entity.Policy;
import koast.admin.repository.PolicyRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 운영 정책
 * @author jeongdae
 *
 */
@AllArgsConstructor
@Service
public class PolicyServiceImpl implements PolicyService {

    private final PolicyRepository policyRepository;

    /**
     * 운영 정책 정보
     * @return
     */
    @Transactional(readOnly=true)
    public Policy getLatest() {
        return policyRepository.getLatest();
    }
}
