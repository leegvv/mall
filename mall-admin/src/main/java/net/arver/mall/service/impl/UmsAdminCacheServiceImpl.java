package net.arver.mall.service.impl;

import cn.hutool.core.collection.CollUtil;
import net.arver.mall.dao.UmsAdminRoleRelationDao;
import net.arver.mall.model.UmsAdmin;
import net.arver.mall.model.UmsResource;
import net.arver.mall.security.service.RedisService;
import net.arver.mall.service.UmsAdminCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UmsAdminCacheServiceImpl implements UmsAdminCacheService {

    @Value("${redis.database}")
    private String REDIS_DATABASE;

    @Value("${redis.expire.common}")
    private Long REDIS_EXPIRE;

    @Value("${redis.key.admin}")
    private String REDIS_KEY_ADMIN;

    @Value("${redis.key.resourceList}")
    private String REDIS_KEY_RESOURCE_LIST;

    @Autowired
    private RedisService redisService;

    @Autowired
    private UmsAdminRoleRelationDao adminRoleRelationDao;

    @Override
    public void delResourceListByResource(final Long resourceId) {
        final List<Long> adminIdList = adminRoleRelationDao.getAdminIdList(resourceId);
        if (CollUtil.isNotEmpty(adminIdList)) {
            final String keyPrefix = REDIS_DATABASE + ":" + REDIS_KEY_RESOURCE_LIST + ":";
            final List<String> keys = adminIdList.stream()
                .map(adminId -> keyPrefix + adminId).collect(Collectors.toList());
            redisService.del(keys);
        }

    }

    @Override
    public UmsAdmin getAdmin(final String username) {
        final String key = REDIS_DATABASE + ":" + REDIS_KEY_ADMIN + ":" + username;
        return (UmsAdmin) redisService.get(key);
    }

    @Override
    public void setAdmin(final UmsAdmin admin) {
        final String key = REDIS_DATABASE + ":" + REDIS_KEY_ADMIN + ":" + admin.getUsername();
        redisService.set(key, admin, REDIS_EXPIRE);
    }

    @Override
    public List<UmsResource> getResourceList(final Long adminId) {
        final String key = REDIS_DATABASE + ":" + REDIS_KEY_RESOURCE_LIST + ":" + adminId;
        return (List<UmsResource>) redisService.get(key);
    }

    @Override
    public void setResourceList(final Long adminId, final List<UmsResource> resourceList) {
        final String key = REDIS_DATABASE + ":" + REDIS_KEY_RESOURCE_LIST + ":" + adminId;
        redisService.set(key, resourceList, REDIS_EXPIRE);
    }
}
