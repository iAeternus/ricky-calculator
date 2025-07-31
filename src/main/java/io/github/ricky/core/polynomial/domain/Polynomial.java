package io.github.ricky.core.polynomial.domain;

import io.github.ricky.core.common.marker.ValueObject;
import io.github.ricky.core.common.utils.DigitUtils;
import lombok.EqualsAndHashCode;
import lombok.Value;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static io.github.ricky.core.common.constants.RcRegexConstants.*;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/9/19
 * @className Polynomial
 * @desc 系数表示多项式<br>
 * f(x) = ax^2 + bx + c
 * 系数表示：[a, b, c]
 */
@Value
@EqualsAndHashCode
public class Polynomial implements ValueObject {

    /**
     * exponent -> coefficient
     */
    Map<Integer, Double> terms;

    public static final Polynomial ZERO = new Polynomial();
    public static final Polynomial ONE = new Polynomial();

    static {
        ZERO.addTerm(0.0, 0);
        ONE.addTerm(1.0, 0);
    }

    public Polynomial() {
        this.terms = new HashMap<>();
    }

    /**
     * 构造n-1次多项式
     *
     * @param coefs 系数向量，按升幂排列 [C0, C1, ..., Cn-1]
     */
    public Polynomial(double[] coefs) {
        this.terms = new HashMap<>();
        for (int i = 0; i < coefs.length; ++i) {
            addTerm(coefs[i], i);
        }
    }

    public Polynomial(String polynomial) {
        if (StringUtils.isBlank(polynomial)) {
            throw new IllegalArgumentException("The polynomial string cannot be blank.");
        }

        // 在多项式字符串开头添加一个正号，用于处理以负号开头的项。并移除多项式中的所有空格
        String modifiedPolynomial = ("+" + polynomial).replaceAll(ILLEGAL_POLYNOMIAL_CHARACTERS_PATTERN, "");
        this.terms = new HashMap<>();
        Pattern pattern = Pattern.compile(POLYNOMIAL_TERM_PATTERN);
        Matcher matcher = pattern.matcher(modifiedPolynomial);

        String[] termsArray = modifiedPolynomial.split(POLYNOMIAL_SPLIT_PATTERN); // 分割项

        for (String term : termsArray) {
            if (term.trim().isEmpty() || "+".equals(term) || "-".equals(term)) {
                continue;
            }

            // 移除开头的正负号，方便匹配
            String trimmedTerm = term.trim().substring(1);
            matcher.reset(trimmedTerm);

            boolean isNegative = term.charAt(0) == '-';
            if (matcher.find()) {
                String coefficientStr = matcher.group(1);
                String exponentStr = matcher.group(2);
                double coefficient = StringUtils.isBlank(coefficientStr) ? 1.0 : Double.parseDouble(coefficientStr);
                int exponent = StringUtils.isBlank(exponentStr) ? (term.contains("x") ? 1 : 0) : Integer.parseInt(exponentStr);

                if (isNegative) {
                    coefficient = -coefficient;
                }

                addTerm(coefficient, exponent);
            } else {
                throw new IllegalArgumentException("Invalid polynomial term: " + term);
            }
        }
    }

    public static Polynomial of(double[] coefs) {
        return new Polynomial(coefs);
    }

    public static Polynomial of(String polynomial) {
        return new Polynomial(polynomial);
    }

    /**
     * 添加项
     *
     * @param coefficient 系数
     * @param exponent    指数
     */
    private void addTerm(double coefficient, int exponent) {
        if (DigitUtils.isZero(coefficient)) {
            return;
        }
        terms.put(exponent, coefficient);
    }

    /**
     * 批量添加项
     *
     * @param coefficients 系数数组
     * @param exponents    指数数组
     */
    @Deprecated
    public void addTerms(double[] coefficients, int[] exponents) {
        if (coefficients.length != exponents.length) {
            throw new RuntimeException("The coefficient array and the exponent array must be the same length.");
        }

        for (int i = 0; i < coefficients.length; i++) {
            addTerm(coefficients[i], exponents[i]);
        }
    }

    /**
     * 获取最大的指数，如果term为空则抛出异常
     *
     * @return 最大的指数
     */
    public int getMaxExp() {
        return terms.keySet().stream()
                .max(Comparator.naturalOrder())
                .orElseThrow(() -> new IllegalStateException("No maximum exponent found"));
    }

