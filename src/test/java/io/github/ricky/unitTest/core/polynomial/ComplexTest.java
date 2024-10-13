package io.github.ricky.unitTest.core.polynomial;

import io.github.ricky.core.polynomial.domain.Complex;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/9/20
 * @className ComplexTest
 * @desc
 */
class ComplexTest {

    private final Complex a = new Complex(1, 2);
    private final Complex b = new Complex(1, -2);

    @Test
    public void add() {
        // When
        Complex result = Complex.add(a, b);

        // Then
        System.out.println(result);
        assertThat(result.toString()).isEqualTo("2+0i");
    }

    @Test
    public void subtract() {
        // When
        Complex result = Complex.subtract(a, b);

        // Then
        System.out.println(result);
        assertThat(result.toString()).isEqualTo("0+4i");
    }

    @Test
    public void multiply() {
        // When
        Complex result = Complex.multiply(a, b);

        // Then
        System.out.println(result);
        assertThat(result.toString()).isEqualTo("5+0i");
    }

    @Test
    public void divide() {
        // When
        Complex result = Complex.divide(a, b);

        // Then
        System.out.println(result);
        assertThat(result.toString()).isEqualTo("-0.6+0.8i");
    }

}