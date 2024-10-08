package io.github.ricky.core.relic.infrastructure.vo;

import io.github.ricky.core.common.validation.attr.AttrRange;
import lombok.Data;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/10/8
 * @className YieldWeightVO
 * @desc
 */
@Data
public class YieldWeightVO {

    private double critChance;
    private double critDamage;
    private double energyRecharge;
    private double elementalMastery;
    private double attackPercentage;
    private double attack;
    private double healthPercentage;
    private double health;
    private double defensePercentage;
    private double defense;

}
