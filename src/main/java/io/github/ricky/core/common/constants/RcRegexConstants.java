package io.github.ricky.core.common.constants;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/10/13
 * @className RcRegexConstants
 * @desc
 */
public interface RcRegexConstants {

    String POLYNOMIAL_PATTERN = "^(([-+]([1-9][0-9]*)(\\*x(\\^[+-]?([1-9][0-9]*))?))|(([1-9][0-9]*)\\*(x(\\^[+-]?([1-9][0-9]*))?))|([-+](x(\\^[+-]?([1-9][0-9]*))?))|([-+]([1-9][0-9]*))|(([1-9][0-9]*))|((x(\\^[+-]?([1-9][0-9]*))?)))+$";
    String POLYNOMIAL_TERM_PATTERN = "([+-]?\\d*(?:\\.\\d+)?)(?:\\s*x\\s*\\^\\s*(\\d+))?";
    String ILLEGAL_POLYNOMIAL_CHARACTERS_PATTERN = "[^0-9x+\\-^]";
    String POLYNOMIAL_SPLIT_PATTERN = "(?=[+-])";

}
