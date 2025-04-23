package koast.admin.common.paging;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Pagination
 *
 * 전체 데이터 수(totalCount)와 현재 페이지(pageNo)를 기반으로
 * 페이징 처리에 필요한 메타데이터를 계산합니다.
 */
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Pagination {

    private static final long DEFAULT_PAGE_SIZE = 10L;
    private static final long DEFAULT_PAGE_LINKS = 10L;

    // 총 데이터 건수
    private long totalCount;
    // 현재 페이지 번호
    private long pageNo;
    // 한 페이지당 데이터 수
    @Builder.Default
    private long pageSize = DEFAULT_PAGE_SIZE;
    @Builder.Default
    private long pageLinks = DEFAULT_PAGE_LINKS;
    private long totalPages;
    private long startPage;
    private long endPage;
    private Long prevPage;
    private Long nextPage;
    private boolean isFirst;
    private boolean isLast;
    private long offset;

    public void init() {
        if (pageNo <= 0) {
            pageNo = 1;
        }
        if (pageSize <= 0) {
            pageSize = DEFAULT_PAGE_SIZE;
        }
        if (pageLinks <= 0) {
            pageLinks = DEFAULT_PAGE_LINKS;
        }

        this.totalPages = (totalCount == 0) ? 0 : (totalCount + pageSize - 1) / pageSize;
        this.offset = (pageNo - 1) * pageSize;

        calculatePageBounds();
        calculateNavigation();
    }

    private void calculatePageBounds() {
        this.startPage = ((pageNo - 1) / pageLinks) * pageLinks + 1;
        this.endPage = Math.min(startPage + pageLinks - 1, totalPages);
    }

    private void calculateNavigation() {
        this.isFirst = (pageNo == 1);
        this.isLast = (pageNo == totalPages);
        this.prevPage = (startPage > 1) ? (startPage - 1) : null;
        this.nextPage = (endPage < totalPages) ? (endPage + 1) : null;
    }
}
