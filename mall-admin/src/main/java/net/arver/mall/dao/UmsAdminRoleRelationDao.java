package net.arver.mall.dao;

import net.arver.mall.model.UmsAdminRoleRelation;
import net.arver.mall.model.UmsPermission;
import net.arver.mall.model.UmsResource;
import net.arver.mall.model.UmsRole;
import net.arver.mall.provider.UmsAdminRoleRelationDaoSqlProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

/**
 * 后台用户与角色管理自定义Dao
 */
public interface UmsAdminRoleRelationDao {

    /**
     * 批量插入用户角色关系
     * @param list
     * @return
     */
    @InsertProvider(type = UmsAdminRoleRelationDaoSqlProvider.class, method = "insertList")
    int insertList(@Param("list") List<UmsAdminRoleRelation> list);

    /**
     * 获取用户所有角色
     * @param adminId
     * @return
     */
    @SelectProvider(type = UmsAdminRoleRelationDaoSqlProvider.class, method = "getRoleList")
    List<UmsRole> getRoleList(Long adminId);

    /**
     * 获取用户所有角色权限
     * @param adminId
     * @return
     */
    @SelectProvider(type = UmsAdminRoleRelationDaoSqlProvider.class, method = "getRolePermissionList")
    List<UmsPermission> getRolePermissionList(Long adminId);

    /**
     * 获取用户所有权限（包括+-权限）
     * @param adminId
     * @return
     */
    @SelectProvider(type = UmsAdminRoleRelationDaoSqlProvider.class, method = "getPermissionList")
    List<UmsPermission> getPermissionList(Long adminId);

    /**
     * 获取用户所有可访问资源
     * @param adminId
     * @return
     */
    @SelectProvider(type = UmsAdminRoleRelationDaoSqlProvider.class, method = "getResourceList")
    List<UmsResource> getResourceList(@Param("adminId") Long adminId);

    /**
     * 获取资源相关用户ID列表
     * @param resourceId
     * @return
     */
    @SelectProvider(type = UmsAdminRoleRelationDaoSqlProvider.class, method = "getAdminIdList")
    List<Long> getAdminIdList(Long resourceId);


}
