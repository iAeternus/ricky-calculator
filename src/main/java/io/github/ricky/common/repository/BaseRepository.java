package io.github.ricky.common.repository;

import io.github.ricky.core.common.domain.AggregateRoot;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/9/27
 * @className BaseRepository
 * @desc 数据仓库接口
 * 关于BaseRepository及其实现类的约定：<br>
 * 1. 后缀为byXXX的方法，在没找到资源时将抛出异常<br>
 * 2. 后缀为byXxxOptional的方法，在没找到资源时返回empty()<br>
 */
public interface BaseRepository<AR extends AggregateRoot> {

    /**
     * 保存聚合根<br>
     * 若已存在则更新，否则插入<br>
     * 若聚合根内有领域事件，会持久化并清空，给聚合根瘦身<br>
     *
     * @param ar 聚合根
     */
    void save(AR ar);

    /**
     * 保存聚合根集合<br>
     * 若已存在则更新，否则插入<br>
     * 若集合为空则什么都不做<br>
     * 同样会持久化领域事件并清空每个聚合根的事件列表<br>
     *
     * @param ars 聚合根集合
     */
    void save(List<AR> ars);

    /**
     * 保存聚合根<br>
     * 如果聚合根ID已存在则抛出异常<br>
     * 同样会持久化领域事件并清空每个聚合根的事件列表<br>
     *
     * @param ar 聚合根
     */
    void insert(AR ar);

    /**
     * 保存聚合根集合<br>
     * 如果聚合根ID已存在则抛出异常<br>
     * 如果集合为空则什么都不做<br>
     * 同样会持久化领域事件并清空每个聚合根的事件列表<br>
     *
     * @param ars 聚合根集合
     */
    void insert(List<AR> ars);

    /**
     * 删除聚合根<br>
     * 删除前会持久化聚合根携带的所有领域事件<br>
     *
     * @param ar 聚合根
     */
    void delete(AR ar);

    /**
     * 批量删除聚合根<br>
     * 如果聚合根集合为空则什么都不做<br>
     * 删除前会持久化聚合根携带的所有领域事件<br>
     *
     * @param ars 聚合根集合
     */
    void delete(List<AR> ars);

    /**
     * 根据ID获取聚合根<br>
     * 没找到资源则抛出异常<br>
     *
     * @param id 聚合根ID
     * @return 聚合根
     */
    AR byId(String id);

    /**
     * 根据ID获取聚合根<br>
     * 没找到资源则返回Optional.empty()<br>
     *
     * @param id 聚合根ID
     * @return 聚合根
     */
    Optional<AR> byIdOptional(String id);

    /**
     * 根据ID集合批量获取聚合根<br>
     * 若ID集合为空则返回空列表<br>
     * 没找到资源则抛出异常<br>
     *
     * @param ids 聚合根ID集合
     * @return 聚合根集合
     */
    List<AR> byIds(Set<String> ids);

    /**
     * 根据ID集合批量获取聚合根<br>
     * 若ID集合为空则返回空列表<br>
     * 没找到资源或者没有找全资源均抛出异常<br>
     *
     * @param ids 聚合根ID集合
     * @return 聚合根集合
     */
    List<AR> byIdsAll(Set<String> ids);

    /**
     * 判断聚合根是否存在
     *
     * @param arId 聚合根ID
     * @return true=存在 false=不存在
     */
    boolean exists(String arId);

    /**
     * 获取当前数据仓库管理的聚合根的Class<br>
     * 不存在则将当前数据仓库管理的聚合根Class加入到容器中<br>
     *
     * @return 当前数据仓库管理的聚合根的Class
     */
    Class<?> getType();

}
