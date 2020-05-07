package net.arver.mall.service;

import net.arver.mall.model.UmsAdmin;
import net.arver.mall.model.UmsResource;

import java.util.List;

public interface UmsAdminCacheService {

    /**
     * 获取缓存后台用户信息
     * @param username
     * @return
     */
    UmsAdmin getAdmin(String username);

    /**
     * 设置缓存后台用户信息
     * @param admin
     */
    void setAdmin(UmsAdmin admin);

    /**
     * 获取缓存后台用户资源列表
     * @param adminId
     * @return
     */
    List<UmsResource> getResourceList(Long adminId);

    /**
     * 设置缓存后台用户资源列表
     * @param adminId
     * @param resourceList
     */
    void setResourceList(Long adminId, List<UmsResource> resourceList);
}
