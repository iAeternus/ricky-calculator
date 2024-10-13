package io.github.ricky.core.common.utils;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/9/19
 * @className DigitUtils
 * @desc
 */
public class DigitUtils {

    /**
     * 误差阈值
     */
    public static final double EPS = 1e-9;

    /**
     * 最大保留多少位小数
     */
    public static final int SCALE = 6;

    /**
     * 比较两个浮点数
     *
     * @param num1 第一个浮点数
     * @param num2 第二个浮点数
     * @return 若两个浮点数在误差阈值内相等，返回0.0
     * 若num1 < num2，返回负数
     * 若num1 > num2，返回正数
     */
    @Deprecated
    public static double cmp(double num1, double num2) {
        double cmp = num1 - num2;
        return Math.abs(cmp) < EPS ? 0.0 : cmp;
    }

    /**
     * 检查两个浮点数是否在误差阈值内为0
     *
     * @param num 浮点数
     * @return true=为0 false=不为0
     */
    public static boolean isZero(double num) {
        return Math.abs(num - 0.0) < EPS;
    }

    /**
     * 检查两个浮点数是否在误差阈值内为0
     *
     * @param num 浮点数
     * @return true=不为0 false=为0
     */
    public static boolean isNotZero(double num) {
        return Math.abs(num - 0.0) >= EPS;
    }

    /**
     * 检查两个浮点数是否在误差阈值内相等
     *
     * @param num1 第一个浮点数
     * @param num2 第二个浮点数
     * @return true=相等 false=不相等
     */
    public static boolean isEqual(double num1, double num2) {
        return Math.abs(num1 - num2) < EPS;
    }

    /**
     * 检查两个浮点数是否在误差阈值内不相等
     *
     * @param num1 第一个浮点数
     * @param num2 第二个浮点数
     * @return true=不相等 false=相等
     */
    public static boolean isNotEqual(double num1, double num2) {
        return Math.abs(num1 - num2) >= EPS;
    }

    /**
     * 比较两个浮点数的大小
     *
     * @param num1 第一个浮点数
     * @param num2 第二个浮点数
     * @return 如果 num1 < num2，则返回 -1；如果 num1 == num2（在误差阈值内），则返回 0；如果 num1 > num2，则返回 1
     */
    public static int compare(double num1, double num2) {
        double diff = num1 - num2;
        if (Math.abs(diff) < EPS) {
            return 0;
        } else if (diff < 0) {
            return -1;
        } else {
            return 1;
        }
    }

    /**
     * 获取整数部分位数
     *
     * @param num 浮点数
     * @return 整数部分位数
     */
    public static int getIntegerPartDigit(double num) {
        int digit = 0;
        int t = (int) Math.floor(num);
        while (t > 0) {
            digit++;
            t /= 10;
        }
        return digit;
    }

    /**
     * 获取小数部分位数
     *
     * @param num 浮点数
     * @return 小数部分位数
     */
    public static int getDecimalPartDigit(double num) {
        int digit = 0;
        double t = num - (int) Math.floor(num);
        while (t - Math.floor(t) > EPS) {
            digit++;
            t *= 10;
        }
        return digit;
    }

    /**
     * 将double转为String
     *
     * @param num 浮点数
     * @return 字符串
     */
    public static String doubleToString(double num, boolean abs) {
        // 处理特殊值
        if (Double.isNaN(num)) return "NaN";
        if (Double.isInfinite(num)) return num > 0 ? "Infinity" : "-Infinity";
        if (Math.abs(num) < EPS) return "0";

        int digit = getDecimalPartDigit(num);
        String formatString;
        formatString = "%." + Math.min(digit, SCALE) + "f";
        return String.format(formatString, (abs ? Math.abs(num) : num));
    }

}
