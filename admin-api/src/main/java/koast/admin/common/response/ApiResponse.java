package koast.admin.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import koast.admin.common.exception.BusinessException;
import koast.admin.common.paging.Pagination;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {

    private T payload;
    private Pagination pagination;
    private BusinessException exception;

    public ApiResponse() {
    }

    @Builder
    public ApiResponse(T payload, Pagination pagination, BusinessException exception) {
        this.payload = payload;
        this.pagination = pagination;
        this.exception = exception;
    }
}