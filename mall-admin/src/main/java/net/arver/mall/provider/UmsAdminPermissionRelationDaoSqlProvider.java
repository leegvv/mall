package net.arver.mall.provider;

import net.arver.mall.model.UmsAdminPermissionRelation;

import java.util.List;
import java.util.Map;

public class UmsAdminPermissionRelationDaoSqlProvider {

    public String insertList(final Map<String, Object> map) {
        final List<UmsAdminPermissionRelation> relations = (List<UmsAdminPermissionRelation>) map.get("list");
        final StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO ums_admin_permission_relation (admin_id, permission_id, type) VALUES ");
        for (int i = 0; i < relations.size(); i++) {
            sql.append("(#{list[" + i + "].adminId,jdbcType=BIGINT},#{list[" + i
                + "].permissionId,jdbcType=BIGINT},#{list[" + i + "].type,jdbcType=INTEGER})");
            if(i!=relations.size()-1){
                sql.append(",");
            }else {
                sql.append(";");
            }
        }
        return sql.toString();
    }
}
