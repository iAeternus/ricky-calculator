package io.github.ricky.core.relic.infrastructure.mapper;

import io.github.ricky.core.relic.infrastructure.vo.YieldWeightVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/10/8
 * @className YieldWeightMapper
 * @desc
 */
@Mapper
public interface YieldWeightMapper {

    @Select("select * from `faster-relic-rating-calculator`.relic_yield_weights where belongs = #{belongTo}")
    YieldWeightVO byBelongs(String belongTo);

}
