package net.arver.mall.service;

import net.arver.mall.model.UmsResource;

import java.util.List;

public interface UmsResourceService {

    /**
     * 查询全部资源.
     * @return
     */
    List<UmsResource> listAll();
}
