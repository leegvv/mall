package net.arver.mall.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import net.arver.mall.bo.AdminUserDetails;
import net.arver.mall.dao.UmsAdminPermissionRelationDao;
import net.arver.mall.dao.UmsAdminRoleRelationDao;
import net.arver.mall.dto.UmsAdminParam;
import net.arver.mall.dto.UpdateAdminPasswordParam;
import net.arver.mall.mapper.UmsAdminLoginLogMapper;
import net.arver.mall.mapper.UmsAdminMapper;
import net.arver.mall.mapper.UmsAdminPermissionRelationMapper;
import net.arver.mall.mapper.UmsAdminRoleRelationMapper;
import net.arver.mall.model.UmsAdmin;
import net.arver.mall.model.UmsAdminExample;
import net.arver.mall.model.UmsAdminLoginLog;
import net.arver.mall.model.UmsAdminPermissionRelation;
import net.arver.mall.model.UmsAdminPermissionRelationExample;
import net.arver.mall.model.UmsAdminRoleRelation;
import net.arver.mall.model.UmsAdminRoleRelationExample;
import net.arver.mall.model.UmsPermission;
import net.arver.mall.model.UmsResource;
import net.arver.mall.model.UmsRole;
import net.arver.mall.security.util.JwtTokenUtil;
import net.arver.mall.service.UmsAdminCacheService;
import net.arver.mall.service.UmsAdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
    private UmsAdminMapper adminMapper;

    @Autowired
    private UmsAdminRoleRelationMapper adminRoleRelationMapper;

    @Autowired
    private UmsAdminRoleRelationDao adminRoleRelationDao;

    @Autowired
    private UmsAdminPermissionRelationMapper adminPermissionRelationMapper;

    @Autowired
    private UmsAdminPermissionRelationDao adminPermissionRelationDao;

    @Autowired
    private UmsAdminLoginLogMapper loginLogMapper;

    @Autowired
    private UmsAdminCacheService adminCacheService;


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
    public UmsAdmin register(final UmsAdminParam umsAdminParam) {
        final UmsAdmin umsAdmin = new UmsAdmin();
        BeanUtils.copyProperties(umsAdminParam, umsAdmin);
        umsAdmin.setCreateTime(new Date());
        umsAdmin.setStatus(1);
        //查询是否有相同用户名的用户
        final UmsAdminExample example = new UmsAdminExample();
        example.createCriteria().andUsernameEqualTo(umsAdmin.getUsername());
        final List<UmsAdmin> adminList = adminMapper.selectByExample(example);
        if (adminList.size() > 0) {
            return null;
        }
        //将密码进行加密
        final String encodePassword = passwordEncoder.encode(umsAdmin.getPassword());
        umsAdmin.setPassword(encodePassword);
        adminMapper.insert(umsAdmin);
        return umsAdmin;
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
    public String refreshToken(final String oldToken) {
        return jwtTokenUtil.refreshHeadToken(oldToken);
    }

    @Override
    public UmsAdmin getItem(final Long id) {
        return adminMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<UmsAdmin> list(final String keyword, final Integer pageSize, final Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        final UmsAdminExample example = new UmsAdminExample();
        final UmsAdminExample.Criteria criteria = example.createCriteria();
        if (StrUtil.isNotEmpty(keyword)) {
            criteria.andUsernameLike("%" + keyword + "%");
            example.or(example.createCriteria().andNickNameLike("%" + keyword + "%"));
        }
        return adminMapper.selectByExample(example);
    }

    @Override
    public int update(final Long id, final UmsAdmin admin) {
        admin.setId(id);
        final UmsAdmin rawAdmin = adminMapper.selectByPrimaryKey(id);
        if (rawAdmin.getPassword().equals(admin.getPassword())) {
            //与原加密密码相同的不需要修改
            admin.setPassword(null);
        } else {
            // 与原加密密码不同的需要加密修改
            if (StrUtil.isEmpty(admin.getPassword())) {
                admin.setPassword(null);
            } else {
                admin.setPassword(passwordEncoder.encode(admin.getPassword()));
            }
        }
        final int count = adminMapper.updateByPrimaryKeySelective(admin);
        adminCacheService.delAdmin(id);
        return count;
    }

    @Override
    public int delete(final Long id) {
        adminCacheService.delAdmin(id);
        final int count = adminMapper.deleteByPrimaryKey(id);
        adminCacheService.delResourceList(id);
        return count;
    }

    @Override
    public int updateRole(final Long adminId, final List<Long> roleIds) {
        final int count = roleIds == null ? 0 : roleIds.size();
        //先删除原来的关系
        final UmsAdminRoleRelationExample example = new UmsAdminRoleRelationExample();
        example.createCriteria().andAdminIdEqualTo(adminId);
        adminRoleRelationMapper.deleteByExample(example);
        //建立新关系
        if (CollUtil.isNotEmpty(roleIds)) {
            final List<UmsAdminRoleRelation> list = new ArrayList<>();
            for (final Long roleId : roleIds) {
                final UmsAdminRoleRelation relation = new UmsAdminRoleRelation();
                relation.setAdminId(adminId);
                relation.setRoleId(roleId);
                list.add(relation);
            }
            adminRoleRelationDao.insertList(list);
        }
        adminCacheService.delResourceList(adminId);
        return count;
    }

    @Override
    public List<UmsRole> getRoleList(final Long adminId) {
        return adminRoleRelationDao.getRoleList(adminId);
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
    public int updatePermission(final Long adminId, final List<Long> permissionIds) {
        final UmsAdminPermissionRelationExample relationExample = new UmsAdminPermissionRelationExample();
        relationExample.createCriteria().andAdminIdEqualTo(adminId);
        adminPermissionRelationMapper.deleteByExample(relationExample);
        //获取用户所有角色权限
        final List<UmsPermission> permissionList = adminRoleRelationDao.getRolePermissionList(adminId);
        final List<Long> rolePermissionList = permissionList.stream()
            .map(UmsPermission::getId).collect(Collectors.toList());
        if (CollUtil.isNotEmpty(permissionIds)) {
            final ArrayList<UmsAdminPermissionRelation> relationList = new ArrayList<>();
            //筛选出+权限
            final List<Long> addPermissionList = permissionIds.stream()
                .filter(permissionId -> !rolePermissionList.contains(permissionId)).collect(Collectors.toList());
            //筛选出-权限
            final List<Long> subPermissionIdList = rolePermissionList.stream()
                .filter(permissionId -> !permissionIds.contains(permissionId)).collect(Collectors.toList());
            relationList.addAll(convert(adminId, 1, addPermissionList));
            relationList.addAll(convert(adminId, -1, subPermissionIdList));
            return adminPermissionRelationDao.insertList(relationList);
        }
        return 0;
    }

    /**
     * 将=-权限关系转化为对象
     * @param adminId
     * @param type
     * @param permissionIdList
     * @return
     */
    private List<UmsAdminPermissionRelation> convert(final Long adminId, final Integer type, final List<Long> permissionIdList) {
        final List<UmsAdminPermissionRelation> relationList = permissionIdList.stream().map(permissionId -> {
            final UmsAdminPermissionRelation relation = new UmsAdminPermissionRelation();
            relation.setAdminId(adminId);
            relation.setType(type);
            relation.setPermissionId(permissionId);
            return relation;
        }).collect(Collectors.toList());
        return relationList;
    }

    @Override
    public List<UmsPermission> getPermissionList(final Long adminId) {
        return adminRoleRelationDao.getPermissionList(adminId);
    }

    @Override
    public int updatePassword(final UpdateAdminPasswordParam param) {
        if (StrUtil.isEmpty(param.getUsername()) || StrUtil.isEmpty(param.getOldPassword())
            || StrUtil.isEmpty(param.getNewPassword())) {
            return -1;
        }
        final UmsAdminExample example = new UmsAdminExample();
        example.createCriteria().andUsernameEqualTo(param.getUsername());
        final List<UmsAdmin> adminList = adminMapper.selectByExample(example);
        if (CollUtil.isEmpty(adminList)) {
            return -2;
        }
        final UmsAdmin umsAdmin = adminList.get(0);
        if (!passwordEncoder.matches(param.getOldPassword(), umsAdmin.getPassword())) {
            return -3;
        }
        umsAdmin.setPassword(passwordEncoder.encode(param.getNewPassword()));
        adminMapper.updateByPrimaryKey(umsAdmin);
        adminCacheService.delAdmin(umsAdmin.getId());
        return 1;
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
