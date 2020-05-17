package net.arver.mall.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.arver.mall.common.api.JsonResult;
import net.arver.mall.model.UmsResourceCategory;
import net.arver.mall.service.UmsResourceCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 后天资源分类管理Controller
 */
@RestController
@Api(tags = "UmsResourceCategoryController", description = "后台资源分类管理")
@RequestMapping("/resourceCategory")
public class UmsResourceCategoryController {

    @Autowired
    private UmsResourceCategoryService resourceCategoryService;

    @ApiOperation("查询所有后台资源分类")
    @GetMapping("/listAll")
    public JsonResult<List<UmsResourceCategory>> listAll() {
        final List<UmsResourceCategory> resourceCategoryList = resourceCategoryService.listAll();
        return JsonResult.success(resourceCategoryList);
    }

    @ApiOperation("添加后台资源分类")
    @PostMapping("/create")
    public JsonResult create(@RequestBody final UmsResourceCategory umsResourceCategory) {
        final int count = resourceCategoryService.create(umsResourceCategory);
        if (count > 0) {
            return JsonResult.success(count);
        } else {
            return JsonResult.failed();
        }
    }

    @ApiOperation("修改后台资源分类")
    @PostMapping("/update/{id}")
    public JsonResult update(@PathVariable final Long id, @RequestBody final UmsResourceCategory umsResourceCategory) {
        final int count = resourceCategoryService.update(id, umsResourceCategory);
        if (count > 0) {
            return JsonResult.success(count);
        } else {
            return JsonResult.failed();
        }
    }

    @ApiOperation("根据id删除后台资源分类")
    @PostMapping("/delete/{id}")
    public JsonResult delete(@PathVariable final Long id) {
        final int count = resourceCategoryService.delete(id);
        if (count > 0) {
            return JsonResult.success(count);
        } else {
            return JsonResult.failed();
        }
    }

}
