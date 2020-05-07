package net.arver.mall.dao;

import net.arver.mall.model.UmsMenu;
import net.arver.mall.provider.UmsRoleDaoSqlProvider;
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
    List<UmsMenu> getMenuList(Long adminId);
}
