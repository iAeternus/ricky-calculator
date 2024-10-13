package io.github.ricky.unitTest.core.polynomial;

import io.github.ricky.core.common.utils.DigitUtils;
import io.github.ricky.core.polynomial.domain.Polynomial;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/9/19
 * @className PolynomialTest
 * @desc
 */
class PolynomialTest {

    @Test
    public void exceptions_should_be_thrown_when_illegal_polynomials_exist() {
        assertThatThrownBy(() -> new Polynomial(""))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("The polynomial string cannot be blank.");
        assertThatNoException().isThrownBy(() -> new Polynomial("-2x^4 +_2x^2$$$$+ 2x + 3    "));
    }

    @Test
    public void result() {
        // Given
        Polynomial polynomial = new Polynomial("2x^4 + 2x^2 + 2x + 3");

        // When
        double res1 = polynomial.result(1);
        double res2 = polynomial.result(2);
        double res3 = polynomial.result(1.5);

        // Then
        System.out.println(res1);
        System.out.println(res2);
        System.out.println(res3);
        assertThat(res1).isEqualTo(9);
        assertThat(res2).isEqualTo(47);
        assertThat(res3).isEqualTo(20.625);
    }

    @Test
    public void add() {
        // Given
        Polynomial poly1 = new Polynomial("2x^4 + 2x^2 + 2x");
        Polynomial poly2 = new Polynomial("-x^3 + x^2 + x");

        // When
        Polynomial result = Polynomial.add(poly1, poly2);

        // Then
        System.out.println(result);
        assertThat(result).isEqualTo(new Polynomial("2x^4 - x^3 + 3x^2 + 3x"));
    }

    @Test
    public void subtract() {
        // Given
        Polynomial poly1 = new Polynomial("2x^4 + 2x^2 + 2x");
        Polynomial poly2 = new Polynomial("-x^3 + x^2 + x");

        // When
        Polynomial result = Polynomial.subtract(poly1, poly2);

        // Then
        System.out.println(result);
        assertThat(result).isEqualTo(new Polynomial("2x^4 + x^3 + x^2 + x"));
    }

    @Test
    public void multiply() {
        // Given
        Polynomial poly1 = new Polynomial("2x^4 + 2x^2 + 2x");
        Polynomial poly2 = new Polynomial("-x^3 + x^2 + x");

        // When
        Polynomial result = Polynomial.multiply(poly1, poly2);

        // Then
        System.out.println(result);
        assertThat(result).isEqualTo(new Polynomial("-2x^7 + 2x^6 + 4x^3 + 2x^2"));
    }

    @Test
    public void pow() {
        // Given
        Polynomial polynomial = new Polynomial("x^3 + x^2");

        // When
        Polynomial res1 = Polynomial.pow(polynomial, 1);
        Polynomial res2 = Polynomial.pow(polynomial, 2);
        Polynomial res3 = Polynomial.pow(polynomial, 3);

        // Then
        System.out.println(res1);
        System.out.println(res2);
        System.out.println(res3);

        assertArrayEqualsWithDelta(res1.toArray(), new double[]{1, 1}, DigitUtils.EPS);
        assertArrayEqualsWithDelta(res2.toArray(), new double[]{1, 2, 1}, DigitUtils.EPS);
        assertArrayEqualsWithDelta(res3.toArray(), new double[]{1, 3, 3, 1}, DigitUtils.EPS);

        // assertThat(res1).isEqualTo(new Polynomial("x^3 + x^2"));
        // assertThat(res2).isEqualTo(new Polynomial("x^6 + 2x^5 + x^4"));
        // assertThat(res3).isEqualTo(new Polynomial("x^9 + 3x^8 + 3x^7 + x^6"));
    }

    @Test
    public void derivative() {
        // Given
        Polynomial polynomial = new Polynomial("x^2 + 2x + 1");

        // When
        Polynomial derivative = polynomial.derivative();

        // Then
        System.out.println(derivative);
        assertThat(derivative).isEqualTo(new Polynomial("2x + 2"));
    }

    @Test
    public void original() {
        // Given
        Polynomial polynomial = new Polynomial("2x + 2");

        // When
        Polynomial original = polynomial.original();

        // Then
        System.out.println(original);
        assertThat(original).isEqualTo(new Polynomial("x^2 + 2x"));
    }

    /**
     * 对数组中的每个数考虑浮点数精度误差
     *
     * @param actual   实际
     * @param expected 期望
     * @param delta    误差阈值
     */
    public static void assertArrayEqualsWithDelta(double[] actual, double[] expected, double delta) {
        assertThat(actual).isNotNull();
        assertThat(expected).isNotNull();
        assertThat(actual.length).isEqualTo(expected.length);

        for (int i = 0; i < actual.length; i++) {
            assertThat(Math.abs(actual[i] - expected[i])).isLessThanOrEqualTo(delta);
        }
    }

}