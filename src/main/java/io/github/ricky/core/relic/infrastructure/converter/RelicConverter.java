package io.github.ricky.core.relic.infrastructure.converter;

import io.github.ricky.core.relic.domain.Relic;
import io.github.ricky.core.relic.query.dto.RelicHistoryResult;
import org.springframework.stereotype.Component;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/10/12
 * @className RelicConverter
 * @desc
 */
@Component
public class RelicConverter {

    public RelicHistoryResult convert(Relic relic) {
        return RelicHistoryResult.builder()
                .belongTo(relic.getBelongTo())
                .isCritical(relic.isCritical())
                .position(relic.getPosition())
                .critChance(relic.getAttr().getCritChance())
                .critDamage(relic.getAttr().getCritDamage())
                .energyRecharge(relic.getAttr().getEnergyRecharge())
                .elementalMastery(relic.getAttr().getElementalMastery())
                .attackPercentage(relic.getAttr().getAttackPercentage())
                .attack(relic.getAttr().getAttack())
                .healthPercentage(relic.getAttr().getHealthPercentage())
                .health(relic.getAttr().getHealth())
                .defensePercentage(relic.getAttr().getDefensePercentage())
                .defense(relic.getAttr().getDefense())
                .score(relic.getScore())
                .build();
    }

}
