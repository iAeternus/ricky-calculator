package io.github.ricky.common.event.publish.sender;

import io.github.ricky.core.common.domain.event.DomainEvent;
import io.github.ricky.core.common.domain.event.DomainEventDao;
import io.github.ricky.core.common.properties.RedisProperties;
import io.github.ricky.core.common.utils.RcObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.connection.stream.ObjectRecord;
import org.springframework.data.redis.connection.stream.StreamRecords;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/9/10
 * @className RedisDomainEventSender
 * @desc 使用Redis流发送领域事件
 */
@Slf4j
@Component
@Profile("ci")
@RequiredArgsConstructor
public class RedisDomainEventSender implements DomainEventSender {

    private final RcObjectMapper mryObjectMapper;
    private final RedisProperties redisProperties;
    private final StringRedisTemplate stringRedisTemplate;
    private final DomainEventDao domainEventDao;

    @Override
    public void send(DomainEvent event) {
        try {
            String eventString = mryObjectMapper.writeValueAsString(event);
            ObjectRecord<String, String> record = StreamRecords.newRecord()
                    .ofObject(eventString)
                    .withStreamKey(redisProperties.getDomainEventStream());
            stringRedisTemplate.opsForStream().add(record);
            domainEventDao.successPublish(event);
        } catch (Throwable t) {
            log.error("Error happened while publish domain event[{}:{}] to redis.", event.getType(), event.getId(), t);
            domainEventDao.failPublish(event);
        }
    }
}
