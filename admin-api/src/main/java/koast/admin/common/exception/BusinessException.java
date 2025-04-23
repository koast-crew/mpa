package koast.admin.common.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/**
 * 비즈니스 로직 처리 중 발생하는 예외를 표준화합니다.
 * type : 에러 분류
 * errorCode : 에러 코드 (JSON 형태 등)
 * message : 에러 메시지
 */
@Getter
@ToString
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = 5252929825861016024L;

    // 에러 분류
    private final String type;
    // 에러 코드 (예: JSON 문자열 형태)
    private final String errorCode;

    @Builder
    public BusinessException(String type, String errorCode, String message) {
        super(message);
        this.type = type;
        this.errorCode = errorCode;
    }

    /**
     * 비즈니스 예외 타입 분류
     */
    public static class Type {
        public static final String LOGIN = "LOGIN";
        public static final String ACCESS_TOKEN = "ACCESS_TOKEN";
        public static final String REFRESH_TOKEN = "REFRESH_TOKEN";
        public static final String USER = "USER";
    }
}
