package io.github.ricky.core.relic.domain;

import io.github.ricky.core.common.marker.ValueObject;
import io.github.ricky.core.common.validation.number.DoubleRange;
import lombok.*;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/9/27
 * @className RelicAttr
 * @desc 圣遗物副词条
 */
@Value
@Builder
@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RelicAttr implements ValueObject {

    /**
     * 暴击率
     */
    @DoubleRange(max = 23.3)
    double critChance;

    /**
     * 暴击伤害
     */
    @DoubleRange(max = 46.6)
    double critDamage;

    /**
     * 充能
     */
    @DoubleRange(max = 38.9)
    double energyRecharge;

    /**
     * 精通
     */
    @DoubleRange(max = 140)
    double elementalMastery;

    /**
     * 大攻击
     */
    @DoubleRange(max = 35)
    double attackPercentage;

    /**
     * 小攻击
     */
    @DoubleRange(max = 117)
    double attack;

    /**
     * 大生命
     */
    @DoubleRange(max = 35)
    double healthPercentage;

    /**
     * 小生命
     */
    @DoubleRange(max = 1793)
    double health;

    /**
     * 大防御
     */
    @DoubleRange(max = 43.7)
    double defensePercentage;

    /**
     * 小防御
     */
    @DoubleRange(max = 139)
    double defense;

}
