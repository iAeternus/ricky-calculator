package io.github.ricky.core.common.domain;

import io.github.ricky.core.common.utils.ValueObject;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.time.Instant;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/9/27
 * @className OpsLog
 * @desc 操作日志
 */
@Value
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class OpsLog implements ValueObject {

    /**
     * 操作时间
     */
    Instant optAt;

    /**
     * 操作人
     */
    String optBy;

    /**
     * 操作人名字
     */
    String obn;

    /**
     * 记录
     */
    String note;

    public Instant getOperatedAt() {
        return optAt;
    }

    public String getOperatedBy() {
        return optBy;
    }

    public String getOperatedByName() {
        return obn;
    }

}
