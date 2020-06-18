package net.arver.mall.dao;

import net.arver.mall.dto.SmsFlashPromotionProduct;
import net.arver.mall.provider.SmsFlashPromotionProductRelationDaoSqlProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

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
    @SelectProvider(type = SmsFlashPromotionProductRelationDaoSqlProvider.class, method = "getList")
    List<SmsFlashPromotionProduct> getList(@Param("flashPromotionId") Long flashPromotionId, @Param("flashPromotionSessionId") Long flashPromotionSessionId);
}
