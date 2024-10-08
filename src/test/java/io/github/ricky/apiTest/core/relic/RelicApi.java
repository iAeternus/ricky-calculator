package io.github.ricky.apiTest.core.relic;

import io.github.ricky.apiTest.BaseApiTest;
import io.github.ricky.core.relic.application.dto.CalculateScoreCommand;
import io.github.ricky.core.relic.application.dto.CalculateScoreResponse;
import io.restassured.response.Response;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/10/8
 * @className RelicApi
 * @desc
 */
public class RelicApi {

    public static Response calculateScoreRaw(CalculateScoreCommand command) {
        return BaseApiTest.given()
                .body(command)
                .when()
                .post("/relic/score");
    }

    public static CalculateScoreResponse calculateScore(CalculateScoreCommand command) {
        return calculateScoreRaw(command)
                .then()
                .statusCode(200)
                .extract()
                .as(CalculateScoreResponse.class);
    }

}
