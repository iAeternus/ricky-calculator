package io.github.ricky.core.polynomial.application.dto;

import io.github.ricky.core.common.marker.Response;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/10/13
 * @className CalculateResultResponse
 * @desc
 */
@Value
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CalculateResultResponse implements Response {

    String id;
    double result;

}
