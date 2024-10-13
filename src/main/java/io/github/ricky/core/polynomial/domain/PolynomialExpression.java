package io.github.ricky.core.polynomial.domain;

import io.github.ricky.core.common.constants.RcConstants;
import io.github.ricky.core.common.domain.AggregateRoot;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/10/13
 * @className PolynomialExpression
 * @desc 多项式运算表达式
 */
@Getter
@Document(RcConstants.POLYNOMIAL_EXPRESSION_COLLECTION)
@TypeAlias(RcConstants.POLYNOMIAL_EXPRESSION_COLLECTION)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PolynomialExpression extends AggregateRoot {

    /**
     * 表达式
     */
    private String expression;

    public PolynomialExpression(String expression) {
        this.expression = expression;
    }
}
