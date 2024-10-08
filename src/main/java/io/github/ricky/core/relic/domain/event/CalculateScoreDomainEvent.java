package io.github.ricky.core.relic.domain.event;

import io.github.ricky.core.common.domain.event.DomainEvent;
import io.github.ricky.core.common.domain.event.DomainEventTypeEnum;
import io.github.ricky.core.relic.domain.Relic;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/10/8
 * @className CalculateScoreDomainEvent
 * @desc
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CalculateScoreDomainEvent extends DomainEvent {

    private Relic relic;

    public CalculateScoreDomainEvent(Relic relic) {
        super(DomainEventTypeEnum.CALCULATE_SCORE);
        this.relic = relic;
    }
}
