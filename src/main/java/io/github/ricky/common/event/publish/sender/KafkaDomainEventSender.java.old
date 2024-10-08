package io.github.ricky.common.event.publish.sender;

import io.github.ricky.core.common.domain.event.DomainEvent;
import io.github.ricky.core.common.domain.event.DomainEventDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/10/8
 * @className KafkaDomainEventSender
 * @desc Kafka事件发送器
 */
@Slf4j
@Component
@Profile("!ci")
@RequiredArgsConstructor
public class KafkaDomainEventSender implements DomainEventSender {

    private final DomainEventDao domainEventDao;
    private final KafkaTemplate<Object, DomainEvent> kafkaTemplate;

    public static final String TOPIC = "rc_backend";

    @Override
    public void send(DomainEvent event) {
        try {
            sendToKafkaTopic(event);
            domainEventDao.successPublish(event);
        } catch (Throwable t) {
            domainEventDao.failPublish(event);
            log.error("Error happened while publish domain event[{}:{}].", event.getType(), event.getId(), t);
        }
    }

    private void sendToKafkaTopic(DomainEvent event) {
        kafkaTemplate.send(TOPIC, event);
    }
}
