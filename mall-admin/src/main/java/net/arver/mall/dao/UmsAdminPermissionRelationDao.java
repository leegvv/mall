package net.arver.mall.dao;

import net.arver.mall.model.UmsAdminPermissionRelation;
import net.arver.mall.provider.UmsAdminPermissionRelationDaoSqlProvider;
import org.apache.ibatis.annotations.InsertProvider;

import java.util.List;

public interface UmsAdminPermissionRelationDao {

    /**
     * 批量创建
     * @param list
     * @return
     */
    @InsertProvider(type = UmsAdminPermissionRelationDaoSqlProvider.class, method = "insertList")
    int insertList(List<UmsAdminPermissionRelation> list);
}
