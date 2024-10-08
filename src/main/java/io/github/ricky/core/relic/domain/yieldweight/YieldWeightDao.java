package io.github.ricky.core.relic.domain.yieldweight;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/10/8
 * @className YieldWeightDao
 * @desc
 */
public interface YieldWeightDao {

    YieldWeight byBelongs(String belongTo);

}
