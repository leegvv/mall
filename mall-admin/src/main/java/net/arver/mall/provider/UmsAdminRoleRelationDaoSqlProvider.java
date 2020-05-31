package net.arver.mall.provider;

import net.arver.mall.model.UmsAdminRoleRelation;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;
import java.util.Map;

public class UmsAdminRoleRelationDaoSqlProvider {

    public String insertList(final Map<String, Object> map) {
        final List<UmsAdminRoleRelation> relations = (List<UmsAdminRoleRelation>) map.get("list");
        final StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO ums_admin_role_relation (admin_id, role_id) VALUES ");
        for (int i = 0; i < relations.size(); i++) {
            sql.append("(#{list[" + i + "].adminId}, #{list[" + i + "].roleId})");
            if(i!=relations.size()-1){
                sql.append(",");
            }else {
                sql.append(";");
            }
        }
        return sql.toString();
    }

    public String getRoleList(@Param("adminId") Long adminId) {
        return new SQL(){{
            SELECT("r.*");
            FROM("ums_admin_role_relation ar");
            LEFT_OUTER_JOIN("ums_role r on ar.role_id = r.id");
            WHERE("ar.admin_id = #{adminId}");
        }}.toString();
    }

    public String getRolePermissionList(@Param("adminId") Long adminId) {
        return new SQL(){{
            SELECT("p.*");
            FROM("ums_admin_role_relation ar");
            LEFT_OUTER_JOIN("ums_role r on ar.role_id = r.id");
            LEFT_OUTER_JOIN("ums_role_permission_relation rp on r.id = rp.role_id");
            LEFT_OUTER_JOIN("ums_permission p on rp.permission_id=p.id");
            WHERE("ar.admin_id = #{adminId} and p.id is not null");
        }}.toString();
    }

    public String getPermissionList(@Param("adminId") Long adminId) {
        final StringBuilder sb = new StringBuilder();
        sb.append("SELECT p.* FROM ums_admin_role_relation ar ");
        sb.append("LEFT JOIN ums_role r ON ar.role_id = r.id ");
        sb.append("LEFT JOIN ums_role_permission_relation rp ON r.id = rp.role_id ");
        sb.append("LEFT JOIN ums_permission p ON rp.permission_id = p.id ");
        sb.append("WHERE ar.admin_id = #{adminId} AND p.id IS NOT NULL ");
        sb.append("AND p.id NOT IN (SELECT p.id FROM ums_admin_permission_relation pr ");
        sb.append("LEFT JOIN ums_permission p ON pr.permission_id = p.id WHERE pr.type = - 1 ");
        sb.append("AND pr.admin_id = #{adminId}) ");
        sb.append("UNION SELECT p.* FROM ums_admin_permission_relation pr ");
        sb.append("LEFT JOIN ums_permission p ON pr.permission_id = p.id WHERE pr.type = 1 ");
        sb.append("AND pr.admin_id = #{adminId}");
        return sb.toString();
    }

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
