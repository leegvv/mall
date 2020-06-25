package net.arver.mall.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.arver.mall.common.api.JsonResult;
import net.arver.mall.common.api.PageResult;
import net.arver.mall.model.SmsHomeNewProduct;
import net.arver.mall.service.SmsHomeNewProductService;
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
 * 首页新品管理Controller
 */
@Api(tags = "SmsHomeNewProductController")
@RestController
@RequestMapping("/home/newProduct")
public class SmsHomeNewProductController {

    @Autowired
    private SmsHomeNewProductService homeNewProductService;

    @ApiOperation("添加首页推荐品牌")
    @PostMapping("/create")
    public JsonResult create(@RequestBody final List<SmsHomeNewProduct> newProductList) {
        final int count = homeNewProductService.create(newProductList);
        if (count > 0) {
            return JsonResult.success(count);
        }
        return JsonResult.failed();
    }

    @ApiOperation("修改推荐排序")
    @PostMapping("/update/sort/{id}")
    public JsonResult updateSort(@PathVariable final Long id, final Integer sort) {
        final int count = homeNewProductService.updateSort(id, sort);
        if (count > 0) {
            return JsonResult.success(count);
        }
        return JsonResult.failed();
    }

    @ApiOperation("批量删除推荐")
    @PostMapping("/delete")
    public JsonResult delete(@RequestParam("ids") final List<Long> ids) {
        final int count = homeNewProductService.delete(ids);
        if (count > 0) {
            return JsonResult.success(count);
        }
        return JsonResult.failed();
    }

    @ApiOperation("批量修改推荐状态")
    @PostMapping("/update/recommendStatus")
    public JsonResult updateRecommendStatus(@RequestParam("ids") final List<Long> ids, @RequestParam final Integer recommendStatus) {
        final int count = homeNewProductService.updateRecommendStatus(ids, recommendStatus);
        if (count > 0) {
            return JsonResult.success(count);
        }
        return JsonResult.failed();
    }

    @ApiOperation("分页查询推荐")
    @GetMapping("/list")
    public JsonResult<PageResult<SmsHomeNewProduct>> list(final String productName,
                                                         final Integer recommendStatus,
                                                         @RequestParam(value = "pageNum", defaultValue = "1") final Integer pageNum,
                                                         @RequestParam(value = "pageSize", defaultValue = "5") final Integer pageSize) {
        final List<SmsHomeNewProduct> homeNewProductList =
            homeNewProductService.list(productName, recommendStatus, pageNum, pageSize);
        return JsonResult.success(PageResult.restPage(homeNewProductList));
    }
}
