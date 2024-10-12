package io.github.ricky.core.common.page;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/10/12
 * @className MongoPageHelper
 * @desc
 */
@Component
@RequiredArgsConstructor
public class MongoPageHelper {

    public static final int FIRST_PAGE_NUM = 1;
    public static final String AGGREGATE_ID = "id";

    private final MongoTemplate mongoTemplate;

    /**
     * 分页查询，直接返回集合类型的结果
     */
    public <T> PagedList<T> pageQuery(Query query, Class<T> entityClass, Pagination pagination) {
        return pageQuery(query, entityClass, Function.identity(), pagination, null);
    }

    /**
     * 分页查询，不考虑条件分页，直接使用skip-limit来分页
     */
    public <T, R> PagedList<R> pageQuery(Query query, Class<T> entityClass, Function<T, R> mapper, Pagination pagination) {
        return pageQuery(query, entityClass, mapper, pagination, null);
    }

    /**
     * 分页查询
     *
     * @param query       Mongo Query对象，构造你自己的查询条件
     * @param entityClass Mongo collection定义的entity class，用来确定查询哪个集合
     * @param mapper      映射器，你从db查出来的list的元素类型是entityClass, 如果你想要转换成另一个对象，比如去掉敏感字段等，可以使用mapper来决定如何转换
     * @param pagination  分页对象，包含页面大小和页号
     * @param lastId      条件分页参数, 区别于skip-limit，采用find(_id>lastId).limit分页<br>
     *                    如果不跳页，像朋友圈，微博这样下拉刷新的分页需求，需要传递上一页的最后一条记录的ObjectId。 如果是null，则返回pageNum那一页
     * @param <T>         collection定义的class类型
     * @param <R>         最终返回时，展现给页面时的一条记录的类型
     * @return PagedList，一个封装page信息的对象
     * @see <a href="https://github.com/Ryan-Miao/mongo-page-helper.git">inspiration</a>
     */
    public <T, R> PagedList<R> pageQuery(Query query, Class<T> entityClass, Function<T, R> mapper, Pagination pagination, String lastId) {
        int pageSize = pagination.getPageSize();
        int pageIndex = pagination.getPageIndex();
        long total = mongoTemplate.count(query, entityClass);
        final int pages = (int) Math.ceil(total / (double) pageSize);
        if (pageIndex <= 0 || pageIndex > pages) {
            pageIndex = FIRST_PAGE_NUM;
        }
        final Criteria criteria = new Criteria();
        if (StringUtils.isNotBlank(lastId)) {
            if (pageIndex != FIRST_PAGE_NUM) {
                criteria.and(AGGREGATE_ID).gt(new ObjectId(lastId));
            }
            query.limit(pageSize);
        } else {
            query.skip(pagination.skip()).limit(pagination.limit());
        }

        final List<T> entityList = mongoTemplate
                .find(query.addCriteria(criteria).with(Sort.by(Sort.Direction.ASC, AGGREGATE_ID)), entityClass);

        return PagedList.<R>builder()
                .totalNumber(total)
                .pageIndex(pageIndex)
                .pageSize(pageSize)
                .data(entityList.stream().map(mapper).toList())
                .build();
    }

    public QueryBuilder queryBuilder() {
        return new QueryBuilder(new Query());
    }

    /**
     * 分页查询条件建造者
     */
    public static class QueryBuilder {
        private final Query query;

        public QueryBuilder(Query query) {
            this.query = query;
        }

        public QueryBuilder where(String key, String value) {
            return where(key, value, StringUtils::isNotBlank);
        }

        public QueryBuilder where(String key, Object value) {
            return where(key, value, Objects::nonNull);
        }

        public <T> QueryBuilder where(String key, T value, Predicate<T> nullJudgement) {
            if(nullJudgement.test(value)) {
                query.addCriteria(Criteria.where(key).is(value));
            }
            return this;
        }

        public QueryBuilder sortByAsc(String... properties) {
            return sortBy(Sort.Direction.ASC, properties);
        }

        public QueryBuilder sortByDesc(String... properties) {
            return sortBy(Sort.Direction.DESC, properties);
        }

        public QueryBuilder sortBy(Sort.Direction direction, String... properties) {
            query.with(Sort.by(direction, properties));
            return this;
        }

        public Query build() {
            return this.query;
        }
    }

}
