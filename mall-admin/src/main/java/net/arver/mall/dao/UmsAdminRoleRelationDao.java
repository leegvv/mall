package net.arver.mall.dao;

import net.arver.mall.model.UmsResource;
import net.arver.mall.provider.UmsAdminRoleRelationDaoSqlProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

/**
 * 后台用户与角色管理自定义Dao
 */
public interface UmsAdminRoleRelationDao {

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
