package net.arver.mall.service.impl;

import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import net.arver.mall.mapper.SmsHomeBrandMapper;
import net.arver.mall.model.SmsHomeBrand;
import net.arver.mall.model.SmsHomeBrandExample;
import net.arver.mall.service.SmsHomeBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 首页品牌管理Service实现类
 */
@Service
public class SmsHomeBrandServiceIimpl implements SmsHomeBrandService {

    @Autowired
    private SmsHomeBrandMapper homeBrandMapper;

    @Override
    public int create(final List<SmsHomeBrand> homeBrandList) {
        for (final SmsHomeBrand homeBrand : homeBrandList) {
            homeBrand.setRecommendStatus(1);
            homeBrand.setSort(0);
            homeBrandMapper.insert(homeBrand);
        }
        return homeBrandList.size();
    }

    @Override
    public int updateSort(final Long id, final Integer sort) {
        final SmsHomeBrand homeBrand = new SmsHomeBrand();
        homeBrand.setId(id);
        homeBrand.setSort(sort);
        return homeBrandMapper.updateByPrimaryKeySelective(homeBrand);
    }

    @Override
    public int delete(final List<Long> ids) {
        final SmsHomeBrandExample example = new SmsHomeBrandExample();
        example.createCriteria().andIdIn(ids);
        return homeBrandMapper.deleteByExample(example);
    }

    @Override
    public int updateRecommendStatus(final List<Long> ids, final Integer recommendStatus) {
        final SmsHomeBrandExample example = new SmsHomeBrandExample();
        example.createCriteria().andIdIn(ids);
        final SmsHomeBrand homeBrand = new SmsHomeBrand();
        homeBrand.setRecommendStatus(recommendStatus);
        return homeBrandMapper.updateByExampleSelective(homeBrand, example);
    }

    @Override
    public List<SmsHomeBrand> list(final String brandName, final Integer recommendStatus, final Integer pageNum, final Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        final SmsHomeBrandExample example = new SmsHomeBrandExample();
        final SmsHomeBrandExample.Criteria criteria = example.createCriteria();
        if (StrUtil.isNotEmpty(brandName)) {
            criteria.andBrandNameLike("%" + brandName + "%");
        }
        if (recommendStatus != null) {
            criteria.andRecommendStatusEqualTo(recommendStatus);
        }
        example.setOrderByClause("sort desc");
        return homeBrandMapper.selectByExample(example);
    }
}
