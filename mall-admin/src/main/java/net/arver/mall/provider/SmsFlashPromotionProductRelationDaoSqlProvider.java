package net.arver.mall.provider;

import org.apache.ibatis.jdbc.SQL;

public class SmsFlashPromotionProductRelationDaoSqlProvider {

    /**
     * 获取列表sql
     * @return
     */
    public String getList() {
        return new SQL(){{
            SELECT("r.*, p.id as p_id, p.name as p_name, p.product_sn p_product_sn, p.price p_price, p.stock as p_stock");
            FROM("sms_flash_promotion_product_relation r");
            LEFT_OUTER_JOIN("pms_product p ON r.product_id = p.id");
            WHERE("r.flash_promotion_id = #{flashPromotionId}",
                "r.flash_promotion_session_id = #{flashPromotionSessionId}");
            ORDER_BY("r.sort DESC");
        }}.toString();
    }
}
