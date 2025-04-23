package koast.admin.domain.policy.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PolicyResponse {

    private Integer policyId;

    private Integer userIdMinLength;
    private Integer userFailLoginCount;
    private Integer userFailLockRelease;
    private Integer userLastLoginLock;
    private Boolean userDuplicationLogin;
    private String userLoginType;
    private Boolean userUpdateConfirmation;
    private Boolean userDeleteConfirmation;
    private String userDeleteType;
    private String loginType;

    private Integer passwordChangeTerm;
    private Integer passwordMinLength;
    private Integer passwordMaxLength;
    private Integer passwordEngUpperCount;
    private Integer passwordEngLowerCount;
    private Integer passwordNumberCount;
    private Integer passwordSpecialCharCount;
    private Integer passwordContinuousCharCount;
    private String passwordCreateType;
    private String passwordCreateChar;
    private String passwordExceptionChar;

    private Boolean securitySessionTimeoutEnabled;
    private String securitySessionTimeout;

    private Integer contentCacheVersion;
    private String contentMenuGroupRoot;
    private String contentUserGroupRoot;

    private String basemapType;

    // 등록일
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdDate;
}
