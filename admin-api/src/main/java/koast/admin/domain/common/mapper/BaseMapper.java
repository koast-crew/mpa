package koast.admin.domain.common.mapper;

/**
 * 입력 DTO → ENTITY → 출력 DTO 매핑을 위한 공통 Mapper 명세
 *
 * @param <REQ> 입력 DTO (예: PolicyRequest)
 * @param <ENTITY> Entity 클래스 (예: Policy)
 * @param <RES> 출력 DTO (예: PolicyResponse)
 */
public interface BaseMapper<REQ, ENTITY, RES> {

    /**
     * 입력 DTO를 Entity로 변환
     * @param requestDto 입력 DTO
     * @return 매핑된 Entity
     */
    ENTITY toEntity(REQ requestDto);

    /**
     * Entity를 출력 DTO로 변환
     * @param entity Entity 객체
     * @return 매핑된 출력 DTO
     */
    RES toDto(ENTITY entity);
}
