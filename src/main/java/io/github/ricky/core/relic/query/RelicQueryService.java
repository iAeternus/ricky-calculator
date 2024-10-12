package io.github.ricky.core.relic.query;

import io.github.ricky.core.common.page.PagedList;
import io.github.ricky.core.relic.domain.RelicRepository;
import io.github.ricky.core.relic.domain.yieldweight.YieldWeight;
import io.github.ricky.core.relic.infrastructure.MysqlYieldWeightDao;
import io.github.ricky.core.relic.infrastructure.converter.YieldWeightConverter;
import io.github.ricky.core.relic.query.dto.RelicHistoryPageQuery;
import io.github.ricky.core.relic.query.dto.RelicHistoryResult;
import io.github.ricky.core.relic.query.dto.YieldWeightQuery;
import io.github.ricky.core.relic.query.dto.YieldWeightResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/10/11
 * @className RelicQueryService
 * @desc
 */
@Component
@RequiredArgsConstructor
public class RelicQueryService {

    private final MysqlYieldWeightDao mysqlYieldWeightDao;
    private final YieldWeightConverter yieldWeightConverter;
    private final RelicRepository relicRepository;

    public YieldWeightResult fetchYieldWeight(YieldWeightQuery query) {
        YieldWeight yieldWeight = mysqlYieldWeightDao.byBelongs(query.getBelongTo());
        return yieldWeightConverter.convert(yieldWeight, query.getBelongTo());
    }

    public PagedList<RelicHistoryResult> listRelicHistory(RelicHistoryPageQuery pageQuery) {
        return relicRepository.listRelicHistory(pageQuery);
    }
}
