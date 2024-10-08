package io.github.ricky.common.repository.mongo;

import io.github.ricky.common.repository.BaseRepository;
import io.github.ricky.core.common.domain.AggregateRoot;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static java.util.Objects.requireNonNull;
import static org.apache.commons.collections4.CollectionUtils.isEmpty;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/9/27
 * @className MongoBaseRepository
 * @desc MongoDB抽象数据仓库
 */
public abstract class MongoBaseRepository<AR extends AggregateRoot> implements BaseRepository<AR> {

    /**
     * 数据仓库管理的聚合根
     */
    private final Map<String, Class<?>> classMapper = new HashMap<>();

    @Autowired
    protected MongoTemplate mongoTemplate;

    @Override
    @Transactional
    public void save(AR ar) {
        requireNonNull(ar, "AggregateRoot must not be null.");
        mongoTemplate.save(ar);
    }

    @Override
    @Transactional
    public void save(List<AR> ars) {
        if(isEmpty(ars)) {
            return;
        }

        ars.forEach(ar -> mongoTemplate.save(ar));
    }

    @Override
    @Transactional
    public void insert(AR ar) {
        requireNonNull(ar, "AggregateRoot must not be null.");
        mongoTemplate.insert(ar);
    }

    @Override
    public void insert(List<AR> ars) {

    }

    @Override
    public void delete(AR ar) {

    }

    @Override
    public void delete(List<AR> ars) {

    }

    @Override
    public AR byId(String id) {
        return null;
    }

    @Override
    public Optional<AR> byIdOptional(String id) {
        return Optional.empty();
    }

    @Override
    public List<AR> byIds(Set<String> ids) {
        return null;
    }

    @Override
    public List<AR> byIdsAll(Set<String> ids) {
        return null;
    }

    @Override
    public boolean exists(String arId) {
        return false;
    }

    @Override
    public Class<?> getType() {
        return null;
    }
}
