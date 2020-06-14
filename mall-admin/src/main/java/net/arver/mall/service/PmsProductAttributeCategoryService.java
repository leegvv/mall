package net.arver.mall.service;

import net.arver.mall.dto.PmsProductAttributeCategoryItem;
import net.arver.mall.model.PmsProductAttributeCategory;

import java.util.List;

/**
 * 商品属性分了Service
 */
public interface PmsProductAttributeCategoryService {

    /**
     * 创建属性分类
     * @param name
     * @return
     */
    int create(String name);

    /**
     * 修改属性分类
     * @param id
     * @param name
     * @return
     */
    int update(Long id, String name);

    /**
     * 删除属性分类
     * @param id
     * @return
     */
    int delete(Long id);

    /**
     * 获取属性分类详情
     * @param id
     * @return
     */
    PmsProductAttributeCategory getItem(Long id);

    /**
     * 分页查询属性分类
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<PmsProductAttributeCategory> getList(Integer pageNum, Integer pageSize);

    /**
     * 获取包含属性的属性分类
     * @return
     */
    List<PmsProductAttributeCategoryItem> getListWithAttr();
}
