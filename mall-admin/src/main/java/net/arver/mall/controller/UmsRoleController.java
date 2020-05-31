package net.arver.mall.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.arver.mall.common.api.JsonResult;
import net.arver.mall.common.api.PageResult;
import net.arver.mall.model.UmsMenu;
import net.arver.mall.model.UmsPermission;
import net.arver.mall.model.UmsResource;
import net.arver.mall.model.UmsRole;
import net.arver.mall.service.UmsRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "UmsRoleController", description = "后台用户角色管理")
@RestController
@RequestMapping("/role")
public class UmsRoleController {

    @Autowired
    private UmsRoleService roleService;

    @ApiOperation("添加角色")
    @PostMapping("/create")
    public JsonResult create(@RequestBody UmsRole role) {
        final int count = roleService.create(role);
        if (count > 0) {
            return JsonResult.success(count);
        }
        return JsonResult.failed();
    }

    @ApiOperation("修改角色")
    @PostMapping("/update/{id}")
    public JsonResult update(@PathVariable final Long id, @RequestBody UmsRole role) {
        final int count = roleService.update(id, role);
        if (count > 0) {
            return JsonResult.success(count);
        }
        return JsonResult.failed();
    }

    @ApiOperation("批量删除角色")
    @PostMapping("/delete")
    public JsonResult delete(@RequestParam("ids") final List<Long> ids) {
        final int count = roleService.delete(ids);
        if (count > 0) {
            return JsonResult.success(count);
        }
        return JsonResult.failed();
    }

    @ApiOperation("获取相应的角色权限")
    @GetMapping("/permission/{roleId}")
    public JsonResult<List<UmsPermission>> getPermissionList(@PathVariable final Long roleId) {
        final List<UmsPermission> permissionList = roleService.getPermissionList(roleId);
        return JsonResult.success(permissionList);
    }

    @ApiOperation("修改角色权限")
    @PostMapping("/permission/update")
    public JsonResult updatePermission(@RequestParam final Long roleId,
                                       @RequestParam("permissionIds") final List<Long> permissionIds) {
        final int count = roleService.updatePermission(roleId, permissionIds);
        if (count > 0) {
            return JsonResult.success(count);
        }
        return JsonResult.failed();
    }

    @ApiOperation("获取所有角色")
    @GetMapping("/listAll")
    public JsonResult<List<UmsRole>> listAll() {
        final List<UmsRole> roleList = roleService.list();
        return JsonResult.success(roleList);
    }

    @ApiOperation("根据角色名称分页获取角色列表")
    @GetMapping("/list")
    public JsonResult<PageResult<UmsRole>> list(@RequestParam(value = "keyword", required = false) final String keyword,
                                                @RequestParam(value = "pageSize", defaultValue = "5") final Integer pageSize,
                                                @RequestParam(value = "pageNum", defaultValue = "1") final Integer pageNum) {
        final List<UmsRole> roleList = roleService.list(keyword, pageSize, pageNum);
        return JsonResult.success(PageResult.restPage(roleList));
    }

    @ApiOperation("修改角色状态")
    @PostMapping("/updateStatus/{id}")
    public JsonResult updateStatus(@PathVariable final Long id, @RequestParam(value = "status")final Integer status) {
        final UmsRole umsRole = new UmsRole();
        umsRole.setSort(status);
        final int count = roleService.update(id, umsRole);
        if (count > 0) {
            return JsonResult.success(count);
        }
        return JsonResult.failed();
    }

    @ApiOperation("获取角色相关菜单")
    @GetMapping("/listMenu/{roleId}")
    public JsonResult<List<UmsMenu>> listMenu(@PathVariable final Long roleId) {
        final List<UmsMenu> menuList = roleService.listMenu(roleId);
        return JsonResult.success(menuList);
    }

    @ApiOperation("获取角色相关资源")
    @GetMapping("/listResource/{roleId}")
    public JsonResult<List<UmsResource>> listResource(@PathVariable final Long roleId) {
        final List<UmsResource> resourceList = roleService.listResource(roleId);
        return JsonResult.success(resourceList);
    }

    @ApiOperation("给角色分配菜单")
    @PostMapping("/allocMenu")
    public JsonResult allocMenu(@RequestParam final Long roleId, @RequestParam List<Long> menuIds) {
        final int count = roleService.allocMenu(roleId, menuIds);
        return JsonResult.success(count);
    }

    @ApiOperation("给角色分配资源")
    @PostMapping("/allocResource")
    public JsonResult allocResource(@RequestParam Long roleId, @RequestParam List<Long> resourceIds) {
        final int count = roleService.allocResource(roleId, resourceIds);
        return JsonResult.success(count);
    }


}
