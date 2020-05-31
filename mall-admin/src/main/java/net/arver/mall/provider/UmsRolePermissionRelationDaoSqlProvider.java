package net.arver.mall.provider;

import net.arver.mall.model.UmsRolePermissionRelation;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;
import java.util.Map;

public class UmsRolePermissionRelationDaoSqlProvider {

    public String insertList(final Map<String, Object> map) {
        final List<UmsRolePermissionRelation> relations = (List<UmsRolePermissionRelation>) map.get("list");
        final StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO ums_role_permission_relation (role_id, permission_id) VALUES ");
        for (int i = 0; i < relations.size(); i++) {
            sql.append("(#{list[" + i + "].roleId}, #{list[" + i + "].permissionId})");
            if(i!=relations.size()-1){
                sql.append(",");
            }else {
                sql.append(";");
            }
        }
        return sql.toString();
    }

    public String getPermissionList() {
        return new SQL(){{
            SELECT("p.*");
            FROM("ums_role_permission_relation r");
            LEFT_OUTER_JOIN("ums_permission p ON r.permission_id = p.id");
            WHERE("r.role_id = #{roleId}");
        }}.toString();
    }
}
