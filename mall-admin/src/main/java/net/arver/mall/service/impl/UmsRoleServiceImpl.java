package net.arver.mall.service.impl;

import net.arver.mall.dao.UmsRoleDao;
import net.arver.mall.model.UmsMenu;
import net.arver.mall.service.UmsRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UmsRoleServiceImpl implements UmsRoleService {

    @Autowired
    private UmsRoleDao roleDao;

    @Override
    public List<UmsMenu> getMenuList(final Long adminId) {
        return roleDao.getMenuList(adminId);
    }
}
