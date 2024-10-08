package io.github.ricky.core.relic.domain;

import io.github.ricky.core.common.domain.AggregateRoot;
import io.github.ricky.core.common.utils.RcConstants;
import io.github.ricky.core.common.utils.SnowflakeIdGenerator;
import io.github.ricky.core.relic.domain.event.CalculateScoreDomainEvent;
import io.github.ricky.core.relic.domain.yieldweight.YieldWeight;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

import static io.github.ricky.core.common.utils.CommonUtils.keepTwo;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/9/27
 * @className Relic
 * @desc 圣遗物
 */
@Getter
@Document
@TypeAlias(RcConstants.APP_COLLECTION)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Relic extends AggregateRoot {

    /**
     * 所属角色
     */
    @NotNull
    private String belongTo;

    /**
     * 主词条是否为双爆
     */
    private boolean isCritical;

    /**
     * 圣遗物位置
     */
    @NotNull
    private RelicPositionEnum position;

    /**
     * 圣遗物副词条
     */
    @Valid
    @NotNull
    private RelicAttr attr;

    /**
     * 圣遗物评分
     */
    private double score;

    public Relic(String belongTo, Boolean isCritical, RelicPositionEnum position, RelicAttr attr) {
        super(newRelicId());
        this.belongTo = belongTo;
        this.isCritical = isCritical;
        this.position = position;
        this.attr = attr;
        this.score = 0.0;
    }

    public static String newRelicId() {
        return "RLC" + SnowflakeIdGenerator.newSnowflakeId();
    }

    /**
     * 计算评分
     *
     * @param weight 收益权重
     * @return 评分
     */
    public double calculateScore(YieldWeight weight) {
        Objects.requireNonNull(weight, "Yield weight must not be null.");

        double score = attr.getCritChance() * 2.0 * weight.getCritChance() +
                attr.getCritDamage() * 1.0 * weight.getCritDamage() +
                attr.getEnergyRecharge() * 1.1979 * weight.getEnergyRecharge() +
                attr.getElementalMastery() * 0.33 * weight.getElementalMastery() +
                attr.getAttackPercentage() * 1.33 * weight.getAttackPercentage() +
                attr.getAttack() * 0.398 * 0.5 * weight.getAttack() +
                attr.getHealthPercentage() * 1.33 * weight.getHealthPercentage() +
                attr.getHealth() * 0.026 * 0.66 * weight.getHealth() +
                attr.getDefensePercentage() * 1.06 * weight.getDefensePercentage() +
                attr.getDefense() * 0.355 * 0.66 * weight.getDefense();

        score += isCritical ? 20 : 0; // 处理主词条
        score = keepTwo(score);
        raiseEvent(new CalculateScoreDomainEvent(this));
        return score;
    }
}
