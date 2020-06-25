package net.arver.mall.service.impl;

import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import net.arver.mall.mapper.SmsHomeRecommendProductMapper;
import net.arver.mall.model.SmsHomeRecommendProduct;
import net.arver.mall.model.SmsHomeRecommendProductExample;
import net.arver.mall.service.SmsHomeRecommendProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 首页人气推荐管理Service实现类
 */
@Service
public class SmsHomeRecommendProductServiceImpl implements SmsHomeRecommendProductService {

    @Autowired
    private SmsHomeRecommendProductMapper recommendProductMapper;

    @Override
    public int create(final List<SmsHomeRecommendProduct> homeRecommendProductList) {
        for (final SmsHomeRecommendProduct homeRecommendProduct : homeRecommendProductList) {
            homeRecommendProduct.setRecommendStatus(1);
            homeRecommendProduct.setSort(0);
            recommendProductMapper.insert(homeRecommendProduct);
        }
        return homeRecommendProductList.size();
    }

    @Override
    public int updateSort(final Long id, final Integer sort) {
        final SmsHomeRecommendProduct recommendProduct = new SmsHomeRecommendProduct();
        recommendProduct.setId(id);
        recommendProduct.setSort(sort);
        return recommendProductMapper.updateByPrimaryKeySelective(recommendProduct);
    }

    @Override
    public int delete(final List<Long> ids) {
        final SmsHomeRecommendProductExample example = new SmsHomeRecommendProductExample();
        example.createCriteria().andIdIn(ids);
        return recommendProductMapper.deleteByExample(example);
    }

    @Override
    public int updateRecommendStatus(final List<Long> ids, final Integer recommendStatus) {
        final SmsHomeRecommendProductExample example = new SmsHomeRecommendProductExample();
        example.createCriteria().andIdIn(ids);
        final SmsHomeRecommendProduct record = new SmsHomeRecommendProduct();
        record.setRecommendStatus(recommendStatus);
        return recommendProductMapper.updateByExampleSelective(record, example);
    }

    @Override
    public List<SmsHomeRecommendProduct> list(final String productName, final Integer recommendStatus, final Integer pageNum, final Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        final SmsHomeRecommendProductExample example = new SmsHomeRecommendProductExample();
        final SmsHomeRecommendProductExample.Criteria criteria = example.createCriteria();
        if (StrUtil.isNotEmpty(productName)) {
            criteria.andProductNameLike("%" + productName + "%");
        }
        if (recommendStatus != null) {
            criteria.andRecommendStatusEqualTo(recommendStatus);
        }
        example.setOrderByClause("sort desc");
        return recommendProductMapper.selectByExample(example);
    }
}
