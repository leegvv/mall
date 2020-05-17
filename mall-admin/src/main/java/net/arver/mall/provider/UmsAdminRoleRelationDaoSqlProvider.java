package net.arver.mall.provider;

import org.apache.ibatis.jdbc.SQL;

public class UmsAdminRoleRelationDaoSqlProvider {

    public String getResourceList(final Long adminId){
        return new SQL(){{
            SELECT("ur.id id, ur.create_time create_time, ur.name name, ur.url url,"
                + " ur.description description, ur.category_id category_id");
            FROM("ums_admin_role_relation ar");
            LEFT_OUTER_JOIN(
                "ums_role r on ar.role_id = r.id",
                "ums_role_resource_relation rrr on r.id = rrr.role_id",
                "ums_resource ur on ur.id = rrr.resource_id"
            );
            WHERE("ar.admin_id = #{adminId}", "ur.id IS NOT NULL");
            GROUP_BY("ur.id");
        }}.toString();
    }

    public String getAdminIdList(final Long resourceId) {
        return new SQL(){{
            SELECT_DISTINCT("ar.admin_id");
            FROM("ums_role_resource_relation rr");
            LEFT_OUTER_JOIN("ums_admin-role_relation ar on rr.role_id = ar.role_id");
            WHERE("rr.resource_id = #{resourceId}");

        }}.toString();
    }
}
