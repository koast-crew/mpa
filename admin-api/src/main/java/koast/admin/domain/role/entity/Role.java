package koast.admin.domain.role.entity;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@ToString(callSuper = true)
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    // 고유번호
    private Integer roleId;
    // Role 명
    private String roleName;
    // Role KEY
    private String roleKey;
    // Role 타켓. 0 : 사용자 사이트, 1 : 관리자  사이트, 2 : 서버
    private String roleTarget;
    // 업무 유형. 0 : 사용자, 1 : 서버, 3 : api
    private String roleType;
    // true : 기본, false : 선택
    private Boolean basic;
    // true : 사용, false : 사용안함
    private Boolean available;
    // 설명
    private String description;
    
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdDate;
}
