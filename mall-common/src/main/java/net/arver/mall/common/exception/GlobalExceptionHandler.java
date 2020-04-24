package net.arver.mall.common.exception;

import net.arver.mall.common.api.JsonResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常处理
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = ApiException.class)
    public JsonResult handle(final ApiException e) {
        if (e.getErrorCode() != null) {
            return JsonResult.failed(e.getErrorCode());
        }
        return JsonResult.failed(e.getMessage());
    }
}
