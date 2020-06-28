package net.arver.mall.dao;

import net.arver.mall.dto.SmsFlashPromotionProduct;
import net.arver.mall.provider.SmsFlashPromotionProductRelationDaoSqlProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

/**
 * 自定义限时购商品关系管理Dao
 */
public interface SmsFlashPromotionProductRelationDao {

    /**
     * 获取显示购机相关商品信息
     * @param flashPromotionId
     * @param flashPromotionSessionId
     * @return
     */
    @Results({
        @Result(column="id", property="id", jdbcType= JdbcType.BIGINT, id=true),
        @Result(column="flash_promotion_id", property="flashPromotionId", jdbcType=JdbcType.BIGINT),
        @Result(column="flash_promotion_session_id", property="flashPromotionSessionId", jdbcType=JdbcType.BIGINT),
        @Result(column="product_id", property="productId", jdbcType=JdbcType.BIGINT),
        @Result(column="flash_promotion_price", property="flashPromotionPrice", jdbcType=JdbcType.DECIMAL),
        @Result(column="flash_promotion_count", property="flashPromotionCount", jdbcType=JdbcType.INTEGER),
        @Result(column="flash_promotion_limit", property="flashPromotionLimit", jdbcType=JdbcType.INTEGER),
        @Result(column="sort", property="sort", jdbcType=JdbcType.INTEGER),
        @Result(column="p_id", property="product.id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="p_name", property="product.name", jdbcType=JdbcType.VARCHAR),
        @Result(column="p_product_sn", property="product.productSn", jdbcType=JdbcType.VARCHAR),
        @Result(column="p_price", property="product.price", jdbcType=JdbcType.DECIMAL),
        @Result(column="p_stock", property="product.stock", jdbcType=JdbcType.INTEGER),
    })
    @SelectProvider(type = SmsFlashPromotionProductRelationDaoSqlProvider.class, method = "getList")
    List<SmsFlashPromotionProduct> getList(@Param("flashPromotionId") Long flashPromotionId, @Param("flashPromotionSessionId") Long flashPromotionSessionId);
}
