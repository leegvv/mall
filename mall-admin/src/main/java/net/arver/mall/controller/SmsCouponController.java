package net.arver.mall.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.arver.mall.common.api.JsonResult;
import net.arver.mall.common.api.PageResult;
import net.arver.mall.dto.SmsCouponParam;
import net.arver.mall.model.SmsCoupon;
import net.arver.mall.service.SmsCouponService;
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
 * 优惠券管理Controller
 */
@Api(tags = "SmsCouponController")
@RestController
@RequestMapping("/coupon")
public class SmsCouponController {

    @Autowired
    private SmsCouponService couponService;

    @ApiOperation("添加优惠券")
    @PostMapping("/create")
    public JsonResult create(@RequestBody final SmsCouponParam couponParam) {
        final int count = couponService.create(couponParam);
        if (count > 0) {
            return JsonResult.success(count);
        }
        return JsonResult.failed();
    }

    @ApiOperation("修改优惠券")
    @PostMapping("/update/{id}")
    public JsonResult update(@PathVariable final Long id, @RequestBody final SmsCouponParam couponParam) {
        final int count = couponService.update(id, couponParam);
        if (count > 0) {
            return JsonResult.success(count);
        }
        return JsonResult.failed();
    }

    @ApiOperation("删除优惠券")
    @PostMapping("/delete/{id}")
    public JsonResult delete(@PathVariable final Long id) {
        final int count = couponService.delete(id);
        if (count > 0) {
            return JsonResult.success(count);
        }
        return JsonResult.failed();
    }

    @ApiOperation("根据优惠券名称和类型分页获取优惠券列表")
    @GetMapping("/list")
    public JsonResult<PageResult<SmsCoupon>> list(final String name, final Integer type,
                                                  @RequestParam(value = "pageNum", defaultValue = "1") final Integer pageNum,
                                                  @RequestParam(value = "pageSize", defaultValue = "5")final Integer pageSize) {
        final List<SmsCoupon> couponList = couponService.list(name, type, pageNum, pageSize);
        return JsonResult.success(PageResult.restPage(couponList));
    }

    @ApiOperation("获取单个优惠券的详细信息")
    @GetMapping("/{id}")
    public JsonResult<SmsCouponParam> getItem(@PathVariable final Long id) {
        final SmsCouponParam couponParam = couponService.getItem(id);
        return JsonResult.success(couponParam);
    }
}
