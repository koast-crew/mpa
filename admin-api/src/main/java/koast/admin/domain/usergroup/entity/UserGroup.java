package koast.admin.domain.usergroup.entity;

import koast.admin.domain.role.entity.Role;
import koast.admin.domain.user.entity.User;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 사용자 그룹
 * @author jeongdae
 *
 */
@ToString
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserGroup {

	// 고유번호
	private Integer userGroupId;
	// 링크 활용 등을 위한 확장 컬럼
	private String userGroupKey;
	// 그룹명
	private String userGroupName;
	// 조상 고유번호
	private Integer ancestor;
	// 부모 고유번호
	private Integer parent;
	// 깊이
	private Integer depth;
	// 나열 순서
	private Integer viewOrder;
	// 자식 존재 유무
	private Integer children;

	// true : 기본, false : 선택
	private Boolean basic;
	// true : 사용, false : 사용안함
	private Boolean available;

	// 설명
	private String description;

    private List<Role> roles;
    private List<User> users;

	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDate;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDate;
}
