package net.arver.mall.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.arver.mall.common.api.JsonResult;
import net.arver.mall.common.api.PageResult;
import net.arver.mall.model.SmsFlashPromotion;
import net.arver.mall.service.SmsFlashPromotionService;
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
 * 限时购活动Controller
 */
@Api(tags = "SmsFlashPromotionController")
@RestController
@RequestMapping("/flash")
public class SmsFlashPromotionController {
    @Autowired
    private SmsFlashPromotionService flashPromotionService;

    @ApiOperation("添加活动")
    @PostMapping("/create")
    public JsonResult create(@RequestBody final SmsFlashPromotion flashPromotion) {
        final int count = flashPromotionService.create(flashPromotion);
        if (count > 0) {
            return JsonResult.success(count);
        }
        return JsonResult.failed();
    }

    @ApiOperation("编辑活动信息")
    @PostMapping("/update/{id}")
    public JsonResult update(@PathVariable final Long id, @RequestBody final SmsFlashPromotion flashPromotion) {
        final int count = flashPromotionService.update(id, flashPromotion);
        if (count > 0) {
            return JsonResult.success(count);
        }
        return JsonResult.failed();
    }

    @ApiOperation("删除活动信息")
    @PostMapping("/delete/{id}")
    public JsonResult delete(@PathVariable final Long id) {
        final int count = flashPromotionService.delete(id);
        if (count > 0) {
            return JsonResult.success(count);
        }
        return JsonResult.failed();
    }

    @ApiOperation("修改上下线状态")
    @PostMapping("/update/status/{id}")
    public JsonResult updateStatus(@PathVariable final Long id, final Integer status) {
        final int count = flashPromotionService.updateStatus(id, status);
        if (count > 0) {
            return JsonResult.success(count);
        }
        return JsonResult.failed();
    }

    @ApiOperation("获取活动详情")
    @GetMapping("/update/status/{id}")
    public JsonResult getItem(@PathVariable final Long id) {
        final SmsFlashPromotion flashPromotion = flashPromotionService.getItem(id);
        return JsonResult.success(flashPromotion);
    }

    @ApiOperation("根据活动名称分页查询")
    @GetMapping("/list")
    public JsonResult getItems(final String keyword,
                               @RequestParam(value = "pageNum", defaultValue = "1") final Integer pageNum,
                               @RequestParam(value = "pageSize", defaultValue = "5") final Integer pageSize) {
        final List<SmsFlashPromotion> flashPromotionList = flashPromotionService.list(keyword, pageNum, pageSize);
        return JsonResult.success(PageResult.restPage(flashPromotionList));
    }

}
