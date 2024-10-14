package io.github.ricky.core.polynomial.application.dto;

import io.github.ricky.core.common.marker.Command;
import io.github.ricky.core.common.validation.polynomial.Polynomial;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/10/13
 * @className CalculateResultCommand
 * @desc
 */
@Value
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CalculateResultCommand implements Command {

    @NotNull
    @Polynomial
    String polynomial;

    @NotNull
    Double x;

}
