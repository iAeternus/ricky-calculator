package io.github.ricky.core.relic.application.dto;

import io.github.ricky.core.common.utils.Command;
import io.github.ricky.core.common.validation.attr.AttrRange;
import io.github.ricky.core.relic.domain.RelicPositionEnum;
import jakarta.validation.constraints.NotNull;
import lombok.*;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/9/27
 * @className CalculateScoreCommand
 * @desc
 */
@Value
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CalculateScoreCommand implements Command {

    /**
     * 所属角色
     */
    @NotNull
    String belongTo;

    /**
     * 主词条是否为双爆
     */
    boolean isCritical;

    /**
     * 圣遗物位置<br>
     * 0-花 1-毛 2-沙 3-杯 4-头
     */
    int position;

    /**
     * 暴击率
     */
    @AttrRange(max = 23.3)
    double critChance;

    /**
     * 暴击伤害
     */
    @AttrRange(max = 46.6)
    double critDamage;

    /**
     * 充能
     */
    @AttrRange(max = 38.9)
    double energyRecharge;

    /**
     * 精通
     */
    @AttrRange(max = 140)
    double elementalMastery;

    /**
     * 大攻击
     */
    @AttrRange(max = 35)
    double attackPercentage;

    /**
     * 小攻击
     */
    @AttrRange(max = 117)
    double attack;

    /**
     * 大生命
     */
    @AttrRange(max = 35)
    double healthPercentage;

    /**
     * 小生命
     */
    @AttrRange(max = 1793)
    double health;

    /**
     * 大防御
     */
    @AttrRange(max = 43.7)
    double defensePercentage;

    /**
     * 小防御
     */
    @AttrRange(max = 139)
    double defense;

}
