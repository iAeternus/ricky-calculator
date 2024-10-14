package io.github.ricky.core.polynomial.application;

import io.github.ricky.core.polynomial.application.dto.CalculateResultCommand;
import io.github.ricky.core.polynomial.application.dto.CalculateResultResponse;
import io.github.ricky.core.polynomial.domain.ExpressionTypeEnum;
import io.github.ricky.core.polynomial.domain.Polynomial;
import io.github.ricky.core.polynomial.domain.PolynomialExpression;
import io.github.ricky.core.polynomial.domain.PolynomialExpressionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/10/12
 * @className PolynomialApplicationService
 * @desc
 */
@Service
@RequiredArgsConstructor
public class PolynomialApplicationService {

    private final PolynomialExpressionRepository polynomialExpressionRepository;

    @Transactional
    public CalculateResultResponse calculateResult(CalculateResultCommand command) {
        Polynomial polynomial = Polynomial.of(command.getPolynomial());
        double result = polynomial.result(command.getX());

        PolynomialExpression polynomialExpression = PolynomialExpression.of(List.of(polynomial, command.getX()),
                List.of(result), ExpressionTypeEnum.RESULT);
        polynomialExpressionRepository.save(polynomialExpression);

        return CalculateResultResponse.builder()
                .id(polynomialExpression.getId())
                .result(result)
                .build();
    }
}
