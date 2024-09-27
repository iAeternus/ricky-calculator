package io.github.ricky.core.relic.domain;

import io.github.ricky.core.common.domain.AggregateRoot;
import io.github.ricky.core.common.utils.RcConstants;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.TypeAlias;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/9/27
 * @className Relic
 * @desc 圣遗物
 */
@Getter
@TypeAlias(RcConstants.APP_COLLECTION)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Relic extends AggregateRoot {
}
