package io.github.ricky.core.common.page;

import io.github.ricky.core.common.exception.RcException;
import lombok.EqualsAndHashCode;
import lombok.Getter;

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
        if (pageIndex < 1) {
            throw RcException.requestValidationException("detail", "pageIndex不能小于1");
        }

        if (pageIndex > 10000) {
            throw RcException.requestValidationException("detail", "pageIndex不能大于10000");
        }

        if (pageSize < 10) {
            throw RcException.requestValidationException("detail", "pageSize不能小于10");
        }

        if (pageSize > 500) {
            throw RcException.requestValidationException("detail", "pageSize不能大于500");
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
