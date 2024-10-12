package io.github.ricky.core.relic.infrastructure.vo;

import io.github.ricky.core.common.marker.PersistentObject;
import lombok.Data;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/10/8
 * @className YieldWeightPO
 * @desc
 */
@Data
public class YieldWeightPO implements PersistentObject {

    private String belongs;
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
