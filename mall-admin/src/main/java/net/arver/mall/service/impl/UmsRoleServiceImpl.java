package net.arver.mall.service.impl;

import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import net.arver.mall.dao.UmsRoleDao;
import net.arver.mall.dao.UmsRolePermissionRelationDao;
import net.arver.mall.mapper.UmsRoleMapper;
import net.arver.mall.mapper.UmsRoleMenuRelationMapper;
import net.arver.mall.mapper.UmsRolePermissionRelationMapper;
import net.arver.mall.mapper.UmsRoleResourceRelationMapper;
import net.arver.mall.model.UmsMenu;
import net.arver.mall.model.UmsPermission;
import net.arver.mall.model.UmsResource;
import net.arver.mall.model.UmsRole;
import net.arver.mall.model.UmsRoleExample;
import net.arver.mall.model.UmsRoleMenuRelation;
import net.arver.mall.model.UmsRoleMenuRelationExample;
import net.arver.mall.model.UmsRolePermissionRelation;
import net.arver.mall.model.UmsRolePermissionRelationExample;
import net.arver.mall.model.UmsRoleResourceRelation;
import net.arver.mall.model.UmsRoleResourceRelationExample;
import net.arver.mall.service.UmsAdminCacheService;
import net.arver.mall.service.UmsRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UmsRoleServiceImpl implements UmsRoleService {

    @Autowired
    private UmsRoleMapper roleMapper;

    @Autowired
    private UmsRolePermissionRelationMapper rolePermissionRelationMapper;

    @Autowired
    private UmsRoleMenuRelationMapper roleMenuRelationMapper;

    @Autowired
    private UmsRoleResourceRelationMapper roleResourceRelationMapper;

    @Autowired
    private UmsRolePermissionRelationDao rolePermissionRelationDao;

    @Autowired
    private UmsRoleDao roleDao;

    @Autowired
    private UmsAdminCacheService adminCacheService;

    @Override
    public int create(final UmsRole role) {
        role.setCreateTime(new Date());
        role.setAdminCount(0);
        role.setSort(0);
        return roleMapper.insert(role);
    }

    @Override
    public int update(final Long id, final UmsRole role) {
        role.setId(id);
        return roleMapper.updateByPrimaryKeySelective(role);
    }

    @Override
    public int delete(final List<Long> ids) {
        final UmsRoleExample example = new UmsRoleExample();
        example.createCriteria().andIdIn(ids);
        final int count = roleMapper.deleteByExample(example);
        adminCacheService.delResourceListByRoleIds(ids);
        return count;
    }

    @Override
    public List<UmsPermission> getPermissionList(final Long roleId) {
        return rolePermissionRelationDao.getPermissionList(roleId);
    }

    @Override
    public int updatePermission(final Long roleId, final List<Long> permissionIds) {
        final UmsRolePermissionRelationExample example = new UmsRolePermissionRelationExample();
        example.createCriteria().andRoleIdEqualTo(roleId);
        rolePermissionRelationMapper.deleteByExample(example);
        //批量插入新关系
        final ArrayList<UmsRolePermissionRelation> relationList = new ArrayList<>();
        for (final Long permissionId : permissionIds) {
            final UmsRolePermissionRelation relation = new UmsRolePermissionRelation();
            relation.setRoleId(roleId);
            relation.setPermissionId(permissionId);
            relationList.add(relation);
        }

        return rolePermissionRelationDao.insertList(relationList);
    }

    @Override
    public List<UmsRole> list() {
        return roleMapper.selectByExample(new UmsRoleExample());
    }

    @Override
    public List<UmsRole> list(final String keyword, final Integer pageSize, final Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        final UmsRoleExample example = new UmsRoleExample();
        if (StrUtil.isNotEmpty(keyword)) {
            example.createCriteria().andNameLike("%" + keyword + "%");
        }
        return roleMapper.selectByExample(example);
    }

    @Override
    public List<UmsMenu> getMenuList(final Long adminId) {
        return roleDao.getMenuList(adminId);
    }

    @Override
    public List<UmsMenu> listMenu(final Long roleId) {
        return roleDao.getMenuListByRoleId(roleId);
    }

    @Override
    public List<UmsResource> listResource(final Long roleId) {
        return roleDao.getResourceListByRoleId(roleId);
    }

    @Override
    public int allocMenu(final Long roleId, final List<Long> menuIds) {
        // 先删除原有关系
        final UmsRoleMenuRelationExample example = new UmsRoleMenuRelationExample();
        example.createCriteria().andRoleIdEqualTo(roleId);
        roleMenuRelationMapper.deleteByExample(example);
        //批量插入新关系
        for (final Long menuId : menuIds) {
            final UmsRoleMenuRelation relation = new UmsRoleMenuRelation();
            relation.setRoleId(roleId);
            relation.setMenuId(menuId);
            roleMenuRelationMapper.insert(relation);
        }
        return menuIds.size();
    }

    @Override
    public int allocResource(final Long roleId, final List<Long> resourceIds) {
        // 先删除原有关系
        final UmsRoleResourceRelationExample example = new UmsRoleResourceRelationExample();
        example.createCriteria().andRoleIdEqualTo(roleId);
        roleResourceRelationMapper.deleteByExample(example);
        //批量插入新关系
        for (final Long resourceId : resourceIds) {
            final UmsRoleResourceRelation relation = new UmsRoleResourceRelation();
            relation.setRoleId(roleId);
            relation.setResourceId(resourceId);
            roleResourceRelationMapper.insert(relation);
        }
        adminCacheService.delResourceListByRole(roleId);
        return resourceIds.size();
    }

}
