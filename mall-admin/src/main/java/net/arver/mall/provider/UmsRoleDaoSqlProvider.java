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

    public String getMenuListByRoleId() {
        return new SQL() {{
            SELECT("m.*");
            FROM("ums_role_menu_relation rmr");
            LEFT_OUTER_JOIN("ums_menu m ON rmr.menu_id = m.id");
            WHERE("rmr.role_id = #{roleId}", "m.id IS NOT NULL");
            GROUP_BY("m.id");
        }}.toString();
    }

    public String getResourceListByRoleId() {
        return new SQL() {{
            SELECT("r.*");
            FROM("ums_role_resource_relation rrr");
            LEFT_OUTER_JOIN("ums_resource r ON rrr.resource_id = r.id");
            WHERE("rrr.role_id = #{roleId}", "r.id IS NOT NULL");
            GROUP_BY("r.id");
        }}.toString();
    }
}
