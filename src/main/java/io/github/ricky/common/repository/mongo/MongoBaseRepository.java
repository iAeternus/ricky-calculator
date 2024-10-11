package io.github.ricky.common.repository.mongo;

import com.google.common.collect.ImmutableSet;
import io.github.ricky.common.event.publish.interception.ThreadLocalDomainEventIdHolder;
import io.github.ricky.common.repository.BaseRepository;
import io.github.ricky.core.common.domain.AggregateRoot;
import io.github.ricky.core.common.domain.event.DomainEvent;
import io.github.ricky.core.common.domain.event.DomainEventDao;
import io.github.ricky.core.common.exception.ErrorCodeEnum;
import io.github.ricky.core.common.exception.RcException;
import io.github.ricky.core.common.utils.CommonUtils;
import io.github.ricky.core.common.utils.MapUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;

import static java.util.Objects.requireNonNull;
import static org.apache.commons.collections4.CollectionUtils.isEmpty;
import static org.hibernate.validator.internal.util.CollectionHelper.toImmutableSet;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/9/27
 * @className MongoBaseRepository
 * @desc MongoDB抽象数据仓库
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public abstract class MongoBaseRepository<AR extends AggregateRoot> implements BaseRepository<AR> {

    /**
     * 数据仓库管理的聚合根
     */
    private final Map<String, Class<?>> classMapper = new HashMap<>();

    @Autowired
    protected MongoTemplate mongoTemplate;

    @Autowired
    protected DomainEventDao domainEventDao;

    /**
     * 保存聚合根<br>
     * 文档已存在则更新，如果不存在则插入<br>
     * 如果聚合根内有领域事件，会持久化并清空，给聚合根瘦身<br>
     *
     * @param ar 聚合根
     */
    @Override
    @Transactional(value = "mongoTransactionManager")
    public void save(AR ar) {
        Objects.requireNonNull(ar, "AggregateRoot must not be null.");

        if (CollectionUtils.isNotEmpty(ar.getEvents())) {
            saveEvents(ar.getEvents());
            ar.clearEvents();
        }

        mongoTemplate.save(ar);
    }

    /**
     * 保存聚合根集合<br>
     * 文档已存在则更新，如果不存在则插入<br>
     * 如果集合为空则什么都不做<br>
     * 同样会持久化领域事件并清空每个聚合根的事件列表<br>
     *
     * @param ars 聚合根集合
     */
    @Override
    @Transactional(value = "mongoTransactionManager")
    public void save(List<AR> ars) {
        if (CollectionUtils.isEmpty(ars)) {
            return;
        }

        List<DomainEvent> events = new ArrayList<>();
        ars.forEach(ar -> {
            if (CollectionUtils.isNotEmpty(ar.getEvents())) {
                events.addAll(ar.getEvents());
                ar.clearEvents();
            }
            mongoTemplate.save(ar);
        });

        saveEvents(events);
    }

    /**
     * 保存聚合根<br>
     * 如果聚合根ID已存在则抛出异常<br>
     * 同样会持久化领域事件并清空每个聚合根的事件列表<br>
     *
     * @param ar 聚合根
     */
    @Override
    @Transactional(value = "mongoTransactionManager")
    public void insert(AR ar) {
        Objects.requireNonNull(ar, "AggregateRoot must not be null.");

        if (CollectionUtils.isNotEmpty(ar.getEvents())) {
            saveEvents(ar.getEvents());
            ar.clearEvents();
        }

        mongoTemplate.insert(ar);
    }

    /**
     * 保存聚合根集合<br>
     * 如果聚合根ID已存在则抛出异常<br>
     * 如果集合为空则什么都不做<br>
     * 同样会持久化领域事件并清空每个聚合根的事件列表<br>
     *
     * @param ars 聚合根集合
     */
    @Override
    @Transactional(value = "mongoTransactionManager")
    public void insert(List<AR> ars) {
        if (CollectionUtils.isEmpty(ars)) {
            return;
        }

        List<DomainEvent> events = new ArrayList<>();
        ars.forEach(ar -> {
            if (CollectionUtils.isNotEmpty(ar.getEvents())) {
                events.addAll(ar.getEvents());
                ar.clearEvents();
            }
        });

        mongoTemplate.insertAll(ars);
        saveEvents(events);
    }

    /**
     * 删除聚合根<br>
     * 删除前会持久化聚合根携带的所有领域事件<br>
     *
     * @param ar 聚合根
     */
    @Override
    @Transactional(value = "mongoTransactionManager")
    public void delete(AR ar) {
        Objects.requireNonNull(ar, "AggregateRoot must not be null.");

        if (CollectionUtils.isNotEmpty(ar.getEvents())) {
            saveEvents(ar.getEvents());
            ar.clearEvents();
        }

        mongoTemplate.remove(ar);
    }

    /**
     * 批量删除聚合根<br>
     * 如果聚合根集合为空则什么都不做<br>
     * 删除前会持久化聚合根携带的所有领域事件<br>
     *
     * @param ars 聚合根集合
     */
    @Override
    @Transactional(value = "mongoTransactionManager")
    public void delete(List<AR> ars) {
        if (CollectionUtils.isEmpty(ars)) {
            return;
        }

        List<DomainEvent> events = new ArrayList<>();
        Set<String> ids = new HashSet<>();
        ars.forEach(ar -> {
            if (CollectionUtils.isNotEmpty(ar.getEvents())) {
                events.addAll(ar.getEvents());
                ar.clearEvents();
            }
            ids.add(ar.getId());
        });

        saveEvents(events);
        mongoTemplate.remove(Query.query(Criteria.where("_id").in(ids)), getType());
    }

    /**
     * 根据ID获取聚合根<br>
     * 没找到资源则抛出异常<br>
     *
     * @param id 聚合根ID
     * @return 聚合根
     */
    @Override
    public AR byId(String id) {
        CommonUtils.requireNonBlank(id, "AggregateRoot ID must not be blank.");

        Object ar = mongoTemplate.findById(id, getType());
        if (ar == null) {
            throw new RcException(ErrorCodeEnum.AR_NOT_FOUND, "未找到资源。", MapUtils.mapOf("type", getType().getSimpleName(), "id", id));
        }

        return (AR) ar;
    }

    /**
     * 根据ID获取聚合根<br>
     * 没找到资源则返回Optional.empty()<br>
     *
     * @param id 聚合根ID
     * @return 聚合根
     */
    @Override
    public Optional<AR> byIdOptional(String id) {
        CommonUtils.requireNonBlank(id, "AggregateRoot ID must not be blank.");

        Object ar = mongoTemplate.findById(id, getType());
        return ar == null ? Optional.empty() : Optional.of((AR) ar);
    }

    /**
     * 根据ID集合批量获取聚合根<br>
     * 若ID集合为空则返回空列表<br>
     * 没找到资源则抛出异常<br>
     *
     * @param ids 聚合根ID集合
     * @return 聚合根集合
     */
    @Override
    public List<AR> byIds(Set<String> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return Collections.emptyList();
        }

        List<AR> ars = mongoTemplate.find(Query.query(Criteria.where("_id").in(ids)), getType());
        return List.copyOf(ars);
    }

    /**
     * 根据ID集合批量获取聚合根<br>
     * 若ID集合为空则返回空列表<br>
     * 没找到资源或者没有找全资源均抛出异常<br>
     *
     * @param ids 聚合根ID集合
     * @return 聚合根集合
     */
    @Override
    public List<AR> byIdsAll(Set<String> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return Collections.emptyList();
        }

        List<AR> ars = byIds(ids);
        if (ars.size() != ids.size()) {
            Set<String> fetchedIds = ars.stream()
                    .map(AggregateRoot::getId)
                    .collect(ImmutableSet.toImmutableSet());
            Set<String> originalIds = new HashSet<>(ids);
            originalIds.removeAll(fetchedIds);
            throw new RcException(ErrorCodeEnum.AR_NOT_FOUND_ALL, "未找到所有资源。",
                    MapUtils.mapOf("type", getType().getSimpleName(), "missingIds", originalIds));
        }
        return List.copyOf(ars);
    }

    /**
     * 判断聚合根是否存在
     *
     * @param arId 聚合根ID
     * @return true=存在 false=不存在
     */
    @Override
    public boolean exists(String arId) {
        CommonUtils.requireNonBlank(arId, "AggregateRoot ID must not be blank.");

        Query query = Query.query(Criteria.where("_id").is(arId));
        return mongoTemplate.exists(query, getType());
    }

    /**
     * 获取当前数据仓库管理的聚合根的Class
     * 不存在则将当前数据仓库管理的聚合根Class加入到容器中
     *
     * @return 当前数据仓库管理的聚合根的Class
     */
    @Override
    public Class getType() {
        String className = getClass().getSimpleName();

        if (!classMapper.containsKey(className)) {
            Type genericSuperclass = getClass().getGenericSuperclass();
            Type[] actualTypeArguments = ((ParameterizedType) genericSuperclass).getActualTypeArguments();
            classMapper.put(className, (Class) actualTypeArguments[0]);
        }

        return classMapper.get(className);
    }

    /**
     * 持久化领域事件，如果入参为空则什么都不做
     *
     * @param events 领域事件集合
     */
    private void saveEvents(List<DomainEvent> events) {
        if (CollectionUtils.isNotEmpty(events)) {
            domainEventDao.insert(events);
            ThreadLocalDomainEventIdHolder.addEvents(events);
        }
    }
}
