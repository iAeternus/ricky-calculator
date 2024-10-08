package io.github.ricky.core.relic.eventhandler;

import io.github.ricky.core.common.domain.event.DomainEvent;
import io.github.ricky.core.common.domain.event.DomainEventHandler;
import io.github.ricky.core.common.domain.event.DomainEventTypeEnum;
import io.github.ricky.core.common.utils.TaskRunner;
import io.github.ricky.core.relic.domain.RelicRepository;
import io.github.ricky.core.relic.domain.event.CalculateScoreDomainEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/10/8
 * @className CalculateScoreDomainEventHandler
 * @desc
 */
@Component
@RequiredArgsConstructor
public class CalculateScoreDomainEventHandler implements DomainEventHandler {

    private final RelicRepository relicRepository;

    @Override
    public boolean canHandle(DomainEvent domainEvent) {
        return domainEvent.getType() == DomainEventTypeEnum.CALCULATE_SCORE;
    }

    @Override
    public void handle(DomainEvent domainEvent, TaskRunner taskRunner) {
        CalculateScoreDomainEvent calculateScoreDomainEvent = (CalculateScoreDomainEvent) domainEvent;
        relicRepository.save(calculateScoreDomainEvent.getRelic());
    }
}
