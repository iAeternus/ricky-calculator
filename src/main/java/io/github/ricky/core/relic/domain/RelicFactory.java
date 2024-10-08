package io.github.ricky.core.relic.domain;

import io.github.ricky.core.relic.application.dto.CalculateScoreCommand;
import org.springframework.stereotype.Component;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/10/8
 * @className RelicFactory
 * @desc
 */
@Component
public class RelicFactory {

    public Relic createRelic(CalculateScoreCommand command) {
        RelicAttr relicAttr = RelicAttr.builder()
                .critChance(command.getCritChance())
                .critDamage(command.getCritDamage())
                .energyRecharge(command.getEnergyRecharge())
                .elementalMastery(command.getElementalMastery())
                .attackPercentage(command.getAttackPercentage())
                .attack(command.getAttack())
                .healthPercentage(command.getHealthPercentage())
                .health(command.getHealth())
                .defensePercentage(command.getDefensePercentage())
                .defense(command.getDefense())
                .build();
        return new Relic(command.getBelongTo(), command.isCritical(), RelicPositionEnum.of(command.getPosition()), relicAttr);
    }

}
