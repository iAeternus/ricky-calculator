package io.github.ricky.core.polynomial.domain.expression;

import io.github.ricky.core.polynomial.domain.ExpressionTypeEnum;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/10/13
 * @className ExpressionBuilderDelegator
 * @desc
 */
public class ExpressionBuilderDelegator {

    private static final List<ExpressionBuilder> BUILDERS = new ArrayList<>();

    static {
        BUILDERS.add(new CalculateResultExpressionBuilder());
    }

    public static String build(List<Object> leftArgs, List<Object> rightArgs, ExpressionTypeEnum type) {
        for (ExpressionBuilder builder : BUILDERS) {
            if(builder.canBuild(type)) {
                return builder.build(leftArgs, rightArgs);
            }
        }
        throw new RuntimeException("Invalid expression type [" + type + "]");
    }

}
