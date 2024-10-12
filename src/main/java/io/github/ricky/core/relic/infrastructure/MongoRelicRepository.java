package io.github.ricky.core.relic.infrastructure;

import io.github.ricky.common.repository.mongo.MongoBaseRepository;
import io.github.ricky.core.common.page.MongoPageHelper;
import io.github.ricky.core.common.page.PagedList;
import io.github.ricky.core.common.page.Pagination;
import io.github.ricky.core.relic.domain.Relic;
import io.github.ricky.core.relic.domain.RelicPositionEnum;
import io.github.ricky.core.relic.domain.RelicRepository;
import io.github.ricky.core.relic.infrastructure.converter.RelicConverter;
import io.github.ricky.core.relic.query.dto.RelicHistoryPageQuery;
import io.github.ricky.core.relic.query.dto.RelicHistoryResult;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/10/8
 * @className MongoRelicRepository
 * @desc
 */
@Repository
@RequiredArgsConstructor
public class MongoRelicRepository extends MongoBaseRepository<Relic> implements RelicRepository {

    private final MongoPageHelper mongoPageHelper;
    private final RelicConverter relicConverter;

    @Override
    public void save(Relic relic) {
        super.save(relic);
    }

    @Override
    public Relic byId(String id) {
        return super.byId(id);
    }

    @Override
    public PagedList<RelicHistoryResult> listRelicHistory(RelicHistoryPageQuery pageQuery) {
        Pagination pagination = Pagination.pagination(pageQuery.getPageIndex(), pageQuery.getPageSize());
        Query query = mongoPageHelper.queryBuilder()
                .where("belongTo", pageQuery.getBelongTo())
                .where("position", RelicPositionEnum.of(pageQuery.getPosition()))
                .sortByDesc("score", "createdAt")
                .build();
        return mongoPageHelper.pageQuery(query, Relic.class, relicConverter::convert, pagination);
    }

    @Override
    public List<Relic> listAll() {
        return mongoTemplate.findAll(Relic.class);
    }

}
