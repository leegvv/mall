package net.arver.mall.dao;

import net.arver.mall.dto.ProductAttrInfo;
import net.arver.mall.model.PmsProductAttribute;
import net.arver.mall.provider.PmsProductAttributeDaoSqlProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

public interface PmsProductAttributeDao {
    /**
     * 获取商品属性信息
     * @param productCategoryId
     * @return
     */
    @SelectProvider(type = PmsProductAttributeDaoSqlProvider.class, method = "getProductAttrInfo")
    List<ProductAttrInfo> getProductAttrInfo(@Param("id") Long productCategoryId);

    @SelectProvider(type = PmsProductAttributeDaoSqlProvider.class, method = "getProductAttributesByCategoryId")
    List<PmsProductAttribute> getProductAttributesByCategoryId(@Param("id") Long productCategoryId);
}
