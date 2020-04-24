package net.arver.mall.common.api;

public class JsonResult<T> {
    private long code;
    private String message;
    private T data;

    protected JsonResult(){}

    protected JsonResult(final long code, final String message, final T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 成功返回结果
     * @param data
     * @param <T>
     * @return
     */
    public static <T> JsonResult<T> success(final T data) {
        return new JsonResult<T>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), data);
    }

    public static <T> JsonResult<T> success(final T data, final String message) {
        return new JsonResult<>(ResultCode.SUCCESS.getCode(), message, data);
    }

    /**
     * 失败返回结果
     * @param errorCode
     * @param <T>
     * @return
     */
    public static <T> JsonResult<T> failed(final IErrorCode errorCode) {
        return new JsonResult<>(errorCode.getCode(), errorCode.getMessage(), null);
    }

    public static <T> JsonResult<T> failed(final IErrorCode errorCode, final String message) {
        return new JsonResult<>(errorCode.getCode(), message, null);
    }

    public static <T> JsonResult<T> failed(final String message) {
        return new JsonResult<>(ResultCode.FAILED.getCode(), message, null);
    }

    public static <T> JsonResult<T> failed() {
        return failed(ResultCode.FAILED);
    }

    public static <T> JsonResult<T> validateFailed() {
        return failed(ResultCode.VALIDATE_FAILED);
    }

    public static <T> JsonResult<T> validateFailed(final String message) {
        return new JsonResult<>(ResultCode.VALIDATE_FAILED.getCode(), message, null);
    }

    /**
     * 为登录返回结果
     * @param data
     * @param <T>
     * @return
     */
    public static <T> JsonResult<T> unauthorized(final T data) {
        return new JsonResult<>(ResultCode.UNAUTHORIZED.getCode(), ResultCode.UNAUTHORIZED.getMessage(), data);
    }

    /**
     * 未授权返回结果
     * @param data
     * @param <T>
     * @return
     */
    public static <T> JsonResult<T> forbidden(final T data) {
        return new JsonResult<>(ResultCode.FORBIDDEN.getCode(), ResultCode.FORBIDDEN.getMessage(), data);
    }


    public long getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }
}
