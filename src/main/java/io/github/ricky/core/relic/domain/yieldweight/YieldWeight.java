package io.github.ricky.core.relic.domain.yieldweight;

import io.github.ricky.core.common.utils.ValueObject;
import io.github.ricky.core.common.validation.attr.AttrRange;
import lombok.*;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/9/27
 * @className YieldWeight
 * @desc 收益权重
 */
@Value
@Builder
@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class YieldWeight implements ValueObject {

    /**
     * 暴击率
     */
    @AttrRange(max = 1)
    double critChance;

    /**
     * 暴击伤害
     */
    @AttrRange(max = 1)
    double critDamage;

    /**
     * 充能
     */
    @AttrRange(max = 1)
    double energyRecharge;

    /**
     * 精通
     */
    @AttrRange(max = 1)
    double elementalMastery;

    /**
     * 大攻击
     */
    @AttrRange(max = 1)
    double attackPercentage;

    /**
     * 小攻击
     */
    @AttrRange(max = 1)
    double attack;

    /**
     * 大生命
     */
    @AttrRange(max = 1)
    double healthPercentage;

    /**
     * 小生命
     */
    @AttrRange(max = 1)
    double health;

    /**
     * 大防御
     */
    @AttrRange(max = 1)
    double defensePercentage;

    /**
     * 小防御
     */
    @AttrRange(max = 1)
    double defense;

}
