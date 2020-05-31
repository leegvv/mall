package net.arver.mall.dao;

import net.arver.mall.model.UmsMenu;
import net.arver.mall.model.UmsResource;
import net.arver.mall.provider.UmsRoleDaoSqlProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

/**
 * 后台用户角色自定义dao
 */
public interface UmsRoleDao {

    /**
     * 根据用户id获取菜单
     * @param adminId
     * @return
     */
    @SelectProvider(type = UmsRoleDaoSqlProvider.class, method = "getMenuList")
    List<UmsMenu> getMenuList(@Param("adminId") Long adminId);

    /**
     * 根据角色id获取菜单
     * @param roleId
     * @return
     */
    @SelectProvider(type = UmsRoleDaoSqlProvider.class, method = "getMenuListByRoleId")
    List<UmsMenu> getMenuListByRoleId(@Param("roleId") Long roleId);

    /**
     * 根据角色id获取资源
     * @param roleId
     * @return
     */
    @SelectProvider(type = UmsRoleDaoSqlProvider.class, method = "getResourceListByRoleId")
    List<UmsResource> getResourceListByRoleId(@Param("roleId") Long roleId);
}
