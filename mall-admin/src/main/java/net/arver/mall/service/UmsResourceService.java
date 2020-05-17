package net.arver.mall.service;

import net.arver.mall.model.UmsResource;

import java.util.List;

public interface UmsResourceService {

    /**
     * 添加资源
     * @param umsResource
     * @return
     */
    int crate(UmsResource umsResource);

    /**
     * 更新资源.
     * @param id
     * @param umsResource
     * @return
     */
    int update(Long id, UmsResource umsResource);

    /**
     * 获取资源详情
     * @param id
     * @return
     */
    UmsResource getItem(Long id);

    /**
     * 删除资源
     * @param id
     * @return
     */
    int delete(Long id);

    /**
     * 分页查询资源
     * @param categoryId
     * @param nameKeyword
     * @param urlKeyword
     * @param pageSize
     * @param pageNum
     * @return
     */
    List<UmsResource> list(Long categoryId, String nameKeyword, String urlKeyword, Integer pageSize, Integer pageNum);


    /**
     * 查询全部资源.
     * @return
     */
    List<UmsResource> listAll();
}
