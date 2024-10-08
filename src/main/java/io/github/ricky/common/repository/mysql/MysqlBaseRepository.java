package io.github.ricky.common.repository.mysql;

import io.github.ricky.common.repository.BaseRepository;
import io.github.ricky.core.common.domain.AggregateRoot;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/9/27
 * @className MysqlBaseRepository
 * @desc MySQL抽象数据仓库
 */
public abstract class MysqlBaseRepository<AR extends AggregateRoot> implements BaseRepository<AR> {

    private final Map<String, Class<?>> classMapper = new HashMap<>();

    @Override
    public void save(AR ar) {

    }

    @Override
    public void save(List<AR> ars) {

    }

    @Override
    public void insert(AR ar) {

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
        String className = getClass().getSimpleName();

        if (!classMapper.containsKey(className)) {
            Type genericSuperclass = getClass().getGenericSuperclass();
            Type[] actualTypeArguments = ((ParameterizedType) genericSuperclass).getActualTypeArguments();
            classMapper.put(className, (Class<?>) actualTypeArguments[0]);
        }

        return classMapper.get(className);
    }
}
