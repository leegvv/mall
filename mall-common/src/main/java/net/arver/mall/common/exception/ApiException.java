package net.arver.mall.common.exception;

import net.arver.mall.common.api.IErrorCode;

/**
 * 自定义API异常
 */
public class ApiException extends RuntimeException {

    private IErrorCode errorCode;

    public ApiException(final IErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public ApiException(final String message) {
        super(message);
    }

    public ApiException(final Throwable cause) {
        super(cause);
    }

    public ApiException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public IErrorCode getErrorCode() {
        return errorCode;
    }
}
