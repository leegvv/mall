package net.arver.mall.provider;

import net.arver.mall.model.SmsCouponProductRelation;

import java.util.List;
import java.util.Map;

public class SmsCouponProductRelationDaoSqlProvider {

    public String insertList(final Map<String, Object> map) {
        final List<SmsCouponProductRelation> relations = (List<SmsCouponProductRelation>) map.get("list");
        final StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO sms_coupon_product_relation (product_id,product_name,product_sn,coupon_id) VALUES ");
        for (int i = 0; i < relations.size(); i++) {
            sql.append("(#{list[" + i + "].productId,jdbcType=BIGINT},#{list[" + i
                + "].productName,jdbcType=VARCHAR}, #{list[" + i + "].productSn, jdbcType=VARCHAR}, "
                + "#{list[" + i + "].couponId, jdbcType=INTEGER})" );
            if(i!=relations.size()-1){
                sql.append(",");
            }else {
                sql.append(";");
            }
        }
        return sql.toString();
    }
}
