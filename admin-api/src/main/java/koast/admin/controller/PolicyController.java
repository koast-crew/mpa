package koast.admin.controller;

import koast.admin.config.CustomPropertiesConfig;
import koast.admin.service.PolicyService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/policy")
public class PolicyController {

    private final CustomPropertiesConfig customPropertiesConfig;
    private final PolicyService policyService;

    /**
     * 운영 정책 취득
     * @return
     */
    @GetMapping
    public void get() {
        log.info("======================================================== {}", customPropertiesConfig.getMaskingFileDir());
        log.info("policy = {}", policyService.getLatest());
    }
}
