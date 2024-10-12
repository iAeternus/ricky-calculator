package io.github.ricky.core.common.validation.id.relic;

import io.github.ricky.core.common.validation.number.DoubleRangeValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/10/12
 * @className RelicId
 * @desc
 */
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = RelicIdValidator.class)
@Documented
public @interface RelicId {

    String message() default "App ID format is incorrect.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
