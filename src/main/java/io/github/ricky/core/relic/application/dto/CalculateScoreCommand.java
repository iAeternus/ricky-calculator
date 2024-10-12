package io.github.ricky.core.relic.application.dto;

import io.github.ricky.core.common.marker.Command;
import io.github.ricky.core.common.validation.number.DoubleRange;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    @Size(max = 10)
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
