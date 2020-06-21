package net.arver.mall.dao;

import net.arver.mall.model.SmsCouponProductRelation;
import net.arver.mall.provider.SmsCouponProductRelationDaoSqlProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 自定义优惠券和商品关系Dao.
 */
public interface SmsCouponProductRelationDao {

    /**
     * 批量创建
     * @param productRelationList
     * @return
     */
    @InsertProvider(type = SmsCouponProductRelationDaoSqlProvider.class, method = "insertList")
    int insertList(@Param("list")List<SmsCouponProductRelation> productRelationList);

}
