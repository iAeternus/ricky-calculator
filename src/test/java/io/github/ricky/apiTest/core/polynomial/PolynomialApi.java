package io.github.ricky.apiTest.core.polynomial;

import io.github.ricky.apiTest.BaseApiTest;
import io.github.ricky.core.polynomial.application.dto.CalculateResultCommand;
import io.github.ricky.core.polynomial.application.dto.CalculateResultResponse;
import io.restassured.response.Response;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/10/13
 * @className PolynomialApi
 * @desc
 */
public class PolynomialApi {

    private static final String ROOT_URL = "/polynomial";

    public static Response calculateResultRaw(CalculateResultCommand command) {
        return BaseApiTest.given()
                .body(command)
                .when()
                .post(ROOT_URL + "/result");
    }

    public static CalculateResultResponse calculateResult(CalculateResultCommand command) {
        return calculateResultRaw(command)
                .then()
                .statusCode(200)
                .extract()
                .as(CalculateResultResponse.class);
    }

}
