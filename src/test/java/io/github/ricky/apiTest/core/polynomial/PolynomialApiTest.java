package io.github.ricky.apiTest.core.polynomial;

import io.github.ricky.apiTest.BaseApiTest;
import io.github.ricky.core.polynomial.application.dto.CalculateResultCommand;
import io.github.ricky.core.polynomial.application.dto.CalculateResultResponse;
import io.github.ricky.core.polynomial.domain.ExpressionTypeEnum;
import io.github.ricky.core.polynomial.domain.Polynomial;
import io.github.ricky.core.polynomial.domain.PolynomialExpression;
import io.github.ricky.core.polynomial.domain.expression.ExpressionBuilderDelegator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/10/13
 * @className PolynomialApiTest
 * @desc
 */
public class PolynomialApiTest extends BaseApiTest {

    @Test
    public void calculate_result_should_save_expression() {
        // Given
        CalculateResultCommand command = CalculateResultCommand.builder()
                .polynomial("x^2 + 2x + 1")
                .x(2.0)
                .build();

        // When
        CalculateResultResponse response = PolynomialApi.calculateResult(command);

        // Then
        assertThat(response.getResult()).isEqualTo(9.0);

        PolynomialExpression polynomialExpression = polynomialExpressionRepository.byId(response.getId());
        assertThat(polynomialExpression.getExpression())
                .isEqualTo(ExpressionBuilderDelegator.build(List.of(new Polynomial("x^2 + 2x + 1"), 2.0), List.of(9.0), ExpressionTypeEnum.RESULT));
    }

}
