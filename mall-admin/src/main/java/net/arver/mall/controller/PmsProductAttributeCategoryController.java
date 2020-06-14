package net.arver.mall.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.arver.mall.common.api.JsonResult;
import net.arver.mall.common.api.PageResult;
import net.arver.mall.dto.PmsProductAttributeCategoryItem;
import net.arver.mall.model.PmsProductAttributeCategory;
import net.arver.mall.service.PmsProductAttributeCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "PmsProductAttributeCategoryController", description = "商品属性分类管理")
@RestController
@RequestMapping("/productAttribute/category")
public class PmsProductAttributeCategoryController {

    @Autowired
    private PmsProductAttributeCategoryService productAttributeCategoryService;

    @ApiOperation("添加商品属性分类")
    @PostMapping("/create")
    public JsonResult create(@RequestParam final String name) {
        final int count = productAttributeCategoryService.create(name);
        if (count > 0) {
            return JsonResult.success(count);
        } else {
            return JsonResult.failed();
        }
    }

    @ApiOperation("修改商品属性分类")
    @PostMapping("/update/{id}")
    public JsonResult update(@PathVariable final Long id, @RequestParam final String name) {
        final int count = productAttributeCategoryService.update(id, name);
        if (count > 0) {
            return JsonResult.success(count);
        } else {
            return JsonResult.failed();
        }
    }

    @ApiOperation("删除单个商品属性分类")
    @GetMapping("/delete/{id}")
    public JsonResult delete(@PathVariable final Long id) {
        final int count = productAttributeCategoryService.delete(id);
        if (count > 0) {
            return JsonResult.success(count);
        } else {
            return JsonResult.failed();
        }
    }

    @ApiOperation("获取单个商品属性分类信息")
    @GetMapping("/{id}")
    public JsonResult<PmsProductAttributeCategory> getItem(@PathVariable final Long id) {
        final PmsProductAttributeCategory productAttributeCategory = productAttributeCategoryService.getItem(id);
        return JsonResult.success(productAttributeCategory);
    }

    @ApiOperation("分页获取所有商品属性分类")
    @GetMapping("/list")
    public JsonResult<PageResult<PmsProductAttributeCategory>> getList(@RequestParam(defaultValue = "1") final Integer pageNum,
                                                                       @RequestParam(defaultValue = "5") final Integer pageSize) {
        final List<PmsProductAttributeCategory> list = productAttributeCategoryService.getList(pageNum, pageSize);
        return JsonResult.success(PageResult.restPage(list));
    }

    @ApiOperation("获取所有商品属性分类及其下属性")
    @GetMapping("/list/withAttr")
    public JsonResult<List<PmsProductAttributeCategoryItem>> getListWithAttr() {
        final List<PmsProductAttributeCategoryItem> listWithAttr = productAttributeCategoryService.getListWithAttr();
        return JsonResult.success(listWithAttr);
    }


}
