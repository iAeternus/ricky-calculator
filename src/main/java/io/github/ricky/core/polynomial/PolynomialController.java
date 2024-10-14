package io.github.ricky.core.polynomial;

import io.github.ricky.core.polynomial.application.PolynomialApplicationService;
import io.github.ricky.core.polynomial.application.dto.CalculateResultCommand;
import io.github.ricky.core.polynomial.application.dto.CalculateResultResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/10/12
 * @className PolynomialController
 * @desc
 */
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/polynomial")
public class PolynomialController {

    private final PolynomialApplicationService polynomialApplicationService;

    @PostMapping("/result")
    public CalculateResultResponse calculateResult(@RequestBody @Valid CalculateResultCommand command) {
        return polynomialApplicationService.calculateResult(command);
    }

}
