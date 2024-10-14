package io.github.ricky.core.polynomial.domain.expression;

import io.github.ricky.core.polynomial.domain.ExpressionTypeEnum;

import java.util.List;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/10/13
 * @className ExpressionBuilder
 * @desc 多项式表达式构建
 */
public interface ExpressionBuilder {

    /**
     * 判断能否构建
     * @param type 表达式类型
     * @return true=能 false=不能
     */
    boolean canBuild(ExpressionTypeEnum type);

    /**
     * 判断表达式参数是否合法
     * @param leftArgs 表达式左值
     * @param rightArgs 表达式右值
     * @return true=合法 false=不合法
     */
    boolean isValidArgs(List<Object> leftArgs, List<Object> rightArgs);

    /**
     * 执行构建
     * @param leftArgs 表达式左值
     * @param rightArgs 表达式右值
     * @return 表达式字符串
     */
    String doBuild(List<Object> leftArgs, List<Object> rightArgs);

    /**
     * 使用参数构建表达式
     * @param leftArgs 表达式左值
     * @param rightArgs 表达式右值
     * @return 表达式字符串
     */
    default String build(List<Object> leftArgs, List<Object> rightArgs) {
        if(!isValidArgs(leftArgs, rightArgs)) {
            throw new RuntimeException("Invalid expression argument.");
        }

        return doBuild(leftArgs, rightArgs);
    }

}
