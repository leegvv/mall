package net.arver.mall.service.impl;

import net.arver.mall.mapper.UmsResourceMapper;
import net.arver.mall.model.UmsResource;
import net.arver.mall.model.UmsResourceExample;
import net.arver.mall.service.UmsResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UmsResourceServiceImpl implements UmsResourceService {

    @Autowired
    private UmsResourceMapper resourceMapper;

    @Override
    public List<UmsResource> listAll() {
        return resourceMapper.selectByExample(new UmsResourceExample());
    }
}
