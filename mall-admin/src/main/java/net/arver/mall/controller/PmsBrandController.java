package net.arver.mall.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.arver.mall.common.api.JsonResult;
import net.arver.mall.common.api.PageResult;
import net.arver.mall.dto.PmsBrandParam;
import net.arver.mall.model.PmsBrand;
import net.arver.mall.service.PmsBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 品牌功能Controller
 */
@Api(tags = "PmsBrandController", description = "商品品牌管理")
@RestController
@RequestMapping("/brand")
public class PmsBrandController {
    @Autowired
    private PmsBrandService brandService;

    @ApiOperation(value = "获取全部品牌列表")
    @GetMapping(value = "/listAll")
    public JsonResult<List<PmsBrand>> getList() {
        return JsonResult.success(brandService.listAllBrand());
    }

    @ApiOperation("添加品牌")
    @PostMapping("/create")
    public JsonResult create(@Validated @RequestBody final PmsBrandParam pmsBrandParam, BindResult result) {
        final int count = brandService.createBrand(pmsBrandParam);
        if (count == 1) {
            return JsonResult.success(count);
        } else {
            return JsonResult.failed();
        }
    }

    @ApiOperation("更新品牌")
    @PostMapping("/update/{id}")
    public JsonResult update(@PathVariable("id") final Long id, @Validated @RequestBody final PmsBrandParam pmsBrandParam,
                             final BindResult result) {
        final int count = brandService.updateBrand(id, pmsBrandParam);
        if (count == 1) {
            return JsonResult.success(count);
        } else {
            return JsonResult.failed();
        }
    }

    @ApiOperation("删除品牌")
    @GetMapping("/delete/{id}")
    public JsonResult delete(@PathVariable("id") final Long id) {
        final int count = brandService.deleteBrand(id);
        if (count == 1) {
            return JsonResult.success(count);
        } else {
            return JsonResult.failed();
        }
    }

    @ApiOperation("根据品牌名称分页获取品牌列表")
    @GetMapping("/list")
    public JsonResult getList(@RequestParam(value = "keyword", required = false) final String keyword,
                              @RequestParam(value = "pageNum", defaultValue = "1") final Integer pageNum,
                              @RequestParam(value = "pageSize", defaultValue = "5") final Integer pageSize) {
        final List<PmsBrand> brandList = brandService.listBrand(keyword, pageNum, pageSize);
        return JsonResult.success(PageResult.restPage(brandList));
    }

    @ApiOperation("根据编号查询品牌信息")
    @GetMapping("/{id}")
    public JsonResult<PmsBrand> getItem(@PathVariable("id")final Long id) {
        return JsonResult.success(brandService.getBrand(id));
    }

    @ApiOperation("批量删除品牌")
    @PostMapping("/delete/batch")
    public JsonResult deleteBatch(@RequestParam("ids") final List<Long> ids) {
        final int count = brandService.deleteBrand(ids);
        if (count > 0) {
            return JsonResult.success(count);
        } else {
            return JsonResult.failed();
        }
    }

    @ApiOperation("批量更新显示状态")
    @PostMapping("/update/showStatus")
    public JsonResult updateShowStatus(@RequestParam("ids")final List<Long> ids,
                                       @RequestParam("showStatus")final Integer showStatus) {
        final int count = brandService.updateShowStatus(ids, showStatus);
        if (count > 0) {
            return JsonResult.success(count);
        } else {
            return JsonResult.failed();
        }
    }

    @ApiOperation(value = "批量更新显示状态")
    @PostMapping("/update/factoryStatus")
    public JsonResult updateFactoryStatus(@RequestParam("ids") final List<Long> ids,
                                          @RequestParam("factoryStatus") final Integer factoryStatus) {
        final int count = brandService.updateFactoryStatus(ids, factoryStatus);
        if (count > 0) {
            return JsonResult.success(count);
        } else {
            return JsonResult.failed();
        }
    }

}
