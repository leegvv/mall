package net.arver.mall.service;

import net.arver.mall.dto.SmsCouponParam;
import net.arver.mall.model.SmsCoupon;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 优惠券管理Service
 */
public interface SmsCouponService {

    /**
     * 添加优惠券.
     * @param couponParam
     * @return
     */
    @Transactional
    int create(SmsCouponParam couponParam);

    /**
     * 更新优惠券
     * @param id
     * @param couponParam
     * @return
     */
    @Transactional
    int update(Long id, SmsCouponParam couponParam);

    /**
     * 根据优惠券id删除优惠券
     * @param id
     * @return
     */
    @Transactional
    int delete(Long id);

    /**
     * 分页获取优惠券列表
     * @param name
     * @param type
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<SmsCoupon> list(String name, Integer type, Integer pageNum, Integer pageSize);

    /**
     * 获取优惠券详情
     * @param id
     * @return
     */
    SmsCouponParam getItem(Long id);
}
