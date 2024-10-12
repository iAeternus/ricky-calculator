package io.github.ricky.core.relic.domain;

import io.github.ricky.core.common.page.PagedList;
import io.github.ricky.core.relic.query.dto.RelicHistoryPageQuery;
import io.github.ricky.core.relic.query.dto.RelicHistoryResult;

import java.util.List;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/10/8
 * @className RelicRepository
 * @desc
 */
public interface RelicRepository {

    void save(Relic relic);

    Relic byId(String id);

    PagedList<RelicHistoryResult> listRelicHistory(RelicHistoryPageQuery pageQuery);

    List<Relic> listAll();

    void removeById(String id);
}
