package net.arver.mall.service;

import net.arver.mall.dto.UmsAdminParam;
import net.arver.mall.dto.UpdateAdminPasswordParam;
import net.arver.mall.model.UmsAdmin;
import net.arver.mall.model.UmsPermission;
import net.arver.mall.model.UmsResource;
import net.arver.mall.model.UmsRole;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 后台管理员service
 */
public interface UmsAdminService {

    /**
     * 根据用户名获取后台管理员
     * @param username
     * @return
     */
    UmsAdmin getAdminByUsername(String username);

    /**
     * 注册功能
     * @param umsAdminParam
     * @return
     */
    UmsAdmin register(UmsAdminParam umsAdminParam);

    /**
     * 登录功能
     * @param username
     * @param password
     * @return
     */
    String login(String username, String password);

    /**
     * 刷新token功能
     * @param oldToken
     * @return
     */
    String refreshToken(String oldToken);

    /**
     * 根据用户id获取用户
     * @param id
     * @return
     */
    UmsAdmin getItem(Long id);

    /**
     * 根据用户名或昵称分页查询用户
     * @param keyword
     * @param pageSize
     * @param pageNum
     * @return
     */
    List<UmsAdmin> list(String keyword, Integer pageSize, Integer pageNum);

    /**
     * 修改指定用户信息
     * @param id
     * @param admin
     * @return
     */
    int update(Long id, UmsAdmin admin);

    /**
     * 删除指定用户
     * @param id
     * @return
     */
    int delete(Long id);

    /**
     * 修改用户角色关系
     * @param adminId
     * @param roleIds
     * @return
     */
    @Transactional
    int updateRole(Long adminId, List<Long> roleIds);

    /**
     * 获取用户角色.
     * @param adminId
     * @return
     */
    List<UmsRole> getRoleList(Long adminId);

    /**
     * 获取指定用户的可访问资源
     * @param adminId
     * @return
     */
    List<UmsResource> getResourceList(Long adminId);

    /**
     * 修改用户权限
     * @param adminId
     * @param permissionIds
     * @return
     */
    @Transactional
    int updatePermission(Long adminId, List<Long> permissionIds);

    /**
     * 获取用户所有权限
     * @param adminId
     * @return
     */
    List<UmsPermission> getPermissionList(Long adminId);

    /**
     * 修改密码
     * @param updateAdminPasswordParam
     * @return
     */
    int updatePassword(UpdateAdminPasswordParam updateAdminPasswordParam);

    /**
     * 获取用户信息.
     * @param username
     * @return
     */
    UserDetails loadUserByUsername(String username);
}
