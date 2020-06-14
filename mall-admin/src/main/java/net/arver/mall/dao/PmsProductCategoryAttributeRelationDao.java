package net.arver.mall.dao;

import net.arver.mall.model.PmsProductCategoryAttributeRelation;
import net.arver.mall.provider.PmsProductCategoryAttributeRelationDaoSqlProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 自定义商品分类和属性关系Dao
 */
public interface PmsProductCategoryAttributeRelationDao {

    /**
     * 批量创建
     * @param relationList
     */
    @InsertProvider(type = PmsProductCategoryAttributeRelationDaoSqlProvider.class, method = "insertList")
    void insertList(@Param("list") List<PmsProductCategoryAttributeRelation> relationList);
}
