package io.github.ricky.core.common.validation.attr;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/9/27
 * @className AttrRange
 * @desc 属性范围
 */
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AttrRangeValidator.class)
@Documented
public @interface AttrRange {

    double min() default 0.0;

    double max() default Double.MAX_VALUE;

    String message() default "The value must be between {min} and {max}.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
