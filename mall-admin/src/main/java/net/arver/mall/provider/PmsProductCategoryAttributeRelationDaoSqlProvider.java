package net.arver.mall.provider;

import net.arver.mall.model.PmsProductCategoryAttributeRelation;

import java.util.List;
import java.util.Map;

public class PmsProductCategoryAttributeRelationDaoSqlProvider {

    public String insertList(final Map<String, Object> map) {
        final List<PmsProductCategoryAttributeRelation> relations = (List<PmsProductCategoryAttributeRelation>) map.get("list");
        final StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO pms_product_category_attribute_relation (product_category_id, product_attribute_id) VALUES ");
        for (int i = 0; i < relations.size(); i++) {
            sql.append("(#{list[" + i + "].productCategoryId,jdbcType=BIGINT},#{list[" + i
                + "].productAttributeId,jdbcType=BIGINT})");
            if(i!=relations.size()-1){
                sql.append(",");
            }else {
                sql.append(";");
            }
        }
        return sql.toString();
    }
}
