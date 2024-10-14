package io.github.ricky.core.common.validation.polynomial;

import io.github.ricky.core.common.validation.id.relic.RelicIdValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/10/13
 * @className Polynomial
 * @desc
 */
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PolynomialValidator.class)
@Documented
public @interface Polynomial {

    String message() default "Polynomial format is incorrect.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
