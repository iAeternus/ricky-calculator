package io.github.ricky.core.common.exception;

import io.github.ricky.core.common.utils.Response;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/9/25
 * @className QErrorResponse
 * @desc
 */
@Value
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class QErrorResponse implements Response {

    Error error;

}
