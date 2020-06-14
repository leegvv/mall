package net.arver.mall.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.arver.mall.common.api.JsonResult;
import net.arver.mall.common.api.PageResult;
import net.arver.mall.dto.PmsProductCategoryParam;
import net.arver.mall.dto.PmsProductCategoryWithChildrenItem;
import net.arver.mall.model.PmsProductCategory;
import net.arver.mall.service.PmsProductCategoryService;
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

@Api(tags = "PmsProductCategoryController", description = "商品分类管理")
@RestController
@RequestMapping("/productCategory")
public class PmsProductCategoryController {

    @Autowired
    private PmsProductCategoryService productCategoryService;

    @ApiOperation("添加产品分类")
    @PostMapping("/create")
    public JsonResult create(@Validated @RequestBody final PmsProductCategoryParam productCategoryParam, BindResult result) {
        final int count = productCategoryService.create(productCategoryParam);
        if (count > 0) {
            return JsonResult.success(count);
        } else {
            return JsonResult.failed();
        }
    }

    @ApiOperation("修改商品分类")
    @PostMapping("/update/{id}")
    public JsonResult update(@PathVariable final Long id,
                             @Validated @RequestBody final PmsProductCategoryParam productCategoryParam,
                             BindResult reuslt) {
        final int count = productCategoryService.update(id, productCategoryParam);
        if (count > 0) {
            return JsonResult.success(count);
        } else {
            return JsonResult.failed();
        }
    }

    @ApiOperation("分页查询商品分类")
    @GetMapping("/list/{parentId}")
    public JsonResult<PageResult<PmsProductCategory>> getList(@PathVariable final Long parentId,
                                                              @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                              @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        final List<PmsProductCategory> productCategoryList = productCategoryService.list(parentId, pageNum, pageSize);
        return JsonResult.success(PageResult.restPage(productCategoryList));
    }

    @ApiOperation("根据id获取商品分类")
    @GetMapping("/{id}")
    public JsonResult<PmsProductCategory> getItem(@PathVariable final Long id) {
        final PmsProductCategory productCategory = productCategoryService.getItem(id);
        return JsonResult.success(productCategory);
    }

    @ApiOperation("删除商品分类")
    @PostMapping("/delete/{id}")
    public JsonResult delete(@PathVariable final Long id) {
        final int count = productCategoryService.delete(id);
        if (count > 0) {
            return JsonResult.success(count);
        } else {
            return JsonResult.failed();
        }
    }

    @ApiOperation("修改导航栏显示状态")
    @PostMapping("/update/navStatus")
    public JsonResult updateNavStatus(@RequestParam("ids") final List<Long> ids,
                                      @RequestParam("navStatus") final Integer navStatus) {
        final int count = productCategoryService.updateNavStatus(ids, navStatus);
        if (count > 0) {
            return JsonResult.success(count);
        } else {
            return JsonResult.failed();
        }
    }

    @ApiOperation("修改显示状态")
    @PostMapping("/update/showStatus")
    public JsonResult updateShowStatus(@RequestParam("ids") final List<Long> ids,
                                       @RequestParam("showStatus") final Integer showStatus) {
        final int count = productCategoryService.updateShowStatus(ids, showStatus);
        if (count > 0) {
            return JsonResult.success(count);
        } else {
            return JsonResult.failed();
        }
    }

    @ApiOperation("查询所有一级分类及子分类")
    @GetMapping("/list/withChildren")
    public JsonResult<List<PmsProductCategoryWithChildrenItem>> listWithChildren() {
        final List<PmsProductCategoryWithChildrenItem> list = productCategoryService.listWithChildren();
        return JsonResult.success(list);
    }


}
