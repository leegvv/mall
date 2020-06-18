package net.arver.mall.service.impl;

import com.github.pagehelper.PageHelper;
import net.arver.mall.dao.SmsFlashPromotionProductRelationDao;
import net.arver.mall.dto.SmsFlashPromotionProduct;
import net.arver.mall.mapper.SmsFlashPromotionProductRelationMapper;
import net.arver.mall.model.SmsFlashPromotionProductRelation;
import net.arver.mall.model.SmsFlashPromotionProductRelationExample;
import net.arver.mall.service.SmsFlashPromotionProductRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 限时购商品关联管理Service显示类
 */
@Service
public class SmsFlashPromotionProductRelationServiceImpl implements SmsFlashPromotionProductRelationService {

    @Autowired
    private SmsFlashPromotionProductRelationMapper relationMapper;

    @Autowired
    private SmsFlashPromotionProductRelationDao relationDao;

    @Override
    public int create(final List<SmsFlashPromotionProductRelation> relationList) {
        for (final SmsFlashPromotionProductRelation relation : relationList) {
            relationMapper.insert(relation);
        }
        return relationList.size();
    }

    @Override
    public int update(final Long id, final SmsFlashPromotionProductRelation relation) {
        relation.setId(id);
        return relationMapper.updateByPrimaryKey(relation);
    }

    @Override
    public int delete(final Long id) {
        return relationMapper.deleteByPrimaryKey(id);
    }

    @Override
    public SmsFlashPromotionProductRelation getItem(final Long id) {
        return relationMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<SmsFlashPromotionProduct> list(final Long flashPromotionId, final Long flashPromotionSessionId,
                                               final Integer pageNum, final Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        return relationDao.getList(flashPromotionId, flashPromotionSessionId);
    }

    @Override
    public long getCount(final Long flashPromotionId, final Long flashPromotionSessionId) {
        final SmsFlashPromotionProductRelationExample example = new SmsFlashPromotionProductRelationExample();
        example.createCriteria()
            .andFlashPromotionIdEqualTo(flashPromotionId)
            .andFlashPromotionSessionIdEqualTo(flashPromotionSessionId);
        return relationMapper.countByExample(example);
    }
}
