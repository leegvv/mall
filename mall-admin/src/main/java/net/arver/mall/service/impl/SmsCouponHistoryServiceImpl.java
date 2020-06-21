package net.arver.mall.service.impl;

import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import net.arver.mall.mapper.SmsCouponHistoryMapper;
import net.arver.mall.model.SmsCouponHistory;
import net.arver.mall.model.SmsCouponHistoryExample;
import net.arver.mall.service.SmsCouponHistoryService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 优惠券领取记录管理Service实现类
 */
public class SmsCouponHistoryServiceImpl implements SmsCouponHistoryService {

    @Autowired
    private SmsCouponHistoryMapper historyMapper;

    @Override
    public List<SmsCouponHistory> list(final Long couponId, final Integer useStatus, final String orderSn, final Integer pageNum, final Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        final SmsCouponHistoryExample example = new SmsCouponHistoryExample();
        final SmsCouponHistoryExample.Criteria criteria = example.createCriteria();
        if (couponId != null) {
            criteria.andCouponIdEqualTo(couponId);
        }
        if (useStatus != null) {
            criteria.andUseStatusEqualTo(useStatus);
        }
        if (StrUtil.isNotEmpty(orderSn)) {
            criteria.andOrderSnEqualTo(orderSn);
        }
        return historyMapper.selectByExample(example);
    }
}
