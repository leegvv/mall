package net.arver.mall.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.arver.mall.common.api.JsonResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 测试
 */
@Api(tags = "TestController")
@Controller
@RequestMapping("/test")
public class TestController {

    @ApiOperation(value = "Hello World!")
    @RequestMapping("hello")
    @ResponseBody
    public JsonResult<String> test() {
        return JsonResult.success("Hello World!");
    }
}
