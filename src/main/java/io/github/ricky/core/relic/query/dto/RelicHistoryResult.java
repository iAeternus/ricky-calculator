package io.github.ricky.core.relic.query.dto;

import io.github.ricky.core.common.marker.Result;
import io.github.ricky.core.relic.domain.RelicAttr;
import io.github.ricky.core.relic.domain.RelicPositionEnum;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/10/11
 * @className RelicHistoryResult
 * @desc
 */
@Value
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RelicHistoryResult implements Result {

    String belongTo;
    boolean isCritical;
    RelicPositionEnum position;
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
    double score;

}
