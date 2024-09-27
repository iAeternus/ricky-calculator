package io.github.ricky.core.common.domain;

import io.github.ricky.core.common.utils.Entity;
import lombok.AccessLevel;
import lombok.Getter;
import org.springframework.data.annotation.Version;

import java.time.Instant;

import static io.github.ricky.core.common.utils.CommonUtils.requireNonBlank;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/9/27
 * @className AggregateRoot
 * @desc 聚合根
 */
@Getter
public abstract class AggregateRoot implements Entity {

    /**
     * 标识符，通过Snowflake算法生成
     */
    private String id;

    /**
     * 创建时间
     */
    private Instant createdAt;

    /**
     * 更新时间
     */
    private Instant updatedAt;

    /**
     * 版本号，实现乐观锁
     */
    @Version
    @Getter(AccessLevel.PRIVATE)
    private Long _version;

    protected AggregateRoot() {
    }

    protected AggregateRoot(String id) {
        requireNonBlank(id, "ID must not be blank.");

        this.id = id;
        this.createdAt = Instant.now();
    }

    @Override
    public String getIdentifier() {
        return id;
    }

}