    /**
     * 获取最小的指数，如果term为空则抛出异常
     *
     * @return 最小的指数
     */
    public int getMinExp() {
        return terms.keySet().stream()
                .min(Comparator.naturalOrder())
                .orElseThrow(() -> new IllegalStateException("No minimum exponent found"));
    }

    /**
     * 计算多项式f(x)在x=x0时的结果f(x0)
     * 时间复杂度：O(n)
     *
     * @param x0 自变量
     * @return 函数值
     */
    public double result(double x0) {
        int maxExp = getMaxExp();
        int minExp = getMinExp();
        double result = 0.0;
        for (int exp = maxExp; exp >= minExp; --exp) {
            double coef = fixedToZeroIfNull(terms.get(exp));
            result = result * x0 + coef;
        }
        return result;
    }

    /**
     * 将多项式转化为系数数组，按降幂排列
     *
     * @return 系数数组
     */
    public double[] toArray() {
        int minExp = getMinExp();
        double[] coefs = new double[getMaxExp() - minExp + 1];
        this.terms.forEach((exp, coef) -> coefs[coefs.length - exp + minExp - 1] = coef);
        return coefs;
    }

    /**
     * 多项式加法
     * 时间复杂度：O(n)
     *
     * @param another 另一个多项式
     * @return this + another
     */
    public Polynomial add(Polynomial another) {
        return this.operate(another, Double::sum);
    }

    /**
     * 多项式加法
     * 时间复杂度：O(n)
     *
     * @param poly1 第一个多项式
     * @param poly2 第二个多项式
     * @return poly1 + poly2
     */
    public static Polynomial add(Polynomial poly1, Polynomial poly2) {
        return poly1.add(poly2);
    }

    /**
     * 多项式减法
     * 时间复杂度：O(n)
     *
     * @param another 另一个多项式
     * @return this - another
     */
    public Polynomial subtract(Polynomial another) {
        return this.operate(another, (coef1, coef2) -> coef1 - coef2);
    }

    /**
     * 多项式减法
     * 时间复杂度：O(n)
     *
     * @param poly1 第一个多项式
     * @param poly2 第二个多项式
     * @return poly1 - poly2
     */
    public static Polynomial subtract(Polynomial poly1, Polynomial poly2) {
        return poly1.subtract(poly2);
    }

    /**
     * 多项式乘常数
     *
     * @param number 常数
     * @return this * number
     */
    public Polynomial multiply(double number) {
        Polynomial result = new Polynomial();
        this.terms.forEach((exp, coef) -> result.addTerm(coef * number, exp));
        return result;
    }

    /**
     * 多项式乘法
     * 时间复杂度：O(n log n)
     *
     * @param another 另一个多项式
     * @return this * another
     */
    public Polynomial multiply(Polynomial another) {
        int n = this.getMaxExp();
        int m = another.getMaxExp();
        int len = getLength(n, m);

        // 预处理
        Complex[] a = pretreatment(this, len);
        Complex[] b = pretreatment(another, len);

        // 系数表达转点值表达
        fft(a, false);
        fft(b, false);

        // 点值表达多项式乘法
        Complex[] c = new Complex[len];
        for (int i = 0; i < len; i++) {
            c[i] = a[i].multiply(b[i]);
        }

        // 逆FFT，点值表达转系数表达
        fft(c, true);

        // 构建结果多项式
        Polynomial result = new Polynomial();
        for (int i = 0; i < n + m + 1; i++) {
            result.addTerm(c[i].getReal(), i);
        }

        return result;
    }

    /**
     * 多项式乘法
     * 时间复杂度：O(n log n)
     *
     * @param poly1 第一个多项式
     * @param poly2 第二个多项式
     * @return poly1 * poly2
     */
    public static Polynomial multiply(Polynomial poly1, Polynomial poly2) {
        return poly1.multiply(poly2);
    }

    /**
     * 多项式求幂
     * 时间复杂度：O(n log n log k)
     *
     * @param polynomial 多项式
     * @param power      幂次
     * @return polynomial^power
     */
    public static Polynomial pow(Polynomial polynomial, int power) {
        Polynomial ans = ONE;
        while (power != 0) {
            if ((power & 1) != 0) {
                ans = ans.multiply(polynomial);
            }
            polynomial = polynomial.multiply(polynomial);
            power >>= 1;
        }
        return ans;
    }

