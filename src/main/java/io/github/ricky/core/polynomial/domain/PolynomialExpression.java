package io.github.ricky.core.polynomial.domain;

import io.github.ricky.core.common.constants.RcConstants;
import io.github.ricky.core.common.domain.AggregateRoot;
import io.github.ricky.core.common.utils.SnowflakeIdGenerator;
import io.github.ricky.core.polynomial.domain.expression.ExpressionBuilderDelegator;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Transient;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

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
     * 表达式左值
     */
    @Transient
    private List<Object> leftArgs;

    /**
     * 表达式右值
     */
    @Transient
    private List<Object> rightArgs;

    /**
     * 表达式类型
     */
    @Transient
    private ExpressionTypeEnum type;

    /**
     * 表达式字符串
     */
    private String expression;

    public PolynomialExpression(List<Object> leftArgs, List<Object> rightArgs, ExpressionTypeEnum type) {
        super(newExpressionId());
        this.leftArgs = leftArgs;
        this.rightArgs = rightArgs;
        this.type = type;
        this.expression = ExpressionBuilderDelegator.build(this.leftArgs, this.rightArgs, this.type);
    }

    public static PolynomialExpression of(List<Object> leftArgs, List<Object> rightArgs, ExpressionTypeEnum type) {
        return new PolynomialExpression(leftArgs, rightArgs, type);
    }

    public static String newExpressionId() {
        return "PNE" + SnowflakeIdGenerator.newSnowflakeId();
    }
}
