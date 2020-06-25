package net.arver.mall.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.arver.mall.common.api.JsonResult;
import net.arver.mall.common.api.PageResult;
import net.arver.mall.model.SmsHomeBrand;
import net.arver.mall.service.SmsHomeBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "SmsHomeBrandController")
@RestController
@RequestMapping("/home/brand")
public class SmsHomeBrandController {

    @Autowired
    private SmsHomeBrandService homeBrandService;

    @ApiOperation("添加首页推荐商品")
    @PostMapping("/create")
    public JsonResult create(@RequestBody List<SmsHomeBrand> homeBrandList) {
        final int count = homeBrandService.create(homeBrandList);
        if (count > 0) {
            return JsonResult.success(count);
        }
        return JsonResult.failed();
    }

    @ApiOperation("修改品牌排序")
    @PostMapping("/update/sort/{id}")
    public JsonResult updateSort(@PathVariable final Long id, final Integer sort) {
        final int count = homeBrandService.updateSort(id, sort);
        if (count > 0) {
            return JsonResult.success(count);
        }
        return JsonResult.failed();
    }

    @ApiOperation("批量删除推荐品牌")
    @PostMapping("/delete")
    public JsonResult delete(@RequestParam("ids") final List<Long> ids) {
        final int count = homeBrandService.delete(ids);
        if (count > 0) {
            return JsonResult.success(count);
        }
        return JsonResult.failed();
    }

    @ApiOperation("批量修改推荐状态")
    @PostMapping("/update/recommendStatus")
    public JsonResult updateRecommendStatus(@RequestParam("ids") final List<Long> ids,
                                            @RequestParam final Integer recommendStatus) {
        final int count = homeBrandService.updateRecommendStatus(ids, recommendStatus);
        if (count > 0) {
            return JsonResult.success(count);
        }
        return JsonResult.failed();
    }

    @ApiOperation("分页查询推荐品牌")
    @GetMapping("/list")
    public JsonResult<PageResult<SmsHomeBrand>> list(final String brandName, final Integer recommendStatus,
                                                     @RequestParam(value = "pageNum", defaultValue = "1") final Integer pageNum,
                                                     @RequestParam(value = "pageSize", defaultValue = "5") final Integer pageSize) {
        final List<SmsHomeBrand> homeBrandList = homeBrandService.list(brandName, recommendStatus, pageNum, pageSize);
        return JsonResult.success(PageResult.restPage(homeBrandList));
    }


}
