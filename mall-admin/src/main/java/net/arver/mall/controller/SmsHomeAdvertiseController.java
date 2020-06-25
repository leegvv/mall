package net.arver.mall.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.arver.mall.common.api.JsonResult;
import net.arver.mall.common.api.PageResult;
import net.arver.mall.model.SmsHomeAdvertise;
import net.arver.mall.service.SmsHomeAdvertiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 首页轮播广告管理Controller
 */
@Api(tags = "SmsHomeAdvertiseController")
@RestController
@RequestMapping("/home/advertise")
public class SmsHomeAdvertiseController {

    @Autowired
    private SmsHomeAdvertiseService advertiseService;

    @ApiOperation("添加广告")
    @PostMapping("/create")
    public JsonResult create(@RequestBody final SmsHomeAdvertise advertise) {
        final int count = advertiseService.create(advertise);
        if (count > 0) {
            return JsonResult.success(count);
        }
        return JsonResult.failed();
    }

    @ApiOperation("删除广告")
    @PostMapping("/delete")
    public JsonResult delete(@RequestParam("ids") final List<Long> ids) {
        final int count = advertiseService.delete(ids);
        if (count > 0) {
            return JsonResult.success(count);
        }
        return JsonResult.failed();
    }

    @ApiOperation("修改上下线状态")
    @PostMapping("/update/status/{id}")
    public JsonResult updateStatus(@PathVariable final Long id, final Integer status) {
        final int count = advertiseService.updateStatus(id, status);
        if (count > 0) {
            return JsonResult.success(count);
        }
        return JsonResult.failed();
    }

    @ApiOperation("获取广告详情")
    @GetMapping("/{id}")
    public JsonResult<SmsHomeAdvertise> getItem(@PathVariable final Long id) {
        final SmsHomeAdvertise advertise = advertiseService.getItem(id);
        return JsonResult.success(advertise);
    }

    @ApiOperation("修改广告")
    @PostMapping("/update/{id}")
    public JsonResult update(@PathVariable final Long id, @RequestBody final SmsHomeAdvertise advertise) {
        final int count = advertiseService.update(id, advertise);
        if (count > 0) {
            return JsonResult.success(count);
        }
        return JsonResult.failed();
    }

    @ApiOperation("分页查询广告")
    @GetMapping("/list")
    public JsonResult<PageResult<SmsHomeAdvertise>> list(final String name,
                                                         final Integer type,
                                                         final String endTime,
                                                         @RequestParam(value = "pageNum", defaultValue = "1") final Integer pageNum,
                                                         @RequestParam(value = "pageSize", defaultValue = "5") final Integer pageSize) {
        final List<SmsHomeAdvertise> advertiseList = advertiseService.list(name, type, endTime, pageNum, pageSize);
        return JsonResult.success(PageResult.restPage(advertiseList));
    }
}
