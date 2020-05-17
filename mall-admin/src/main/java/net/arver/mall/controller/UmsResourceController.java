package net.arver.mall.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.arver.mall.common.api.JsonResult;
import net.arver.mall.common.api.PageResult;
import net.arver.mall.model.UmsResource;
import net.arver.mall.security.component.DynamicSecurityMetadataSource;
import net.arver.mall.service.UmsResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(tags = "UmsResourceController", description = "后台资源管理")
@RequestMapping("/resource")
public class UmsResourceController {

    @Autowired
    private UmsResourceService resourceService;

    @Autowired
    private DynamicSecurityMetadataSource dynamicSecurityMetadataSource;

    @ApiOperation("添加后台资源")
    @PostMapping("/create")
    public JsonResult create(@RequestBody final UmsResource umsResource) {
        final int count = resourceService.crate(umsResource);
        dynamicSecurityMetadataSource.clearDataSource();
        if (count > 0) {
            return JsonResult.success(count);
        } else {
            return JsonResult.failed();
        }
    }

    @ApiOperation("修改后台资源")
    @PostMapping("/update/{id}")
    public JsonResult update(@PathVariable final Long id, @RequestBody final UmsResource umsResource) {
        final int count = resourceService.update(id, umsResource);
        dynamicSecurityMetadataSource.clearDataSource();;
        if (count > 0) {
            return JsonResult.success(count);
        } else {
            return JsonResult.failed();
        }
    }

    @ApiOperation("根据id获取资源详情")
    @GetMapping("/{id}")
    public JsonResult<UmsResource> getItem(@PathVariable final Long id) {
        final UmsResource umsResource = resourceService.getItem(id);
        return JsonResult.success(umsResource);
    }

    @ApiOperation("根据id删除后台资源")
    @PostMapping("/delete/{id}")
    public JsonResult delete(@PathVariable final Long id) {
        final int count = resourceService.delete(id);
        dynamicSecurityMetadataSource.clearDataSource();
        if (count > 0) {
            return JsonResult.success(count);
        } else {
            return JsonResult.failed();
        }
    }

    @ApiOperation("分页模糊查询后台资源")
    @GetMapping("/list")
    public JsonResult<PageResult<UmsResource>> list(@RequestParam(required = false) final Long categoryId,
                                                    @RequestParam(required = false) final String nameKeyword,
                                                    @RequestParam(required = false) final String urlKeyword,
                                                    @RequestParam(value = "pageSize", defaultValue = "5") final Integer pageSize,
                                                    @RequestParam(value = "pageNum", defaultValue = "1") final Integer pageNum) {
        final List<UmsResource> resourceList = resourceService.list(categoryId, nameKeyword, urlKeyword, pageSize, pageNum);
        return JsonResult.success(PageResult.restPage(resourceList));
    }

    @ApiOperation("查询所有后台资源")
    @GetMapping("/listAll")
    public JsonResult<List<UmsResource>> listAll() {
        final List<UmsResource> resourceList = resourceService.listAll();
        return JsonResult.success(resourceList);
    }


}
