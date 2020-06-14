package net.arver.mall.service;

import net.arver.mall.dto.PmsProductCategoryParam;
import net.arver.mall.dto.PmsProductCategoryWithChildrenItem;
import net.arver.mall.model.PmsProductCategory;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 商品分类Service
 */
public interface PmsProductCategoryService {

    /**
     * 创建商品分类
     * @param pmsProductCategoryParam
     * @return
     */
    @Transactional
    int create(PmsProductCategoryParam pmsProductCategoryParam);

    /**
     * 修改商品分类
     * @param id
     * @param pmsProductCategoryParam
     * @return
     */
    @Transactional
    int update(Long id, PmsProductCategoryParam pmsProductCategoryParam);

    /**
     * 分页获取商品分类
     * @param parentId
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<PmsProductCategory> list(Long parentId, Integer pageNum, Integer pageSize);

    /**
     * 删除商品分类
     * @param id
     * @return
     */
    int delete(Long id);

    /**
     * 根据ID获取商品分类
     * @param id
     * @return
     */
    PmsProductCategory getItem(Long id);

    /**
     * 批量修改导航状态
     * @param ids
     * @param navStatus
     * @return
     */
    int updateNavStatus(List<Long> ids, Integer navStatus);

    /**
     * 批量修改显示状态
     * @param ids
     * @param showStatus
     * @return
     */
    int updateShowStatus(List<Long> ids, Integer showStatus);

    /**
     * 以层级形式获取商品分类
     * @return
     */
    List<PmsProductCategoryWithChildrenItem> listWithChildren();

}
