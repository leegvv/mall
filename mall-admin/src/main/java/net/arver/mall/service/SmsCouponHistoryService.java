package net.arver.mall.service;

import net.arver.mall.model.SmsCouponHistory;

import java.util.List;

/**
 * 优惠券领取记录管理Service
 */
public interface SmsCouponHistoryService {

    /**
     * 分页查询优惠券领取记录
     * @param couponId
     * @param useStatus
     * @param orderSn
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<SmsCouponHistory> list(Long couponId, Integer useStatus, String orderSn, Integer pageNum, Integer pageSize);
}
