package net.arver.mall.service.impl;

import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import net.arver.mall.mapper.SmsHomeRecommendSubjectMapper;
import net.arver.mall.model.SmsHomeRecommendSubject;
import net.arver.mall.model.SmsHomeRecommendSubjectExample;
import net.arver.mall.service.SmsHomeRecommendSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 首页专题推荐管理Service实现类
 */
@Service
public class SmsHomeRecommendSubjectServiceImpl implements SmsHomeRecommendSubjectService {

    @Autowired
    private SmsHomeRecommendSubjectMapper recommendSubjectMapper;
    @Override
    public int create(final List<SmsHomeRecommendSubject> recommendSubjectList) {
        for (final SmsHomeRecommendSubject homeRecommendSubject : recommendSubjectList) {
            homeRecommendSubject.setRecommendStatus(1);
            homeRecommendSubject.setSort(0);
            recommendSubjectMapper.insert(homeRecommendSubject);
        }
        return recommendSubjectList.size();
    }

    @Override
    public int updateSort(final Long id, final Integer sort) {
        final SmsHomeRecommendSubject recommendSubject = new SmsHomeRecommendSubject();
        recommendSubject.setId(id);
        recommendSubject.setSort(sort);
        return recommendSubjectMapper.updateByPrimaryKeySelective(recommendSubject);
    }

    @Override
    public int delete(final List<Long> ids) {
        final SmsHomeRecommendSubjectExample example = new SmsHomeRecommendSubjectExample();
        example.createCriteria().andIdIn(ids);
        return recommendSubjectMapper.deleteByExample(example);
    }

    @Override
    public int updateRecommendStatus(final List<Long> ids, final Integer recommendStatus) {
        final SmsHomeRecommendSubjectExample example = new SmsHomeRecommendSubjectExample();
        example.createCriteria().andIdIn(ids);
        final SmsHomeRecommendSubject record = new SmsHomeRecommendSubject();
        record.setRecommendStatus(recommendStatus);
        return recommendSubjectMapper.updateByExampleSelective(record, example);
    }

    @Override
    public List<SmsHomeRecommendSubject> list(final String subjectName, final Integer recommendStatus, final Integer pageNum, final Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        final SmsHomeRecommendSubjectExample example = new SmsHomeRecommendSubjectExample();
        final SmsHomeRecommendSubjectExample.Criteria criteria = example.createCriteria();
        if (StrUtil.isNotEmpty(subjectName)) {
            criteria.andSubjectNameLike("%" + subjectName + "%");
        }
        if (recommendStatus != null) {
            criteria.andRecommendStatusEqualTo(recommendStatus);
        }
        example.setOrderByClause("sort desc");
        return recommendSubjectMapper.selectByExample(example);
    }
}
