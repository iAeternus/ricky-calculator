package io.github.ricky.core.relic.infrastructure.converter;

import io.github.ricky.core.relic.domain.yieldweight.YieldWeight;
import io.github.ricky.core.relic.infrastructure.vo.YieldWeightPO;
import io.github.ricky.core.relic.query.dto.YieldWeightResult;
import org.springframework.stereotype.Component;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/10/8
 * @className YieldWeightConverter
 * @desc
 */
@Component
public class YieldWeightConverter {

    public YieldWeight convert(YieldWeightPO yieldWeightPO) {
        return YieldWeight.builder()
                .critChance(yieldWeightPO.getCritChance())
                .critDamage(yieldWeightPO.getCritDamage())
                .energyRecharge(yieldWeightPO.getEnergyRecharge())
                .elementalMastery(yieldWeightPO.getElementalMastery())
                .attackPercentage(yieldWeightPO.getAttackPercentage())
                .attack(yieldWeightPO.getAttack())
                .healthPercentage(yieldWeightPO.getHealthPercentage())
                .health(yieldWeightPO.getHealth())
                .defensePercentage(yieldWeightPO.getDefensePercentage())
                .defense(yieldWeightPO.getDefense())
                .build();
    }

    public YieldWeightResult convert(YieldWeight yieldWeight, String belongTo) {
        return YieldWeightResult.builder()
                .belongTo(belongTo)
                .critChance(yieldWeight.getCritChance())
                .critDamage(yieldWeight.getCritDamage())
                .energyRecharge(yieldWeight.getEnergyRecharge())
                .elementalMastery(yieldWeight.getElementalMastery())
                .attackPercentage(yieldWeight.getAttackPercentage())
                .attack(yieldWeight.getAttack())
                .healthPercentage(yieldWeight.getHealthPercentage())
                .health(yieldWeight.getHealth())
                .defensePercentage(yieldWeight.getDefensePercentage())
                .defense(yieldWeight.getDefense())
                .build();
    }

}
