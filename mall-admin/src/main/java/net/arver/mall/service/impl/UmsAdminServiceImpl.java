package net.arver.mall.service.impl;

import cn.hutool.core.collection.CollUtil;
import net.arver.mall.bo.AdminUserDetails;
import net.arver.mall.dao.UmsAdminRoleRelationDao;
import net.arver.mall.mapper.UmsAdminMapper;
import net.arver.mall.model.UmsAdmin;
import net.arver.mall.model.UmsAdminExample;
import net.arver.mall.model.UmsResource;
import net.arver.mall.service.UmsAdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * UmsAdminService实现类
 */
@Service
public class UmsAdminServiceImpl implements UmsAdminService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UmsAdminServiceImpl.class);

    @Autowired
    private UmsAdminCacheServiceImpl adminCacheService;

    @Autowired
    private UmsAdminMapper adminMapper;

    @Autowired
    private UmsAdminRoleRelationDao adminRoleRelationDao;


    @Override
    public UmsAdmin getAdminByUsername(final String username) {
        UmsAdmin admin = adminCacheService.getAdmin(username);
        if (admin != null) {
            return admin;
        }
        final UmsAdminExample example = new UmsAdminExample();
        example.createCriteria().andUsernameEqualTo(username);
        final List<UmsAdmin> adminList = adminMapper.selectByExample(example);
        if (CollUtil.isNotEmpty(adminList)) {
            admin = adminList.get(0);
            adminCacheService.setAdmin(admin);
            return admin;
        }

        return null;
    }

    @Override
    public List<UmsResource> getResourceList(final Long adminId) {
        List<UmsResource> resourceList = adminCacheService.getResourceList(adminId);
        if (CollUtil.isNotEmpty(resourceList)) {
            return resourceList;
        }
        resourceList = adminRoleRelationDao.getResourceList(adminId);
        if (CollUtil.isNotEmpty(resourceList)) {
            adminCacheService.setResourceList(adminId, resourceList);
        }
        return resourceList;
    }

    @Override
    public UserDetails loadUserByUsername(final String username) {
        //获取用户信息
        final UmsAdmin admin = getAdminByUsername(username);
        if (admin != null) {
            final List<UmsResource> resourceList = getResourceList(admin.getId());
            return new AdminUserDetails(admin, resourceList);
        }
        throw new UsernameNotFoundException("用户名或密码错误");
    }
}
