package io.github.ricky.core.relic;

import io.github.ricky.core.relic.application.RelicApplicationService;
import io.github.ricky.core.relic.application.dto.CalculateScoreCommand;
import io.github.ricky.core.relic.application.dto.CalculateScoreResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/9/27
 * @className RelicController
 * @desc
 */
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/relic")
public class RelicController {

    private final RelicApplicationService relicApplicationService;

    @PostMapping("/score")
    public CalculateScoreResponse calculateScore(@RequestBody CalculateScoreCommand command) {
        return relicApplicationService.calculateScore(command);
    }

}
