package net.arver.mall.service;

import net.arver.mall.dto.UmsMenuNode;
import net.arver.mall.model.UmsMenu;

import java.util.List;

/**
 * 后台管理菜单service.
 */
public interface UmsMenuService {

    /**
     * 创建后台菜单
     * @param umsMenu
     * @return
     */
    int create(UmsMenu umsMenu);

    /**
     * 修改后台菜单
     * @param id
     * @param umsMenu
     * @return
     */
    int update(Long id, UmsMenu umsMenu);

    /**
     * 根据id获取菜单详情
     * @param id
     * @return
     */
    UmsMenu getItem(Long id);

    /**
     * 根据id删除菜单
     * @param id
     * @return
     */
    int delete(Long id);

    /**
     * 分页查询后台菜单
     */
    List<UmsMenu> list(Long parentId, Integer pageSize, Integer pageNum);

    /**
     * 树形结构返回所有菜单列表
     * @return
     */
    List<UmsMenuNode> treeList();

    /**
     * 修改菜单显示状态
     * @param id
     * @param hidden
     * @return
     */
    int updateHidden(Long id, Integer hidden);
}
