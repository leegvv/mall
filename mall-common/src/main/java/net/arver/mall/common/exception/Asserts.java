package net.arver.mall.common.exception;

import net.arver.mall.common.api.IErrorCode;

/**
 * 断言处理类，用于抛出各种API异常
 */
public class Asserts {

    public static void fail(final String message) {
        throw new ApiException(message);
    }

    public static void fail(final IErrorCode errorCode) {
        throw new ApiException(errorCode);
    }
}
