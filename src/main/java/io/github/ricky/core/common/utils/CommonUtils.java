package io.github.ricky.core.common.utils;

import static org.apache.commons.lang3.StringUtils.isBlank;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/9/27
 * @className CommonUtils
 * @desc 校验工具类
 */
public class CommonUtils {

    public static String nullIfBlank(String string) {
        if (isBlank(string)) {
            return null;
        }

        return string;
    }

    public static String requireNonBlank(String str, String message) {
        if (isBlank(str)) {
            throw new IllegalArgumentException(message);
        }
        return str;
    }

}
