package net.arver.mall.service;

import net.arver.mall.dto.PmsProductAttributeParam;
import net.arver.mall.dto.ProductAttrInfo;
import net.arver.mall.model.PmsProductAttribute;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 商品属性Service
 */
public interface PmsProductAttributeService {

    /**
     * 根据分类分页获取商品属性
     * @param cid 分类id
     * @param type 0->属性； 1->参数
     * @param pageSize
     * @param pageNum
     * @return
     */
    List<PmsProductAttribute> list(Long cid, Integer type, Integer pageSize, Integer pageNum);

    /**
     * 添加商品属性
     * @param pmsProductAttributeParam
     * @return
     */
    @Transactional
    int create(PmsProductAttributeParam pmsProductAttributeParam);

    /**
     * 修改商品属性
     * @param id
     * @param pmsProductAttributeParam
     * @return
     */
    int update(Long id, PmsProductAttributeParam pmsProductAttributeParam);

    /**
     * 修改商品属性
     * @param id
     * @return
     */
    PmsProductAttribute getItem(Long id);

    /**
     * 删除
     * @param ids
     * @return
     */
    @Transactional
    int delete(List<Long> ids);

    /**
     *
     * @param productCategoryId
     * @return
     */
    List<ProductAttrInfo> getProductAttrInfo(Long productCategoryId);


}
