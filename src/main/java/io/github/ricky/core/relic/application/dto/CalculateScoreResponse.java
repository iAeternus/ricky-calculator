package io.github.ricky.core.relic.application.dto;

import io.github.ricky.core.common.utils.Response;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/9/27
 * @className CalculateScoreResponse
 * @desc
 */
@Value
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CalculateScoreResponse implements Response {

    String id;
    double score;

}
