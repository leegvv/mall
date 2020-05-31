package net.arver.mall.service;

import net.arver.mall.model.UmsMenu;
import net.arver.mall.model.UmsPermission;
import net.arver.mall.model.UmsResource;
import net.arver.mall.model.UmsRole;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 后台角色管理service
 */
public interface UmsRoleService {

    /**
     * 添加角色
     * @param role
     * @return
     */
    int create(UmsRole role);

    /**
     * 修改角色信息
     * @param id
     * @param role
     * @return
     */
    int update(Long id, UmsRole role);

    /**
     * 批量删除角色
     * @param ids
     * @return
     */
    int delete(List<Long> ids);

    /**
     * 获取指定角色权限
     * @param roleId
     * @return
     */
    List<UmsPermission> getPermissionList(Long roleId);

    /**
     * 修改指定角色的权限
     * @param roleId
     * @param permissionIds
     * @return
     */
    @Transactional
    int updatePermission(Long roleId, List<Long> permissionIds);

    /**
     * 获取所有角色列表.
     * @return
     */
    List<UmsRole> list();

    /**
     * 分页获取角色列表.
     * @param keyword
     * @param pageSize
     * @param pageNum
     * @return
     */
    List<UmsRole> list(String keyword, Integer pageSize, Integer pageNum);

    /**
     * 根据管理员id获取对应菜单
     * @param adminId
     * @return
     */
    List<UmsMenu> getMenuList(Long adminId);

    /**
     * 获取角色相关菜单.
     * @param roleId
     * @return
     */
    List<UmsMenu> listMenu(Long roleId);

    /**
     * 获取角色相关资源
     * @param roleId
     * @return
     */
    List<UmsResource> listResource(Long roleId);

    /**
     * 给角色分配菜单
     * @param roleId
     * @param menuIds
     * @return
     */
    @Transactional
    int allocMenu(Long roleId, List<Long> menuIds);

    /**
     * 给角色分配资源
     * @param roleId
     * @param resourceIds
     * @return
     */
    @Transactional
    int allocResource(Long roleId, List<Long> resourceIds);

}
