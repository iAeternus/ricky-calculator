package io.github.ricky.core.relic;

import io.github.ricky.core.common.page.PagedList;
import io.github.ricky.core.relic.application.RelicApplicationService;
import io.github.ricky.core.relic.application.dto.CalculateScoreCommand;
import io.github.ricky.core.relic.application.dto.CalculateScoreResponse;
import io.github.ricky.core.relic.query.RelicQueryService;
import io.github.ricky.core.relic.query.dto.RelicHistoryPageQuery;
import io.github.ricky.core.relic.query.dto.RelicHistoryResult;
import io.github.ricky.core.relic.query.dto.YieldWeightQuery;
import io.github.ricky.core.relic.query.dto.YieldWeightResult;
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
    private final RelicQueryService relicQueryService;

    @PostMapping("/score")
    public CalculateScoreResponse calculateScore(@RequestBody @Valid CalculateScoreCommand command) {
        return relicApplicationService.calculateScore(command);
    }

    @PostMapping("/terms")
    public YieldWeightResult fetchYieldWeight(@RequestBody @Valid YieldWeightQuery query) {
        return relicQueryService.fetchYieldWeight(query);
    }

    @PostMapping("/list-relic-history")
    public PagedList<RelicHistoryResult> listRelicHistory(@RequestBody @Valid RelicHistoryPageQuery pageQuery) {
        return relicQueryService.listRelicHistory(pageQuery);
    }

}
