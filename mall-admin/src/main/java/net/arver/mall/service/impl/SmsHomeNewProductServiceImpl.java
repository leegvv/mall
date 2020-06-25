package net.arver.mall.service.impl;

import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import net.arver.mall.mapper.SmsHomeNewProductMapper;
import net.arver.mall.model.SmsHomeNewProduct;
import net.arver.mall.model.SmsHomeNewProductExample;
import net.arver.mall.service.SmsHomeNewProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 首页新品推荐管理Service实现类
 */
@Service
public class SmsHomeNewProductServiceImpl implements SmsHomeNewProductService {

    @Autowired
    private SmsHomeNewProductMapper homeNewProductMapper;

    @Override
    public int create(final List<SmsHomeNewProduct> homeNewProductList) {
        for (final SmsHomeNewProduct smsHomeNewProduct : homeNewProductList) {
            smsHomeNewProduct.setRecommendStatus(1);
            smsHomeNewProduct.setSort(0);
            homeNewProductMapper.insert(smsHomeNewProduct);
        }
        return homeNewProductList.size();
    }

    @Override
    public int updateSort(final Long id, final Integer sort) {
        final SmsHomeNewProduct homeNewProduct = new SmsHomeNewProduct();
        homeNewProduct.setId(id);
        homeNewProduct.setSort(sort);
        return homeNewProductMapper.updateByPrimaryKeySelective(homeNewProduct);
    }

    @Override
    public int delete(final List<Long> ids) {
        final SmsHomeNewProductExample example = new SmsHomeNewProductExample();
        example.createCriteria().andIdIn(ids);
        return homeNewProductMapper.deleteByExample(example);
    }

    @Override
    public int updateRecommendStatus(final List<Long> ids, final Integer recommendStatus) {
        final SmsHomeNewProductExample example = new SmsHomeNewProductExample();
        example.createCriteria().andIdIn(ids);
        final SmsHomeNewProduct record = new SmsHomeNewProduct();
        record.setRecommendStatus(recommendStatus);
        return homeNewProductMapper.updateByExampleSelective(record, example);
    }

    @Override
    public List<SmsHomeNewProduct> list(final String productName, final Integer recommendStatus, final Integer pageNum, final Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        final SmsHomeNewProductExample example = new SmsHomeNewProductExample();
        final SmsHomeNewProductExample.Criteria criteria = example.createCriteria();
        if (StrUtil.isNotEmpty(productName)) {
            criteria.andProductNameLike("%" + productName + "%");
        }
        if (recommendStatus != null) {
            criteria.andRecommendStatusEqualTo(recommendStatus);
        }
        example.setOrderByClause("sort desc");
        return homeNewProductMapper.selectByExample(example);
    }
}
