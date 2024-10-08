package io.github.ricky.core.relic.infrastructure.converter;

import io.github.ricky.core.relic.domain.yieldweight.YieldWeight;
import io.github.ricky.core.relic.infrastructure.vo.YieldWeightVO;
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

    public YieldWeight convert(YieldWeightVO yieldWeightVO) {
        return YieldWeight.builder()
                .critChance(yieldWeightVO.getCritChance())
                .critDamage(yieldWeightVO.getCritDamage())
                .energyRecharge(yieldWeightVO.getEnergyRecharge())
                .elementalMastery(yieldWeightVO.getElementalMastery())
                .attackPercentage(yieldWeightVO.getAttackPercentage())
                .attack(yieldWeightVO.getAttack())
                .healthPercentage(yieldWeightVO.getHealthPercentage())
                .health(yieldWeightVO.getHealth())
                .defensePercentage(yieldWeightVO.getDefensePercentage())
                .defense(yieldWeightVO.getDefense())
                .build();
    }

}
