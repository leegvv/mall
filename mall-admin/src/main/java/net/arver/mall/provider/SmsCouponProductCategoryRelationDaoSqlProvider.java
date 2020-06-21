package net.arver.mall.provider;

import net.arver.mall.model.SmsCouponProductCategoryRelation;

import java.util.List;
import java.util.Map;

public class SmsCouponProductCategoryRelationDaoSqlProvider {

    public String insertList(final Map<String, Object> map) {
        final List<SmsCouponProductCategoryRelation> relations = (List<SmsCouponProductCategoryRelation>) map.get("list");
        final StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO sms_coupon_product_category_relation (product_category_id,product_category_name,parent_category_name,coupon_id) VALUES ");
        for (int i = 0; i < relations.size(); i++) {
            sql.append("(#{list[" + i + "].productCategoryId,jdbcType=BIGINT},#{list[" + i
                + "].productCategoryName,jdbcType=VARCHAR}, #{list[" + i + "].parentCategoryName, jdbcType=VARCHAR}, "
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
