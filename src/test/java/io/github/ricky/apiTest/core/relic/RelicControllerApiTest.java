package io.github.ricky.apiTest.core.relic;

import io.github.ricky.apiTest.BaseApiTest;
import io.github.ricky.core.common.page.PagedList;
import io.github.ricky.core.relic.application.dto.CalculateScoreCommand;
import io.github.ricky.core.relic.application.dto.CalculateScoreResponse;
import io.github.ricky.core.relic.domain.Relic;
import io.github.ricky.core.relic.query.dto.RelicHistoryPageQuery;
import io.github.ricky.core.relic.query.dto.RelicHistoryResult;
import io.github.ricky.core.relic.query.dto.YieldWeightQuery;
import io.github.ricky.core.relic.query.dto.YieldWeightResult;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/10/8
 * @className RelicControllerApiTest
 * @desc
 */
public class RelicControllerApiTest extends BaseApiTest {

    @Test
    public void calculate_score_should_save_relic() {
        // Given
        CalculateScoreCommand command = CalculateScoreCommand.builder()
                .belongTo("胡桃")
                .isCritical(false)
                .position(0)
                .critChance(7.4)
                .critDamage(19.4)
                .energyRecharge(4.5)
                .healthPercentage(10.5)
                .build();

        // When
        CalculateScoreResponse response = RelicApi.calculateScore(command);

        // Then
        assertEquals(45.37, response.getScore());

        Relic relic = relicRepository.byId(response.getId());
        System.out.println(relic);
    }

    @Test
    public void should_fetch_yield_weight() {
        // Given
        YieldWeightQuery query = YieldWeightQuery.builder()
                .belongTo("胡桃")
                .build();

        // When
        YieldWeightResult result = RelicApi.fetchYieldWeight(query);

        // Then
        assertEquals(result, YieldWeightResult.builder()
                .belongTo("胡桃")
                .critChance(1)
                .critDamage(1)
                .elementalMastery(0.75)
                .attackPercentage(0.5)
                .attack(0.5)
                .healthPercentage(0.8)
                .health(0.8)
                .build());
    }

    @Test
    public void should_list_relic_history() {
        // Given
        RelicHistoryPageQuery pageQuery = RelicHistoryPageQuery.builder()
                .belongTo("胡桃")
                .position(0)
                .pageIndex(1)
                .pageSize(10)
                .build();

        // When
        PagedList<RelicHistoryResult> pagedList = RelicApi.listRelicHistory(pageQuery);

        // Then
        List<Relic> relics = relicRepository.listAll();
        assertEquals(pagedList.size(), Math.min(10, relics.size()));
    }

}
