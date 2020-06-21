package net.arver.mall.service.impl;

import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import net.arver.mall.dao.SmsCouponProductCategoryRelationDao;
import net.arver.mall.dao.SmsCouponProductRelationDao;
import net.arver.mall.dto.SmsCouponParam;
import net.arver.mall.mapper.SmsCouponMapper;
import net.arver.mall.mapper.SmsCouponProductCategoryRelationMapper;
import net.arver.mall.mapper.SmsCouponProductRelationMapper;
import net.arver.mall.model.SmsCoupon;
import net.arver.mall.model.SmsCouponExample;
import net.arver.mall.model.SmsCouponProductCategoryRelation;
import net.arver.mall.model.SmsCouponProductCategoryRelationExample;
import net.arver.mall.model.SmsCouponProductRelation;
import net.arver.mall.model.SmsCouponProductRelationExample;
import net.arver.mall.service.SmsCouponService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SmsCouponServiceImpl implements SmsCouponService {

    @Autowired
    private SmsCouponMapper couponMapper;

    @Autowired
    private SmsCouponProductRelationMapper productRelationMapper;

    @Autowired
    private SmsCouponProductCategoryRelationMapper productCategoryRelationMapper;

    @Autowired
    private SmsCouponProductRelationDao productRelationDao;

    @Autowired
    private SmsCouponProductCategoryRelationDao productCategoryRelationDao;

    @Override
    public int create(final SmsCouponParam couponParam) {
        couponParam.setCount(couponParam.getPublishCount());
        couponParam.setUseCount(0);
        couponParam.setReceiveCount(0);
        //插入优惠券表
        final int count = couponMapper.insert(couponParam);
        //插入优惠券与商品关系表
        if (couponParam.getUseType().equals(2)) {
            for (final SmsCouponProductRelation productRelation : couponParam.getProductRelationList()) {
                productRelation.setCouponId(couponParam.getId());
            }
            productRelationDao.insertList(couponParam.getProductRelationList());
        }
        //插入优惠券和商品分类关系表
        if (couponParam.getUseType().equals(1)) {
            for (final SmsCouponProductCategoryRelation productCategoryRelation : couponParam.getProductCategoryRelationList()) {
                productCategoryRelation.setCouponId(couponParam.getId());
            }
            productCategoryRelationDao.insertList(couponParam.getProductCategoryRelationList());
        }
        return count;
    }

    @Override
    public int update(final Long id, final SmsCouponParam couponParam) {
        couponParam.setId(id);
        final int count = couponMapper.updateByPrimaryKey(couponParam);
        //删除后插入优惠券和商品关系表
        if (couponParam.getUseType().equals(2)) {
            for (final SmsCouponProductRelation productRelation : couponParam.getProductRelationList()) {
                productRelation.setCouponId(couponParam.getId());
            }
            deleteProductRelation(id);
            productRelationDao.insertList(couponParam.getProductRelationList());
        }
        //删除后插入优惠券和商品分类关系表
        if (couponParam.getUseType().equals(1)) {
            for (final SmsCouponProductCategoryRelation productCategoryRelation : couponParam.getProductCategoryRelationList()) {
                productCategoryRelation.setCouponId(couponParam.getId());
            }
            deleteProductRelation(id);
            productCategoryRelationDao.insertList(couponParam.getProductCategoryRelationList());
        }

        return count;
    }

    private void deleteProductCategoryRelation(final Long id) {
        final SmsCouponProductCategoryRelationExample example = new SmsCouponProductCategoryRelationExample();
        example.createCriteria().andCouponIdEqualTo(id);
        productCategoryRelationMapper.deleteByExample(example);
    }

    private void deleteProductRelation(final Long id) {
        final SmsCouponProductRelationExample example = new SmsCouponProductRelationExample();
        example.createCriteria().andCouponIdEqualTo(id);
        productRelationMapper.deleteByExample(example);
    }

    @Override
    public int delete(final Long id) {
        //删除优惠券
        final int count = couponMapper.deleteByPrimaryKey(id);
        //删除商品关联
        deleteProductRelation(id);
        //删除商品分类关联
        deleteProductCategoryRelation(id);
        return count;
    }

    @Override
    public List<SmsCoupon> list(final String name, final Integer type, final Integer pageNum, final Integer pageSize) {
        final SmsCouponExample example = new SmsCouponExample();
        final SmsCouponExample.Criteria criteria = example.createCriteria();
        if (StrUtil.isNotEmpty(name)) {
            criteria.andNameLike("%" + name + "%");
        }
        if (type != null) {
            criteria.andTypeEqualTo(type);
        }
        PageHelper.startPage(pageNum, pageSize);
        return couponMapper.selectByExample(example);
    }

    @Override
    public SmsCouponParam getItem(final Long id) {
        final SmsCoupon smsCoupon = couponMapper.selectByPrimaryKey(id);
        final SmsCouponParam couponParam = new SmsCouponParam();
        BeanUtils.copyProperties(smsCoupon, couponParam);

        final SmsCouponProductRelationExample productExample = new SmsCouponProductRelationExample();
        productExample.createCriteria().andCouponIdEqualTo(id);
        final List<SmsCouponProductRelation> couponProductRelationList = productRelationMapper.selectByExample(productExample);
        couponParam.setProductRelationList(couponProductRelationList);

        final SmsCouponProductCategoryRelationExample categoryExample = new SmsCouponProductCategoryRelationExample();
        categoryExample.createCriteria().andCouponIdEqualTo(id);
        final List<SmsCouponProductCategoryRelation> categoryRelationList = productCategoryRelationMapper.selectByExample(categoryExample);
        couponParam.setProductCategoryRelationList(categoryRelationList);
        return couponParam;
    }
}
