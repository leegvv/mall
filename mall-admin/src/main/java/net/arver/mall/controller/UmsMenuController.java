package net.arver.mall.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.arver.mall.common.api.JsonResult;
import net.arver.mall.common.api.PageResult;
import net.arver.mall.dto.UmsMenuNode;
import net.arver.mall.model.UmsMenu;
import net.arver.mall.service.UmsMenuService;
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
 * 后台菜单管理Controller
 */
@RestController
@Api(tags = "UmsMenuController", description = "后台菜单管理")
@RequestMapping("/menu")
public class UmsMenuController {

    @Autowired
    private UmsMenuService menuService;

    @ApiOperation("添加后台菜单")
    @PostMapping("/create")
    public JsonResult create(@RequestBody final UmsMenu umsMenu) {
        final int count = menuService.create(umsMenu);
        if (count > 0) {
            return JsonResult.success(count);
        } else {
            return JsonResult.failed();
        }
    }

    @ApiOperation("修改后天菜单")
    @PostMapping("/update/{id}")
    public JsonResult update(@PathVariable final Long id, @RequestBody final UmsMenu umsMenu) {
        final int count = menuService.update(id, umsMenu);
        if (count > 0) {
            return JsonResult.success(count);
        } else {
            return JsonResult.failed();
        }
    }

    @ApiOperation("根据id获取菜单详情")
    @GetMapping("/{id}")
    public JsonResult<UmsMenu> getItem(@PathVariable final Long id) {
        final UmsMenu umsMenu = menuService.getItem(id);
        return JsonResult.success(umsMenu);
    }

    @ApiOperation("根据id删除后台菜单")
    @PostMapping("/delete/{id}")
    public JsonResult delete(@PathVariable final Long id) {
        final int count = menuService.delete(id);
        if (count > 0) {
            return JsonResult.success(count);
        } else {
            return JsonResult.failed();
        }
    }

    @ApiOperation("分页查询后台菜单")
    @GetMapping("/list/{parentId}")
    public JsonResult<PageResult<UmsMenu>> list(@PathVariable final Long parentId,
                                                @RequestParam(value = "pageSize", defaultValue = "5") final Integer pageSize,
                                                @RequestParam(value = "pageNum", defaultValue = "1") final Integer pageNum) {
        final List<UmsMenu> menuList = menuService.list(parentId, pageSize, pageNum);
        return JsonResult.success(PageResult.restPage(menuList));
    }

    @ApiOperation("树形结构返回所有菜单列表")
    @GetMapping("/treeList")
    public JsonResult<List<UmsMenuNode>> treeList() {
        final List<UmsMenuNode> list = menuService.treeList();
        return JsonResult.success(list);
    }

    @ApiOperation("修改菜单显示状态")
    @PostMapping("/updateHidden/{id}")
    public JsonResult updateHidden(@PathVariable final Long id, @RequestParam("hidden") final Integer hidden) {
        final int count = menuService.updateHidden(id, hidden);
        if (count > 0) {
            return JsonResult.success(count);
        } else {
            return JsonResult.failed();
        }
    }

}
