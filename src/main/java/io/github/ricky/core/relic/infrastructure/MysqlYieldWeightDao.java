package io.github.ricky.core.relic.infrastructure;

import io.github.ricky.core.common.exception.ErrorCodeEnum;
import io.github.ricky.core.common.exception.RcException;
import io.github.ricky.core.common.utils.MapUtils;
import io.github.ricky.core.relic.domain.yieldweight.YieldWeight;
import io.github.ricky.core.relic.domain.yieldweight.YieldWeightDao;
import io.github.ricky.core.relic.infrastructure.converter.YieldWeightConverter;
import io.github.ricky.core.relic.infrastructure.mapper.YieldWeightMapper;
import io.github.ricky.core.relic.infrastructure.vo.YieldWeightPO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/10/8
 * @className MysqlYieldWeightDao
 * @desc
 */
@Component
@RequiredArgsConstructor
public class MysqlYieldWeightDao implements YieldWeightDao {

    private final YieldWeightMapper yieldWeightMapper;
    private final YieldWeightConverter yieldWeightConverter;

    @Override
    public YieldWeight byBelongs(String belongTo) {
        YieldWeightPO yieldWeightPO = yieldWeightMapper.byBelongs(belongTo);
        if(yieldWeightPO == null) {
            throw new RcException(ErrorCodeEnum.YIELD_WEIGHT_NOT_FOUND, "未找到资源。",
                    MapUtils.mapOf("type", YieldWeightPO.class.getSimpleName(), "belongTo", belongTo));
        }
        return yieldWeightConverter.convert(yieldWeightPO);
    }
}
