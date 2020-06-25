package net.arver.mall.service;

import net.arver.mall.model.SmsHomeBrand;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 首页品牌管理Service
 */
public interface SmsHomeBrandService {

    /**
     * 添加首页品牌推荐
     * @param homeBrandList
     * @return
     */
    @Transactional
    int create(List<SmsHomeBrand> homeBrandList);

    /**
     * 修改品牌推荐排序
     * @param id
     * @param sort
     * @return
     */
    int updateSort(Long id, Integer sort);

    /**
     * 批量删除品牌推荐
     * @param ids
     * @return
     */
    int delete(List<Long> ids);

    /**
     * 分页查询品牌推荐
     * @param ids
     * @param recommendStatus
     * @return
     */
    int updateRecommendStatus(List<Long> ids, Integer recommendStatus);

    /**
     * 分页查询品牌推荐
     * @param brandName
     * @param recommendStatus
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<SmsHomeBrand> list(String brandName, Integer recommendStatus, Integer pageNum, Integer pageSize);
}
