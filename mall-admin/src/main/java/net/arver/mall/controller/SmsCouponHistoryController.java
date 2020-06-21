package net.arver.mall.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.arver.mall.common.api.JsonResult;
import net.arver.mall.common.api.PageResult;
import net.arver.mall.model.SmsCouponHistory;
import net.arver.mall.service.SmsCouponHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "SmsCouponHistoryController")
@RestController
@RequestMapping("/couponHistory")
public class SmsCouponHistoryController {

    @Autowired
    private SmsCouponHistoryService historyService;

    @ApiOperation("根据优惠券id，使用状态，订单编号分页获取领取记录")
    @GetMapping("/list")
    public JsonResult<PageResult<SmsCouponHistory>> list(final Long couponId, final Integer useStatus, final String orderSn,
                                                         @RequestParam(value = "pageNum", defaultValue = "1")final Integer pageNum,
                                                         @RequestParam(value = "pageSize", defaultValue = "5") final Integer pageSize) {
        final List<SmsCouponHistory> historyList = historyService.list(couponId, useStatus, orderSn, pageNum, pageSize);
        return JsonResult.success(PageResult.restPage(historyList));
    }
}
