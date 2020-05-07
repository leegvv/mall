package net.arver.mall.service;

import net.arver.mall.model.UmsMenu;

import java.util.List;

/**
 * 后台角色管理service
 */
public interface UmsRoleService {

    /**
     * 根据管理员id获取对应菜单
     * @param adminId
     * @return
     */
    List<UmsMenu> getMenuList(Long adminId);

}
