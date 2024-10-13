package io.github.ricky.core.polynomial.domain;

import io.github.ricky.core.common.marker.ValueObject;
import io.github.ricky.core.common.utils.DigitUtils;
import lombok.EqualsAndHashCode;
import lombok.Value;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/9/20
 * @className Complex
 * @desc 复数
 */
@Value
@EqualsAndHashCode
public class Complex implements ValueObject {

    public static final Complex ZERO = new Complex(0, 0);

    /**
     * 实部
     */
    double real;

    /**
     * 虚部
     */
    double imag;

    public Complex(double real, double imag) {
        this.real = real;
        this.imag = imag;
    }

    /**
     * 将实数转换为只有实部的虚数
     *
     * @param num 实数
     * @return 只有实部的虚数
     */
    public static Complex toComplex(double num) {
        return new Complex(num, 0);
    }

    /**
     * 将只有实部的虚数转换为实数
     *
     * @return 实数
     */
    public double toReal() {
        if (DigitUtils.isNotZero(imag)) {
            throw new RuntimeException(this + " has an imaginary part.");
        }
        return this.real;
    }

    /**
     * 计算复数模的平方
     *
     * @return 模平方
     */
    public double sqrModulo() {
        return real * real + imag * imag;
    }

    /**
     * 计算复数的模
     *
     * @return 模
     */
    public double modulo() {
        return Math.sqrt(sqrModulo());
    }

    /**
     * 复数相加
     *
     * @param another 另一个复数
     * @return this + another
     */
    public Complex add(Complex another) {
        return new Complex(this.real + another.real, this.imag + another.imag);
    }

    /**
     * 复数相加
     *
     * @param c1 第一个复数
     * @param c2 第二个复数
     * @return c1 + c2
     */
    public static Complex add(Complex c1, Complex c2) {
        return c1.add(c2);
    }

    /**
     * 复数相减
     *
     * @param another 另一个复数
     * @return this - another
     */
    public Complex subtract(Complex another) {
        return new Complex(this.real - another.real, this.imag - another.imag);
    }

    /**
     * 复数相减
     *
     * @param c1 第一个复数
     * @param c2 第二个复数
     * @return c1 - c2
     */
    public static Complex subtract(Complex c1, Complex c2) {
        return c1.subtract(c2);
    }

    /**
     * 复数相乘
     *
     * @param another 另一个复数
     * @return this - another
     */
    public Complex multiply(Complex another) {
        return new Complex(
                this.real * another.real - this.imag * another.imag,
                this.real * another.imag + this.imag * another.real
        );
    }

    /**
     * 复数相乘
     *
     * @param c1 第一个复数
     * @param c2 第二个复数
     * @return c1 * c2
     */
    public static Complex multiply(Complex c1, Complex c2) {
        return c1.multiply(c2);
    }

    /**
     * 复数乘实数
     *
     * @param realNumber 实数
     * @return this * realNumber
     */
    public Complex multiply(double realNumber) {
        return new Complex(real * realNumber, imag * realNumber);
    }

    /**
     * 复数乘实数
     *
     * @param complex    复数
     * @param realNumber 实数
     * @return complex * realNumber
     */
    public static Complex multiply(Complex complex, double realNumber) {
        return complex.multiply(realNumber);
    }

    /**
     * 复数相除
     *
     * @param another 另一个复数
     * @return this / another
     */
    public Complex divide(Complex another) {
        double sqrModulo = another.sqrModulo();
        if (sqrModulo == 0) {
            throw new RuntimeException("Cannot divide by zero complex number: " + another);
        }

        return new Complex(
                (this.real * another.real + this.imag * another.imag) / sqrModulo,
                (this.imag * another.real - this.real * another.imag) / sqrModulo
        );
    }

    /**
     * 复数相除
     *
     * @param c1 第一个复数
     * @param c2 第二个复数
     * @return c1 / c2
     */
    public static Complex divide(Complex c1, Complex c2) {
        return c1.divide(c2);
    }

    /**
     * 复数除以实数
     *
     * @param realNumber 实数
     * @return this / realNumber
     */
    public Complex divide(double realNumber) {
        if (realNumber == 0) {
            throw new RuntimeException("Cannot divide by zero.");
        }

        return new Complex(real / realNumber, imag / realNumber);
    }

    /**
     * 复数除以实数
     *
     * @param complex    复数
     * @param realNumber 实数
     * @return complex / realNumber
     */
    public static Complex divide(Complex complex, double realNumber) {
        return complex.divide(realNumber);
    }

    /**
     * 计算共轭复数
     *
     * @return 共轭复数
     */
    public Complex conjugate() {
        return new Complex(this.real, -this.imag);
    }

    @Override
    public String toString() {
        return DigitUtils.doubleToString(real, false) +
                (imag >= 0 ? "+" : "-") +
                DigitUtils.doubleToString(imag, true) + "i";
    }
}
