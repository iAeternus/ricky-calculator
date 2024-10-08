package io.github.ricky.apiTest.core.relic;

import io.github.ricky.apiTest.BaseApiTest;
import io.github.ricky.core.relic.application.dto.CalculateScoreCommand;
import io.github.ricky.core.relic.application.dto.CalculateScoreResponse;
import org.junit.jupiter.api.Test;

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


    }

}
