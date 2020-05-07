package net.arver.mall.service;

import net.arver.mall.model.UmsAdmin;
import net.arver.mall.model.UmsResource;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

/**
 * 后台管理员service
 */
public interface UmsAdminService {

    /**
     * 根据用户名获取后台管理员
     * @param username
     * @return
     */
    UmsAdmin getAdminByUsername(String username);

    /**
     * 登录功能
     * @param username
     * @param password
     * @return
     */
    String login(String username, String password);

    /**
     * 获取指定用户的可访问资源.
     * @param adminId
     * @return
     */
    List<UmsResource> getResourceList(Long adminId);

    /**
     * 获取用户信息.
     * @param username
     * @return
     */
    UserDetails loadUserByUsername(String username);
}
