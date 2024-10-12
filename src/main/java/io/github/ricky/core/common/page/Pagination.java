package io.github.ricky.core.common.page;

import io.github.ricky.core.common.exception.RcException;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import static io.github.ricky.core.common.page.PageQuery.*;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/10/11
 * @className Pagination
 * @desc 分页
 */
@Getter
@EqualsAndHashCode
public class Pagination {

    /**
     * 页号
     */
    private final int pageIndex;

    /**
     * 页面大小
     */
    private final int pageSize;

    private Pagination(int pageIndex, int pageSize) {
        if (pageIndex < MIN_PAGE_INDEX) {
            throw RcException.requestValidationException("detail", "pageIndex不能小于" + MIN_PAGE_INDEX);
        }

        if (pageIndex > MAX_PAGE_INDEX) {
            throw RcException.requestValidationException("detail", "pageIndex不能大于" + MAX_PAGE_INDEX);
        }

        if (pageSize < MIN_PAGE_SIZE) {
            throw RcException.requestValidationException("detail", "pageSize不能小于" + MIN_PAGE_SIZE);
        }

        if (pageSize > MAX_PAGE_SIZE) {
            throw RcException.requestValidationException("detail", "pageSize不能大于" + MAX_PAGE_SIZE);
        }

        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
    }

    public static Pagination pagination(int pageIndex, int pageSize) {
        return new Pagination(pageIndex, pageSize);
    }

    public int skip() {
        return (this.pageIndex - 1) * this.pageSize;
    }

    public int limit() {
        return this.pageSize;
    }
}
