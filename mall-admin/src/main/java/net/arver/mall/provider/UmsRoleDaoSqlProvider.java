package net.arver.mall.provider;

import org.apache.ibatis.jdbc.SQL;

public class UmsRoleDaoSqlProvider {

    public String getMenuList(final Long adminId) {
        return new SQL(){{
            SELECT("m.*");
            FROM("ums_admin_role_relation arr");
            LEFT_OUTER_JOIN("ums_role r on arr.role_id = r.id");
            LEFT_OUTER_JOIN("ums_role_menu_relation rmr on r.id = rmr.role_id");
            LEFT_OUTER_JOIN("ums_menu m on rmr.menu_id = m.id");
            WHERE("arr.admin_id = #{adminId}", "m.id IS NOT NULL");
            GROUP_BY("m.id");
        }}.toString();
    }
}
