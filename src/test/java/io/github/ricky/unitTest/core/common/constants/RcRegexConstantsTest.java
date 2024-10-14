package io.github.ricky.unitTest.core.common.constants;

import io.github.ricky.core.common.constants.RcRegexConstants;
import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/10/13
 * @className RcRegexConstantsTest
 * @desc
 */
class RcRegexConstantsTest {

    @Test
    public void test_polynomial_pattern() {
        // Given
        String value = "-x^2+2x+1";

        // When
        boolean matches = Pattern.compile(RcRegexConstants.POLYNOMIAL_PATTERN).matcher(value).matches();

        // Then
        assertTrue(matches);
    }

}