package io.github.ricky.core.polynomial.domain.expression;

import io.github.ricky.core.polynomial.domain.ExpressionTypeEnum;
import io.github.ricky.core.polynomial.domain.Polynomial;

import java.util.List;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/10/13
 * @className CalculateResultExpressionBuilder
 * @desc
 */
public class CalculateResultExpressionBuilder implements ExpressionBuilder {
    @Override
    public boolean canBuild(ExpressionTypeEnum type) {
        return ExpressionTypeEnum.RESULT == type;
    }

    @Override
    public String doBuild(List<Object> leftArgs, List<Object> rightArgs) {
        Polynomial polynomial = (Polynomial) leftArgs.get(0);
        Double x = (Double) leftArgs.get(1);
        Double result = (Double) rightArgs.get(0);
        return "f(" + x + ") = " + polynomial + " = " + result;
    }

    @Override
    public boolean isValidArgs(List<Object> leftArgs, List<Object> rightArgs) {
        if (leftArgs.size() != 2 || rightArgs.size() != 1) {
            return false;
        }

        return leftArgs.get(0) instanceof Polynomial &&
                leftArgs.get(1) instanceof Double &&
                rightArgs.get(0) instanceof Double;
    }
}
