package io.github.ricky.apiTest.core.relic;

import io.github.ricky.apiTest.BaseApiTest;
import io.github.ricky.core.common.exception.RcException;
import io.github.ricky.core.common.page.PageQuery;
import io.github.ricky.core.common.page.PagedList;
import io.github.ricky.core.relic.application.dto.CalculateScoreCommand;
import io.github.ricky.core.relic.application.dto.CalculateScoreResponse;
import io.github.ricky.core.relic.domain.Relic;
import io.github.ricky.core.relic.domain.RelicAttr;
import io.github.ricky.core.relic.domain.RelicPositionEnum;
import io.github.ricky.core.relic.query.dto.RelicHistoryPageQuery;
import io.github.ricky.core.relic.query.dto.RelicHistoryResult;
import io.github.ricky.core.relic.query.dto.YieldWeightQuery;
import io.github.ricky.core.relic.query.dto.YieldWeightResult;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.github.ricky.core.common.page.PageQuery.MIN_PAGE_SIZE;
import static org.junit.jupiter.api.Assertions.*;

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
    public void should_delete_relic_by_id() {
        // Given
        Relic relic = new Relic("胡桃", false, RelicPositionEnum.FLOWER_OF_LIFE, RelicAttr.builder()
                .critChance(7.4)
                .critDamage(19.4)
                .energyRecharge(4.5)
                .healthPercentage(10.5)
                .build());
        relicRepository.save(relic);
        String id = relic.getId();

        // When
        RelicApi.deleteById(id);

        // Then
        assertThrows(RcException.class, () -> relicRepository.byId(id));
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
                .scoreGt(45.0)
                .pageIndex(1)
                .pageSize(MIN_PAGE_SIZE)
                .build();

        // When
        PagedList<RelicHistoryResult> pagedList = RelicApi.listRelicHistory(pageQuery);

        // Then
        List<Relic> relics = relicRepository.listAll();
        assertEquals(pagedList.size(), Math.min(MIN_PAGE_SIZE, relics.size()));
    }

}