    /**
     * 获取当前多项式的导函数
     *
     * @return 当前多项式的导函数
     */
    public Polynomial derivative() {
        Polynomial ans = new Polynomial();
        this.terms.forEach((exp, coef) -> ans.addTerm(coef * exp, exp - 1));
        return ans;
    }

    /**
     * 获取当前多项式的原函数，这里省略了加在原函数末位的常数项C
     *
     * @return 当前多项式的原函数
     */
    public Polynomial original() {
        Polynomial ans = new Polynomial();
        this.terms.forEach((exp, coef) -> ans.addTerm(coef / (exp + 1), exp + 1));
        return ans;
    }

    /**
     * 运算
     */
    @FunctionalInterface
    private interface Operation {

        /**
         * 执行运算
         *
         * @param coef1 第一个系数
         * @param coef2 第二个系数
         * @return 运算结果
         */
        double operate(double coef1, double coef2);
    }

    /**
     * 多项式运算
     *
     * @param another   另一个多项式
     * @param operation 实际的运算
     * @return 结果多项式
     */
    private Polynomial operate(Polynomial another, Operation operation) {
        int maxExp = Math.max(this.getMaxExp(), another.getMaxExp());
        int minExp = Math.min(this.getMinExp(), another.getMinExp());
        Polynomial result = new Polynomial();
        forEachCoef(maxExp, minExp, (exp) -> {
            double coef = fixedToZeroIfNull(this.terms.get(exp));
            double anotherCoef = fixedToZeroIfNull(another.terms.get(exp));
            result.addTerm(operation.operate(coef, anotherCoef), exp);
        });
        return result;
    }

    /**
     * 按照降序遍历系数向量，间隔为1，并执行谓词操作
     *
     * @param maxExp   指数最大值
     * @param minExp   指数最小值
     * @param consumer 谓词 pred(coef)
     */
    private void forEachCoef(int maxExp, int minExp, Consumer<Integer> consumer) {
        if (minExp >= maxExp) {
            return;
        }

        for (int i = maxExp; i >= minExp; --i) {
            consumer.accept(i);
        }
    }

    /**
     * 修正浮点数
     *
     * @param num 浮点数
     * @return 若为null修正为0.0，否则什么都不做
     */
    private static double fixedToZeroIfNull(Double num) {
        return num != null ? num : 0.0;
    }

    /**
     * 计算系数向量的长度，2的整数幂方便FFT运算
     *
     * @param n 第一个多项式为n次多项式
     * @param m 第二个多项式为m次多项式
     * @return 长度，2的整数幂
     */
    private int getLength(int n, int m) {
        int len = 1;
        while (len <= n + m + 1) {
            len <<= 1;
        }
        return len;
    }

    /**
     * 预处理多项式，将其转换为Complex数组形式。
     * 如果len大于多项式项数，则用Complex.ZERO填充剩余位置。
     *
     * @param polynomial 多项式
     * @param len        目标数组长度
     * @return 长度为len的Complex数组
     */
    private static Complex[] pretreatment(Polynomial polynomial, int len) {
        Complex[] result = new Complex[len];
        Arrays.fill(result, Complex.ZERO);
        polynomial.terms.forEach((exp, coef) -> result[exp] = Complex.toComplex(coef));
        return result;
    }

    // /**
    //  * 递归版FFT，仅用作算法演示
    //  * 注意系数向量的长度必须为2的整数幂
    //  *
    //  * @param a 系数向量
    //  */
    // @Deprecated
    // private void fftRecursive(Complex[] a) {
    //     int n = a.length;
    //     if (a.length == 1) {
    //         return;
    //     }
    //
    //     int halfN = n >> 1;
    //     Complex[] even = new Complex[halfN];
    //     Complex[] odd = new Complex[halfN];
    //     for (int i = 0; i < halfN; ++i) {
    //         even[i] = a[i << 1];
    //         odd[i] = a[(i << 1) + 1];
    //     }
    //
    //     fftRecursive(even);
    //     fftRecursive(odd);
    //
    //     Complex wk; // 旋转因子初始值
    //     for (int k = 0; k < halfN; k++) {
    //         wk = unitRoot(n, k);
    //         a[k] = even[k].add(wk.multiply(odd[k]));
    //         a[k + halfN] = even[k].subtract(wk.multiply(odd[k]));
    //     }
    // }

