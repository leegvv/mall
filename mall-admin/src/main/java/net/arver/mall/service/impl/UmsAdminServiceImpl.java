package net.arver.mall.service.impl;

import cn.hutool.core.collection.CollUtil;
import net.arver.mall.bo.AdminUserDetails;
import net.arver.mall.dao.UmsAdminRoleRelationDao;
import net.arver.mall.mapper.UmsAdminLoginLogMapper;
import net.arver.mall.mapper.UmsAdminMapper;
import net.arver.mall.model.UmsAdmin;
import net.arver.mall.model.UmsAdminExample;
import net.arver.mall.model.UmsAdminLoginLog;
import net.arver.mall.model.UmsResource;
import net.arver.mall.security.util.JwtTokenUtil;
import net.arver.mall.service.UmsAdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * UmsAdminService实现类
 */
@Service
public class UmsAdminServiceImpl implements UmsAdminService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UmsAdminServiceImpl.class);

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UmsAdminCacheServiceImpl adminCacheService;

    @Autowired
    private UmsAdminMapper adminMapper;

    @Autowired
    private UmsAdminRoleRelationDao adminRoleRelationDao;

    @Autowired
    private UmsAdminLoginLogMapper loginLogMapper;


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
    public String login(final String username, final String password) {
        String token = null;
        try {
            final UserDetails userDetails = loadUserByUsername(username);
            if (!passwordEncoder.matches(password, userDetails.getPassword())) {
                throw new BadCredentialsException("密码不正确");
            }
            final UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            token = jwtTokenUtil.generateToken(userDetails);
            insertLoginLog(username);
        } catch (AuthenticationException e) {
            LOGGER.warn("登录异常：{}", e.getMessage());
        }
        return token;
    }

    /**
     * 添加登录记录
     * @param username
     */
    private void insertLoginLog(final String username) {
        final UmsAdmin admin = getAdminByUsername(username);
        if (admin == null) {
            return;
        }
        final UmsAdminLoginLog loginLog = new UmsAdminLoginLog();
        loginLog.setAdminId(admin.getId());
        loginLog.setCreateTime(new Date());
        final ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        final HttpServletRequest request = attributes.getRequest();
        loginLog.setIp(request.getRemoteAddr());
        loginLogMapper.insert(loginLog);
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
