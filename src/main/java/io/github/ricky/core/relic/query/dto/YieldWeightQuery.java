package io.github.ricky.core.relic.query.dto;

import io.github.ricky.core.common.marker.Query;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/10/11
 * @className YieldWeightQuery
 * @desc
 */
@Value
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class YieldWeightQuery implements Query {

    @Size(max = 10)
    String belongTo;

}
