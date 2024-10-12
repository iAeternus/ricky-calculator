package io.github.ricky.core.common.page;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/10/11
 * @className PagedList
 * @desc 页面
 */
@Getter
@Builder
public class PagedList<T> {

    /**
     * 页面总数
     */
    private final long totalNumber;

    /**
     * 页号
     */
    private final int pageIndex;

    /**
     * 页面大小
     */
    private final int pageSize;

    /**
     * 数据
     */
    private final List<T> data;

    public static <T> PagedList<T> pagedList(Pagination pagination, long count, List<T> data) {
        return PagedList.<T>builder()
                .totalNumber(count)
                .pageIndex(pagination.getPageIndex())
                .pageSize(pagination.getPageSize())
                .data(data)
                .build();
    }

    public long size() {
        return data.size();
    }

    @Override
    public String toString() {
        return "PagedList{" +
                "totalNumber=" + totalNumber +
                ", pageIndex=" + pageIndex +
                ", pageSize=" + pageSize +
                ", data=" + data +
                '}';
    }
}