    /**
     * FFT迭代版
     * 注意系数向量的长度必须为2的整数幂
     *
     * @param a         系数向量
     * @param isInverse 是否为逆FFT
     */
    private void fft(Complex[] a, boolean isInverse) {
        int n = a.length;
        int halfN = n >> 1;
        int[] pos = new int[n];

        // bit reversal
        pos[0] = 0; // 第0个元素倒位序已知
        for (int i = 1; i < n; ++i) {
            pos[i] = (pos[i >> 1] >> 1) + (i % 2) * halfN; // 求倒位序
        }
        for (int i = 0; i < n; ++i) {
            if (i < pos[i]) {
                // 根据倒位序原址交换
                Complex tmp = a[i];
                a[i] = a[pos[i]];
                a[pos[i]] = tmp;
            }
        }

        // Butterfly diagram
        for (int l = 2; l <= n; l <<= 1) {
            int halfL = l >> 1;
            for (int i = 0; i < n; i += l) {
                Complex wk = new Complex(1, 0);
                Complex w = unitRoot(l);
                if (isInverse) {
                    w = w.conjugate();
                }
                for (int j = 0; j < halfL; ++j) {
                    Complex x = a[i + j];
                    Complex y = a[i + j + halfL].multiply(wk);
                    a[i + j] = x.add(y);
                    a[i + j + halfL] = x.subtract(y);
                    wk = wk.multiply(w);
                }
            }
        }

        // For IFFT, divide by n
        if (isInverse) {
            for (int i = 0; i < n; i++) {
                a[i] = a[i].divide(n);
            }
        }
    }

    /**
     * 计算主n次单位根 (wn)^k 的k次幂，其中n是当前的子问题大小
     *
     * @param n 当前子问题的大小
     * @return (wn)^k
     */
    private Complex unitRoot(int n) {
        double angle = 2 * Math.PI / n;
        return new Complex(Math.cos(angle), -Math.sin(angle));
    }

    /**
     * 启动建造者模式
     *
     * @return Builder
     */
    @Deprecated
    public static CoefficientRepresentationPolynomialBuilder builder() {
        return new CoefficientRepresentationPolynomialBuilder();
    }

    /**
     * for builder
     */
    @Deprecated
    public static class CoefficientRepresentationPolynomialBuilder {
        private final Polynomial polynomial;

        public CoefficientRepresentationPolynomialBuilder() {
            this.polynomial = new Polynomial();
        }

        /**
         * 添加项
         *
         * @param coefficient 系数
         * @param exponent    指数
         */
        public CoefficientRepresentationPolynomialBuilder addTerm(double coefficient, int exponent) {
            polynomial.addTerm(coefficient, exponent);
            return this;
        }

        /**
         * 批量添加项
         *
         * @param coefficients 系数数组
         * @param exponents    指数数组
         */
        public CoefficientRepresentationPolynomialBuilder addTerms(double[] coefficients, int[] exponents) {
            polynomial.addTerms(coefficients, exponents);
            return this;
        }

        public Polynomial build() {
            return polynomial;
        }
    }

    @Override
    public String toString() {
        TreeSet<Integer> exponents = new TreeSet<>(Comparator.reverseOrder());
        exponents.addAll(terms.keySet());

        StringBuilder stringBuilder = new StringBuilder();
        boolean firstItem = true;
        for (Integer exponent : exponents) {
            double coefficient = terms.get(exponent);
            if (coefficient == 0) continue;

            if (!firstItem) {
                stringBuilder.append(coefficient > 0 ? " + " : " - ");
            }

            stringBuilder.append(itemToString(coefficient, exponent, firstItem));
            firstItem = false;
        }
        return stringBuilder.isEmpty() ? "0" : stringBuilder.toString();
    }

    private String itemToString(double coefficient, int exponent, boolean isFirst) {
        StringBuilder item = new StringBuilder();

        if (Math.abs(coefficient) != 1 || exponent == 0) {
            item.append(DigitUtils.doubleToString(coefficient, !isFirst));
        }

        if (exponent != 0) {
            item.append("x");
            if (exponent != 1) {
                item.append("^").append(DigitUtils.doubleToString(exponent, true));
            }
        }

        return item.toString();
    }
}
