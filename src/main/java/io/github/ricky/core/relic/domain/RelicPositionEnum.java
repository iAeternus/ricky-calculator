package io.github.ricky.core.relic.domain;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/9/27
 * @className RelicPositionEnum
 * @desc 圣遗物位置
 */
public enum RelicPositionEnum {

    /**
     * 生之花
     */
    FLOWER_OF_LIFE,

    /**
     * 死之羽
     */
    PLUME_OF_DEATH,

    /**
     * 时之沙
     */
    SANDS_OF_EON,

    /**
     * 空之杯
     */
    GOBLET_OF_EONOTHEM,

    /**
     * 理之冠
     */
    CIRCLET_OF_LOGOS,
    ;

    public static RelicPositionEnum of(int value) {
        return switch (value) {
            case 0 -> FLOWER_OF_LIFE;
            case 1 -> PLUME_OF_DEATH;
            case 2 -> SANDS_OF_EON;
            case 3 -> GOBLET_OF_EONOTHEM;
            case 4 -> CIRCLET_OF_LOGOS;
            default -> throw new IllegalStateException("Unexpected value: " + value);
        };
    }

}
