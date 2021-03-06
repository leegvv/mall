package net.arver.mall.service;

import net.arver.mall.model.SmsHomeNewProduct;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 首页新品管理Service
 */
public interface SmsHomeNewProductService {

    /**
     * 添加首页推荐
     * @param homeNewProductList
     * @return
     */
    @Transactional
    int create(List<SmsHomeNewProduct> homeNewProductList);

    /**
     * 修改推荐排序
     * @param id
     * @param sort
     * @return
     */
    int updateSort(Long id, Integer sort);

    /**
     * 批量删除推荐
     * @param ids
     * @return
     */
    int delete(List<Long> ids);

    /**
     * 更新推荐状态
     * @param ids
     * @param recommendStatus
     * @return
     */
    int updateRecommendStatus(List<Long> ids, Integer recommendStatus);

    /**
     * 分页查询推荐
     * @param productName
     * @param recommendStatus
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<SmsHomeNewProduct> list(String productName, Integer recommendStatus, Integer pageNum, Integer pageSize);

}
