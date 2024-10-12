package io.github.ricky.core.common.domain.event;

import io.github.ricky.core.common.domain.AggregateRoot;
import io.github.ricky.core.common.constants.RcConstants;
import io.github.ricky.core.common.utils.SnowflakeIdGenerator;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.Objects;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/9/5
 * @className DomainEvent
 * @desc 领域事件<br>
 * 这里ar代指AggregateRoot
 * DomainEvent既要保证能支持MongoDB的序列化/反序列化，有要能够通过Jackson序列化/反序列化（因为要发送到Redis）<br>
 */
@Getter
@DomainEventJsonConfig
@Document(RcConstants.EVENT_COLLECTION)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class DomainEvent {

    /**
     * 事件ID，不能为空
     */
    private String id;

    /**
     * 事件对应的聚合根ID，不能为空
     */
    private String arId;

    /**
     * 事件类型
     */
    private DomainEventTypeEnum type;

    /**
     * 事件状态
     */
    private DomainEventStatusEnum status;

    /**
     * 已经发布的次数，无论成功与否
     */
    private int publishedCount;

    /**
     * 已经被消费的次数，无论成功与否
     */
    private int consumedCount;

    /**
     * 引发该事件的memberId
     */
    private String raisedBy;

    /**
     * 事件产生时间
     */
    private Instant raisedAt;

    protected DomainEvent(DomainEventTypeEnum type) {
        Objects.requireNonNull(type, "Domain event type must not be null.");

        this.id = newEventId();
    }

    /**
     * 生成事件id
     *
     * @return 事件id
     */
    public String newEventId() {
        return "EVT" + SnowflakeIdGenerator.newSnowflakeId();
    }

    /**
     * 设置聚合根信息
     *
     * @param ar 聚合根
     */
    public void setArInfo(AggregateRoot ar) {
        this.arId = ar.getId();
    }

}
