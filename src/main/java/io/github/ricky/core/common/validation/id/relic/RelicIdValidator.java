package io.github.ricky.core.common.validation.id.relic;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

import static org.apache.commons.lang3.StringUtils.isBlank;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/10/12
 * @className RelicIdValidator
 * @desc
 */
public class RelicIdValidator implements ConstraintValidator<RelicId, String> {

    private static final Pattern PATTERN = Pattern.compile("^RLC[0-9]{17,19}$");

    @Override
    public boolean isValid(String relicId, ConstraintValidatorContext constraintValidatorContext) {
        if(isBlank(relicId)) {
            return true;
        }

        return isRelicId(relicId);
    }

    public static boolean isRelicId(String relicId) {
        return PATTERN.matcher(relicId).matches();
    }
}
