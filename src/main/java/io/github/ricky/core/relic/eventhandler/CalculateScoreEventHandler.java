package io.github.ricky.core.relic.eventhandler;

import io.github.ricky.core.common.domain.event.DomainEvent;
import io.github.ricky.core.common.domain.event.DomainEventHandler;
import io.github.ricky.core.common.utils.TaskRunner;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/10/14
 * @className CalculateScoreEventHandler
 * @desc
 */
public class CalculateScoreEventHandler implements DomainEventHandler {
    @Override
    public boolean canHandle(DomainEvent domainEvent) {
        return false;
    }

    @Override
    public void handle(DomainEvent domainEvent, TaskRunner taskRunner) {

    }
}
