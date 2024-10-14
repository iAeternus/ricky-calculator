package io.github.ricky.core.polynomial.domain;

import io.github.ricky.core.polynomial.application.dto.CalculateResultCommand;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/10/13
 * @className PolynomialExpressionRepository
 * @desc
 */
public interface PolynomialExpressionRepository {

    void save(PolynomialExpression polynomialExpression);

    PolynomialExpression byId(String id);
}
