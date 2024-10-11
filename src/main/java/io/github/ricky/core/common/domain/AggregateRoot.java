package io.github.ricky.core.common.domain;

import io.github.ricky.core.common.domain.event.DomainEvent;
import io.github.ricky.core.common.utils.CommonUtils;
import io.github.ricky.core.common.utils.Entity;
import lombok.AccessLevel;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;

import java.time.Instant;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import static io.github.ricky.core.common.utils.CommonUtils.requireNonBlank;
import static java.time.Instant.now;

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
    @Id
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
     * 领域事件列表，用于临时存放完成某个业务流程中所发出的事件，会被BaseRepository保存到事件表中
     */
    private List<DomainEvent> events;

    /**
     * 版本号，实现乐观锁
     */
    @Version
    @Getter(AccessLevel.PRIVATE)
    private Long _version;

    protected AggregateRoot() {
        this.clearEvents();
    }

    protected AggregateRoot(String id) {
        CommonUtils.requireNonBlank(id, "ID must not be blank.");

        this.id = id;
        this.createdAt = Instant.now();
    }

    /**
     * 触发事件
     *
     * @param event 事件
     */
    protected void raiseEvent(DomainEvent event) {
        event.setArInfo(this);
        allEvents().add(event);
    }

    /**
     * 清除领域事件
     * 为了方便传输和持久化，需要清除领域事件给聚合根瘦身
     */
    public void clearEvents() {
        this.events = null;
    }

    /**
     * 获取所有领域事件
     *
     * @return 领域事件集合，没有就返回空集合
     */
    private List<DomainEvent> allEvents() {
        if (events == null) {
            this.events = new ArrayList<>();
        }

        return events;
    }

    @Override
    public String getIdentifier() {
        return id;
    }
}
