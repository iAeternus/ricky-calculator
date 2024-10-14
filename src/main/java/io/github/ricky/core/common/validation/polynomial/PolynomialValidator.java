package io.github.ricky.core.common.validation.polynomial;

import io.github.ricky.core.common.constants.RcRegexConstants;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

import static io.github.ricky.core.common.constants.RcRegexConstants.ILLEGAL_POLYNOMIAL_CHARACTERS_PATTERN;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/10/13
 * @className PolynomialValidator
 * @desc
 */
public class PolynomialValidator implements ConstraintValidator<Polynomial, String> {

    private static final Pattern PATTERN = Pattern.compile(RcRegexConstants.POLYNOMIAL_PATTERN);

    @Override
    public boolean isValid(String polynomial, ConstraintValidatorContext constraintValidatorContext) {
        if(StringUtils.isBlank(polynomial)) {
            return true;
        }

        return PATTERN.matcher(polynomial.replaceAll(ILLEGAL_POLYNOMIAL_CHARACTERS_PATTERN, "")).matches();
    }
}
