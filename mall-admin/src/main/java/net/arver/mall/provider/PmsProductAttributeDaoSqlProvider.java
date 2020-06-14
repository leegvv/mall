package net.arver.mall.provider;

import org.apache.ibatis.jdbc.SQL;

public class PmsProductAttributeDaoSqlProvider {

    public String getProductAttrInfo() {
        return new SQL(){{
            SELECT("pa.id attributeId, pac.id attributeCategoryId");
            FROM("pms_product_category_attribute_relation pcar");
            LEFT_OUTER_JOIN("pms_product_attribute pa ON pa.id = pcar.product_attribute_id");
            LEFT_OUTER_JOIN("pms_product_attribute_category pac ON pa.product_attribute_category_id = pac.id");
            WHERE("pcar.product_category_id = #{id}");
        }}.toString();
    }

    public String getProductAttributesByCategoryId() {
        return new SQL(){{
            SELECT("pa.*");
            FROM("pms_product_category_attribute_relation pcar");
            JOIN("pms_product_attribute pa ON pa.id = pcar.product_attribute_id");
            WHERE("pcar.product_category_id = #{id}", "pa.type=1");
        }}.toString();
    }
}
