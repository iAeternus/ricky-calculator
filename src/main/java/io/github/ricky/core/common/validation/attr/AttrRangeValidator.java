package io.github.ricky.core.common.validation.attr;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/9/27
 * @className AttrRangeValidator
 * @desc 属性范围校验器
 */
public class AttrRangeValidator implements ConstraintValidator<AttrRange, Double> {

    private double min;
    private double max;

    @Override
    public void initialize(AttrRange constraintAnnotation) {
        this.min = constraintAnnotation.min();
        this.max = constraintAnnotation.max();
    }

    @Override
    public boolean isValid(Double attr, ConstraintValidatorContext constraintValidatorContext) {
        return attr >= min && attr <= max;
    }
}
