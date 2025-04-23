package koast.admin.common.paging;

/**
 * 목록 검색용 기본 Base DTO
 * 모든 목록 DTO가 이 클래스를 상속하여 사용한다.
 * (lombok 사용하지 않고 직접 Getter/Setter 작성)
 * (toString()은 작성하지 않음)
 * (기본 생성자만 직접 추가)
 *
 * @author Jeongdae
 */
public class BaseSearch {

    public BaseSearch() {
    }

    private Long totalCount;
    private Long offset;
    private Long limit;
    private String searchWord;
    private String searchOption;
    private String searchValue;
    private String startDate;
    private String endDate;
    private String orderWord;
    private String orderValue;
    private Long pageNo = 1L;
    private Long pageRows = 10L;
    private Long listCounter = 10L;

    /**
     * 검색 파라미터 문자열 생성
     */
    public String getParameters() {
        StringBuilder sb = new StringBuilder();

        // 추가 QueryString
        String additional = getAdditionalQueryString();
        if (additional != null && !additional.isEmpty()) {
            sb.append(additional);
        }

        // 기본 검색 조건
        String sDate = trimDate(this.startDate);
        String eDate = trimDate(this.endDate);

        sb.append("&searchWord=").append(getDefaultValue(this.searchWord));
        sb.append("&searchOption=").append(getDefaultValue(this.searchOption));
        sb.append("&searchValue=").append(getDefaultValue(this.searchValue));
        sb.append("&startDate=").append(getDefaultValue(sDate));
        sb.append("&endDate=").append(getDefaultValue(eDate));
        sb.append("&orderWord=").append(getDefaultValue(this.orderWord));
        sb.append("&orderValue=").append(getDefaultValue(this.orderValue));
        sb.append("&listCounter=").append(this.listCounter);

        return sb.toString();
    }

    /**
     * 추가 QueryString을 하위 클래스에서 override할 수 있도록 제공
     */
    protected String getAdditionalQueryString() {
        return "";
    }

    private String trimDate(String date) {
        if (date != null && date.length() >= 8) {
            return date.substring(0, 8);
        }
        return date;
    }

    private String getDefaultValue(String value) {
        return (value == null || value.trim().isEmpty()) ? "" : value;
    }

    // ---------- Getter / Setter ----------
    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    public Long getOffset() {
        return offset;
    }

    public void setOffset(Long offset) {
        this.offset = offset;
    }

    public Long getLimit() {
        return limit;
    }

    public void setLimit(Long limit) {
        this.limit = limit;
    }

    public String getSearchWord() {
        return searchWord;
    }

    public void setSearchWord(String searchWord) {
        this.searchWord = searchWord;
    }

    public String getSearchOption() {
        return searchOption;
    }

    public void setSearchOption(String searchOption) {
        this.searchOption = searchOption;
    }

    public String getSearchValue() {
        return searchValue;
    }

    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getOrderWord() {
        return orderWord;
    }

    public void setOrderWord(String orderWord) {
        this.orderWord = orderWord;
    }

    public String getOrderValue() {
        return orderValue;
    }

    public void setOrderValue(String orderValue) {
        this.orderValue = orderValue;
    }

    public Long getPageNo() {
        return pageNo;
    }

    public void setPageNo(Long pageNo) {
        this.pageNo = pageNo;
    }

    public Long getPageRows() {
        return pageRows;
    }

    public void setPageRows(Long pageRows) {
        this.pageRows = pageRows;
    }

    public Long getListCounter() {
        return listCounter;
    }

    public void setListCounter(Long listCounter) {
        this.listCounter = listCounter;
    }
}
