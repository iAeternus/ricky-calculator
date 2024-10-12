package io.github.ricky.core.relic.query.dto;

import io.github.ricky.core.common.marker.Result;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/10/11
 * @className YieldWeightResult
 * @desc
 */
@Value
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class YieldWeightResult implements Result {

    String belongTo;
    double critChance;
    double critDamage;
    double energyRecharge;
    double elementalMastery;
    double attackPercentage;
    double attack;
    double healthPercentage;
    double health;
    double defensePercentage;
    double defense;

}
