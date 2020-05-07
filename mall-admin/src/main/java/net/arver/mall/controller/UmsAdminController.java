package net.arver.mall.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.arver.mall.common.api.JsonResult;
import net.arver.mall.dto.UmsAdminLoginParam;
import net.arver.mall.model.UmsAdmin;
import net.arver.mall.service.UmsAdminService;
import net.arver.mall.service.UmsRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.HashMap;

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
}
