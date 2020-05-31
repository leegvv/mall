package net.arver.mall.dao;

import net.arver.mall.model.UmsPermission;
import net.arver.mall.model.UmsRolePermissionRelation;
import net.arver.mall.provider.UmsRolePermissionRelationDaoSqlProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 后台用户角色管理自定义dao
 */
public interface UmsRolePermissionRelationDao {

    /**
     * 批量插入角色和权限关系
     * @param list
     * @return
     */
    @InsertProvider(type = UmsRolePermissionRelationDaoSqlProvider.class, method = "insertList")
    int insertList(@Param("list")List<UmsRolePermissionRelation> list);

    /**
     * 根据角色获取权限
     * @param roleId
     * @return
     */
    List<UmsPermission> getPermissionList(@Param("roleId") Long roleId);
}
