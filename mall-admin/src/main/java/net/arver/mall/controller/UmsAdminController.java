package net.arver.mall.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.arver.mall.common.api.JsonResult;
import net.arver.mall.common.api.PageResult;
import net.arver.mall.dto.UmsAdminLoginParam;
import net.arver.mall.dto.UmsAdminParam;
import net.arver.mall.dto.UpdateAdminPasswordParam;
import net.arver.mall.model.UmsAdmin;
import net.arver.mall.model.UmsPermission;
import net.arver.mall.model.UmsRole;
import net.arver.mall.service.UmsAdminService;
import net.arver.mall.service.UmsRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;

/**
 * 后台用户管理
 */
@RestController
@Api(tags = "UmsAdminController", description = "后台用户管理")
@RequestMapping("/admin")
public class UmsAdminController {

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Autowired
    private UmsAdminService adminService;

    @Autowired
    private UmsRoleService roleService;

    @ApiOperation(value = "用户注册")
    @PostMapping("/register")
    public JsonResult<UmsAdmin> register(@RequestBody final UmsAdminParam umsAdminParam) {
        final UmsAdmin umsAdmin = adminService.register(umsAdminParam);
        if (umsAdmin == null) {
            JsonResult.failed();
        }
        return JsonResult.success(umsAdmin);
    }

    @ApiOperation(value = "登录后返回token")
    @PostMapping("/login")
    public JsonResult login(@RequestBody UmsAdminLoginParam umsAdminLoginParam) {
        final String token = adminService.login(umsAdminLoginParam.getUsername(), umsAdminLoginParam.getPassword());
        if (token == null) {
            return JsonResult.validateFailed("用户名或密码错误");
        }
        final HashMap<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return JsonResult.success(tokenMap);
    }

    @ApiOperation(value = "刷新token")
    @GetMapping("/refreshToken")
    public JsonResult refreshToken(HttpServletRequest request) {
        final String token = request.getHeader(tokenHeader);
        final String refreshToken = adminService.refreshToken(token);
        if (refreshToken == null) {
            return JsonResult.failed("token已经过期");
        }
        final HashMap<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", refreshToken);
        tokenMap.put("tokenHead", tokenHead);
        return JsonResult.success(tokenMap);
    }


    @ApiOperation("获取当前登录用户信息")
    @GetMapping("/info")
    public JsonResult getAdminInfo(final Principal principal) {
        if (principal == null) {
            return JsonResult.unauthorized(null);
        }
        final String username = principal.getName();
        final UmsAdmin umsAdmin = adminService.getAdminByUsername(username);
        final HashMap<String, Object> data = new HashMap<>();
        data.put("username", umsAdmin.getUsername());
        data.put("roles", new String[]{"TEST"});
        data.put("menus", roleService.getMenuList(umsAdmin.getId()));
        data.put("icon", umsAdmin.getIcon());
        return JsonResult.success(data);
    }

    @ApiOperation("登出功能")
    @PostMapping("/logout")
    public JsonResult logout() {
        return JsonResult.success();
    }

    @ApiOperation("根据用户名或姓名分页获取用户列表")
    @GetMapping("/list")
    public JsonResult<PageResult<UmsAdmin>> list(@RequestParam(value = "keyword", required = false) final String keyword,
                                                 @RequestParam(value = "pageSize", defaultValue = "5") final Integer pagetSize,
                                                 @RequestParam(value = "pageNum", defaultValue = "1") final Integer pageNum) {
        final List<UmsAdmin> adminList = adminService.list(keyword, pagetSize, pageNum);
        return JsonResult.success(PageResult.restPage(adminList));
    }

    @ApiOperation("获取指定用户")
    @GetMapping("/{id}")
    public JsonResult<UmsAdmin> getItem(@PathVariable final Long id) {
        final UmsAdmin admin = adminService.getItem(id);
        return JsonResult.success(admin);
    }

    @ApiOperation("修改指定用户信息")
    @PostMapping("/update/{id}")
    public JsonResult update(@PathVariable final Long id, @RequestBody final UmsAdmin admin) {
        final int count = adminService.update(id, admin);
        if (count > 0) {
            return JsonResult.success(count);
        }
        return JsonResult.failed();
    }

    @ApiOperation("修改指定用户密码")
    @PostMapping("/updatePassword")
    public JsonResult updatePassword(@RequestBody final UpdateAdminPasswordParam updateAdminPasswordParam) {
        final int status = adminService.updatePassword(updateAdminPasswordParam);
        if (status > 0) {
            return JsonResult.success(status);
        } else if (status == -1) {
            return JsonResult.failed("提交参数不合法");
        } else if (status == -2) {
            return JsonResult.failed("找不到该用户");
        } else if (status == -3) {
            return JsonResult.failed("旧密码错误");
        }
        return JsonResult.failed();
    }

    @ApiOperation("删除指定用户信息")
    @PostMapping("/delete/{id}")
    public JsonResult delete(@PathVariable final Long id) {
        final int count = adminService.delete(id);
        if (count > 0) {
            return JsonResult.success(count);
        }
        return JsonResult.failed();
    }

    @ApiOperation("修改账号状态")
    @PostMapping("/updateStatus/{id}")
    public JsonResult updateStatus(@PathVariable final Long id, @RequestParam(value = "status") final Integer status) {
        final UmsAdmin umsAdmin = new UmsAdmin();
        umsAdmin.setStatus(status);
        final int count = adminService.update(id, umsAdmin);
        if (count > 0) {
            return JsonResult.success(count);
        }
        return JsonResult.failed();
    }

    @ApiOperation("给用户分配角色")
    @PostMapping("/role/update")
    public JsonResult updateRole(@RequestParam("adminId")final Long adminId,
                                 @RequestParam("roleIds")final List<Long> roleIds) {
        final int count = adminService.updateRole(adminId, roleIds);
        if (count > 0) {
            return JsonResult.success(count);
        }
        return JsonResult.failed();
    }

    @ApiOperation("获取指定用户的角色")
    @GetMapping("/role/{adminId}")
    public JsonResult<List<UmsRole>> getRoleList(@PathVariable final Long adminId) {
        final List<UmsRole> roleList = adminService.getRoleList(adminId);
        return JsonResult.success(roleList);
    }

    @ApiOperation("给用户分配+-权限")
    @PostMapping("/permission/update")
    public JsonResult updatePermission(@RequestParam final Long adminId,
                                       @RequestParam("permissionIds") final List<Long> permissionIds) {
        final int count = adminService.updatePermission(adminId, permissionIds);
        if (count > 0) {
            return JsonResult.success(count);
        }
        return JsonResult.failed();
    }

    @ApiOperation("获取用户所有权限（包括+-权限）")
    @GetMapping("/permission/{adminId}")
    public JsonResult<List<UmsPermission>> getPermissionList(@PathVariable Long adminId) {
        final List<UmsPermission> permissionList = adminService.getPermissionList(adminId);
        return JsonResult.success(permissionList);
    }

}
