package io.github.ricky.core.common.page;

import io.github.ricky.core.common.marker.Query;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.CriteriaDefinition;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/10/11
 * @className PageQuery
 * @desc
 */
@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class PageQuery implements Query {

    public static final int MIN_PAGE_INDEX = 1;
    public static final int MAX_PAGE_INDEX = 10000;
    public static final int MIN_PAGE_SIZE = 10;
    public static final int MAX_PAGE_SIZE = 100;

    /**
     * 页号
     */
    @Min(MIN_PAGE_INDEX)
    private int pageIndex;

    /**
     * 页面大小
     */
    @Min(MIN_PAGE_SIZE)
    @Max(MAX_PAGE_SIZE)
    private int pageSize;

}
