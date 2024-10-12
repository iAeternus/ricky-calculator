package io.github.ricky.core.relic.query.dto;

import io.github.ricky.core.common.page.PageQuery;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/10/11
 * @className RelicHistoryPageQuery
 * @desc
 */
@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RelicHistoryPageQuery extends PageQuery {

    @Size(max = 10)
    private String belongTo;

    @Min(0)
    @Max(4)
    private Integer position;

}
