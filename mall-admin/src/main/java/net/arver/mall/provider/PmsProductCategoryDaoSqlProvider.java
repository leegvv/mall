package net.arver.mall.provider;

import org.apache.ibatis.jdbc.SQL;

public class PmsProductCategoryDaoSqlProvider {

    public String listWithChildren() {
        return new SQL(){{
            SELECT("c1.id, c1.name, c2.id child_id, c2.name child_name");
            FROM("pms_product_category c1");
            LEFT_OUTER_JOIN("pms_product_category c2 on c1.id = c2.parent_id");
            WHERE("c1.parent_id = 0");
        }}.toString();
    }
}
