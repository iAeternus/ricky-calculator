package io.github.ricky.common.repository.mongo;

import io.github.ricky.core.common.domain.event.DomainEvent;
import io.github.ricky.core.common.domain.event.DomainEventDao;
import io.github.ricky.core.common.domain.event.DomainEventTypeEnum;
import io.github.ricky.core.common.exception.ErrorCodeEnum;
import io.github.ricky.core.common.exception.RcException;
import io.github.ricky.core.common.utils.CommonUtils;
import io.github.ricky.core.common.utils.MapUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

import static io.github.ricky.core.common.domain.event.DomainEventStatusEnum.*;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/9/10
 * @className MongoDomainEventDao
 * @desc 领域事件数据访问对象
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class MongoDomainEventDao implements DomainEventDao {

    private final MongoTemplate mongoTemplate;

    @Override
    public void insert(List<DomainEvent> events) {
        Objects.requireNonNull(events, "Domain events must not be null.");

        mongoTemplate.insertAll(events);
    }

    @Override
    public DomainEvent byId(String id) {
        CommonUtils.requireNonBlank(id, "Domain event ID must not be blank.");

        DomainEvent domanEvent = mongoTemplate.findOne(Query.query(Criteria.where("_id").is(id)), DomainEvent.class);
        if (domanEvent == null) {
            throw new RcException(ErrorCodeEnum.DOMAIN_EVENT_NOT_FOUND, "未找到领域事件。", MapUtils.mapOf("eventId", id));
        }

        return domanEvent;
    }

    @Override
    public List<DomainEvent> byIds(List<String> ids) {
        Objects.requireNonNull(ids, "Domain event IDs must not be blank.");

        return mongoTemplate.find(Query.query(Criteria.where("_id").in(ids))
                        .with(Sort.by(Sort.Direction.ASC, "raisedAt")),
                DomainEvent.class
        );
    }

    @Override
    public <T extends DomainEvent> T latestEventFor(String arId, DomainEventTypeEnum type, Class<T> eventClass) {
        CommonUtils.requireNonBlank(arId, "AR ID must not be blank.");
        Objects.requireNonNull(type, "Domain event type must not be null.");
        Objects.requireNonNull(eventClass, "Domain event class must not be null.");

        Query query = Query.query(Criteria.where("arId").is(arId).and("type").is(type))
                .with(Sort.by(Sort.Direction.DESC, "raisedAt"));
        return mongoTemplate.findOne(query, eventClass);
    }

    @Override
    public void successPublish(DomainEvent event) {
        Objects.requireNonNull(event, "Domain event must not be null.");

        Query query = Query.query(Criteria.where("_id").is(event.getId()));
        Update update = new Update();
        update.set("status", PUBLISH_SUCCEED.name())
                .inc("publishedCount");
        mongoTemplate.updateFirst(query, update, DomainEvent.class);
    }

    @Override
    public void failPublish(DomainEvent event) {
        Objects.requireNonNull(event, "Domain event must not be null.");

        Query query = Query.query(Criteria.where("_id").is(event.getId()));
        Update update = new Update();
        update.set("status", PUBLISH_FAILED.name())
                .inc("publishedCount");
        mongoTemplate.updateFirst(query, update, DomainEvent.class);
    }

    @Override
    public void successConsume(DomainEvent event) {
        Objects.requireNonNull(event, "Domain event must not be null.");

        Query query = Query.query(Criteria.where("_id").is(event.getId()));
        Update update = new Update();
        update.set("status", CONSUME_SUCCEED.name())
                .inc("consumedCount");
        mongoTemplate.updateFirst(query, update, DomainEvent.class);
    }

    @Override
    public void failConsume(DomainEvent event) {
        Objects.requireNonNull(event, "Domain event must not be null.");

        Query query = Query.query(Criteria.where("_id").is(event.getId()));
        Update update = new Update();
        update.set("status", CONSUME_FAILED.name())
                .inc("consumedCount");
        mongoTemplate.updateFirst(query, update, DomainEvent.class);
    }

    @Override
    public List<DomainEvent> tobePublishedEvents(String startId, int limit) {
        CommonUtils.requireNonBlank(startId, "Start ID must not be blank.");

        Query query = Query.query(Criteria.where("status").in(CREATED, PUBLISH_FAILED, CONSUME_FAILED)
                        .and("_id").gt(startId)
                        .and("publishedCount").lt(3)
                        .and("consumedCount").lt(3))
                .with(Sort.by(Sort.Direction.ASC, "raisedAt"))
                .limit(limit);
        return mongoTemplate.find(query, DomainEvent.class);
    }
}
