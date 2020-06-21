package net.arver.mall.dao;

import net.arver.mall.model.SmsCouponProductCategoryRelation;
import net.arver.mall.provider.SmsCouponProductCategoryRelationDaoSqlProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 自定义优惠券和商品分类关系管理dao.
 */
public interface SmsCouponProductCategoryRelationDao {

    /**
     * 批量创建.
     * @param productCategoryRelationList
     * @return
     */
    @InsertProvider(type = SmsCouponProductCategoryRelationDaoSqlProvider.class, method = "insertList")
    int insertList(@Param("list")List<SmsCouponProductCategoryRelation> productCategoryRelationList);
}
