package net.arver.mall.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.arver.mall.common.api.JsonResult;
import net.arver.mall.dao.SmsFlashPromotionSessionDetail;
import net.arver.mall.model.SmsFlashPromotionSession;
import net.arver.mall.service.SmsFlashPromotionSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 限时购场次管理
 */
@Api(tags = "SmsFlashPromotionSessionController")
@RestController
@RequestMapping("/flashSession")
public class SmsFlashPromotionSessionController {

    @Autowired
    private SmsFlashPromotionSessionService flashPromotionSessionService;

    @ApiOperation("添加场次")
    @PostMapping("/create")
    public JsonResult create(@RequestBody final SmsFlashPromotionSession promotionSession) {
        final int count = flashPromotionSessionService.create(promotionSession);
        if (count > 0) {
            return JsonResult.success(count);
        }
        return JsonResult.failed();
    }

    @ApiOperation("修改场次")
    @PostMapping(value = "/update/{id}")
    public JsonResult update(@PathVariable final Long id, @RequestBody final SmsFlashPromotionSession promotionSession) {
        final int count = flashPromotionSessionService.update(id, promotionSession);
        if (count > 0) {
            return JsonResult.success(count);
        }
        return JsonResult.failed();
    }

    @ApiOperation("修改启用状态")
    @PostMapping("/update/status/{id}")
    public JsonResult updateStatus(@PathVariable final Long id, final Integer status) {
        final int count = flashPromotionSessionService.updateStatus(id, status);
        if (count > 0) {
            return JsonResult.success(count);
        }
        return JsonResult.failed();
    }

    @ApiOperation("删除场次")
    @PostMapping("/delete/{id}")
    public JsonResult delete(@PathVariable final Long id) {
        final int count = flashPromotionSessionService.delete(id);
        if (count > 0) {
            return JsonResult.success(count);
        }
        return JsonResult.failed();
    }

    @ApiOperation("获取场次详情")
    @GetMapping("/{id}")
    public JsonResult<SmsFlashPromotionSession> getItem(@PathVariable final Long id) {
        final SmsFlashPromotionSession promotionSession = flashPromotionSessionService.getItem(id);
        return JsonResult.success(promotionSession);
    }

    @ApiOperation("获取全部场次")
    @GetMapping("/list")
    public JsonResult<List<SmsFlashPromotionSession>> list() {
        final List<SmsFlashPromotionSession> promotionSessionList = flashPromotionSessionService.list();
        return JsonResult.success(promotionSessionList);
    }

    @ApiOperation("获取全部可选场次及其数量")
    @GetMapping("/selectList")
    public JsonResult<List<SmsFlashPromotionSessionDetail>> selectList(final Long flashPromotionId) {
        final List<SmsFlashPromotionSessionDetail> detailList = flashPromotionSessionService.selectList(flashPromotionId);
        return JsonResult.success(detailList);
    }

}
