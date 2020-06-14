package net.arver.mall.service;

import net.arver.mall.dto.PmsBrandParam;
import net.arver.mall.model.PmsBrand;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 商品品牌servic
 */
public interface PmsBrandService {

    /**
     * 获取所有品牌.
     * @return
     */
    List<PmsBrand> listAllBrand();

    /**
     * 创建品牌
     * @param pmsBrandParam
     * @return
     */
    int createBrand(PmsBrandParam pmsBrandParam);

    /**
     * 更新品牌
     * @param id
     * @param pmsBrandParam
     * @return
     */
    @Transactional
    int updateBrand(Long id, PmsBrandParam pmsBrandParam);

    /**
     * 删除品牌
     * @param id
     * @return
     */
    int deleteBrand(Long id);

    /**
     * 批量删除品牌
     * @param ids
     * @return
     */
    int deleteBrand(List<Long> ids);

    /**
     * 分页查询品牌
     * @param keyword
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<PmsBrand> listBrand(String keyword, int pageNum, int pageSize);

    /**
     * 获取品牌
     * @param id
     * @return
     */
    PmsBrand getBrand(Long id);

    /**
     * 修改显示状态
     * @param ids
     * @param showStatus
     * @return
     */
    int updateShowStatus(List<Long> ids, Integer showStatus);

    /**
     * 修改厂家制造状态
     * @param ids
     * @param factoryStatus
     * @return
     */
    int updateFactoryStatus(List<Long> ids, Integer factoryStatus);

}
