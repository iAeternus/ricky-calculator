package io.github.ricky.apiTest.core.relic;

import io.github.ricky.apiTest.BaseApiTest;
import io.github.ricky.core.common.page.PagedList;
import io.github.ricky.core.relic.application.dto.CalculateScoreCommand;
import io.github.ricky.core.relic.application.dto.CalculateScoreResponse;
import io.github.ricky.core.relic.query.dto.RelicHistoryPageQuery;
import io.github.ricky.core.relic.query.dto.RelicHistoryResult;
import io.github.ricky.core.relic.query.dto.YieldWeightQuery;
import io.github.ricky.core.relic.query.dto.YieldWeightResult;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/10/8
 * @className RelicApi
 * @desc
 */
public class RelicApi {

    public static final String ROOT_URL = "/relic";

    public static Response calculateScoreRaw(CalculateScoreCommand command) {
        return BaseApiTest.given()
                .body(command)
                .when()
                .post(ROOT_URL + "/score");
    }

    public static CalculateScoreResponse calculateScore(CalculateScoreCommand command) {
        return calculateScoreRaw(command)
                .then()
                .statusCode(200)
                .extract()
                .as(CalculateScoreResponse.class);
    }

    public static Response fetchYieldWeightRaw(YieldWeightQuery query) {
        return BaseApiTest.given()
                .body(query)
                .when()
                .post(ROOT_URL + "/terms");
    }

    public static YieldWeightResult fetchYieldWeight(YieldWeightQuery query) {
        return fetchYieldWeightRaw(query)
                .then()
                .statusCode(200)
                .extract()
                .as(YieldWeightResult.class);
    }

    public static Response listRelicHistoryRaw(RelicHistoryPageQuery pageQuery) {
        return BaseApiTest.given()
                .body(pageQuery)
                .when()
                .post(ROOT_URL + "/list-relic-history");
    }

    public static PagedList<RelicHistoryResult> listRelicHistory(RelicHistoryPageQuery pageQuery) {
        return listRelicHistoryRaw(pageQuery)
                .then()
                .statusCode(200)
                .extract()
                .as(new TypeRef<>() {
                });
    }

}
