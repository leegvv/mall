package net.arver.mall.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.github.pagehelper.PageHelper;
import net.arver.mall.dao.PmsProductCategoryAttributeRelationDao;
import net.arver.mall.dao.PmsProductCategoryDao;
import net.arver.mall.dto.PmsProductCategoryParam;
import net.arver.mall.dto.PmsProductCategoryWithChildrenItem;
import net.arver.mall.mapper.PmsProductCategoryAttributeRelationMapper;
import net.arver.mall.mapper.PmsProductCategoryMapper;
import net.arver.mall.mapper.PmsProductMapper;
import net.arver.mall.model.PmsProduct;
import net.arver.mall.model.PmsProductCategory;
import net.arver.mall.model.PmsProductCategoryAttributeRelation;
import net.arver.mall.model.PmsProductCategoryAttributeRelationExample;
import net.arver.mall.model.PmsProductCategoryExample;
import net.arver.mall.model.PmsProductExample;
import net.arver.mall.service.PmsProductCategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 商品分类服务实现
 */
@Service
public class PmsProductCategoryServiceImpl implements PmsProductCategoryService {

    @Autowired
    private PmsProductCategoryMapper productCategoryMapper;

    @Autowired
    private PmsProductMapper productMapper;

    @Autowired
    private PmsProductCategoryAttributeRelationDao productCategoryAttributeRelationDao;

    @Autowired
    private PmsProductCategoryAttributeRelationMapper productCategoryAttributeRelationMapper;

    @Autowired
    private PmsProductCategoryDao productCategoryDao;

    @Override
    public int create(final PmsProductCategoryParam pmsProductCategoryParam) {
        final PmsProductCategory productCategory = new PmsProductCategory();
        productCategory.setProductCount(0);
        BeanUtils.copyProperties(pmsProductCategoryParam, productCategory);
        // 没有父分类是为一级分类
        setCategoryLevel(productCategory);
        final int count = productCategoryMapper.insertSelective(productCategory);
        //创建筛选属性关联
        final List<Long> productAttributeIdList = pmsProductCategoryParam.getProductAttributeIdList();
        if (CollUtil.isNotEmpty(productAttributeIdList)) {
            insertRelationList(productCategory.getId(), productAttributeIdList);
        }
        return count;
    }

    /**
     * 批量插入商品分类与筛选属性关系表
     * @param productCategoryId
     * @param productAttributeIdList
     */
    private void insertRelationList(final Long productCategoryId, final List<Long> productAttributeIdList) {
        final List<PmsProductCategoryAttributeRelation> relationList = new ArrayList<>();
        for (final Long productAttrId : productAttributeIdList) {
            final PmsProductCategoryAttributeRelation relation = new PmsProductCategoryAttributeRelation();
            relation.setProductCategoryId(productCategoryId);
            relation.setProductAttributeId(productAttrId);
            relationList.add(relation);
        }
        productCategoryAttributeRelationDao.insertList(relationList);
    }

    @Override
    public int update(final Long id, final PmsProductCategoryParam pmsProductCategoryParam) {
        final PmsProductCategory productCategory = new PmsProductCategory();
        productCategory.setId(id);
        BeanUtils.copyProperties(pmsProductCategoryParam, productCategory);
        setCategoryLevel(productCategory);
        //更新商品分类时要更新商品中的名称
        final PmsProduct product = new PmsProduct();
        product.setProductCategoryName(productCategory.getName());
        final PmsProductExample example = new PmsProductExample();
        example.createCriteria().andProductCategoryIdEqualTo(id);
        productMapper.updateByExampleSelective(product, example);
        //同时更新筛选属性的信息
        if (CollUtil.isNotEmpty(pmsProductCategoryParam.getProductAttributeIdList())) {
            final PmsProductCategoryAttributeRelationExample relationExample = new PmsProductCategoryAttributeRelationExample();
            relationExample.createCriteria().andProductCategoryIdEqualTo(id);
            productCategoryAttributeRelationMapper.deleteByExample(relationExample);
            insertRelationList(id, pmsProductCategoryParam.getProductAttributeIdList());
        } else {
            final PmsProductCategoryAttributeRelationExample relationExample = new PmsProductCategoryAttributeRelationExample();
            relationExample.createCriteria().andProductCategoryIdEqualTo(id);
            productCategoryAttributeRelationMapper.deleteByExample(relationExample);
        }
        return productCategoryMapper.updateByPrimaryKeySelective(productCategory);
    }

    @Override
    public List<PmsProductCategory> list(final Long parentId, final Integer pageNum, final Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        final PmsProductCategoryExample example = new PmsProductCategoryExample();
        example.setOrderByClause("sort desc");
        example.createCriteria().andParentIdEqualTo(parentId);
        return productCategoryMapper.selectByExample(example);
    }

    @Override
    public int delete(final Long id) {
        return productCategoryMapper.deleteByPrimaryKey(id);
    }

    @Override
    public PmsProductCategory getItem(final Long id) {
        return productCategoryMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateNavStatus(final List<Long> ids, final Integer navStatus) {
        final PmsProductCategory productCategory = new PmsProductCategory();
        productCategory.setNavStatus(navStatus);
        final PmsProductCategoryExample example = new PmsProductCategoryExample();
        example.createCriteria().andIdIn(ids);
        return productCategoryMapper.updateByExampleSelective(productCategory, example);
    }

    @Override
    public int updateShowStatus(final List<Long> ids, final Integer showStatus) {
        final PmsProductCategory productCategory = new PmsProductCategory();
        productCategory.setShowStatus(showStatus);
        final PmsProductCategoryExample example = new PmsProductCategoryExample();
        example.createCriteria().andIdIn(ids);
        return productCategoryMapper.updateByExampleSelective(productCategory, example);
    }

    @Override
    public List<PmsProductCategoryWithChildrenItem> listWithChildren() {
        return productCategoryDao.listWithChildren();
    }

    /**
     * 根据分类的parentId设置分类的level
     * @param productCategory
     */
    private void setCategoryLevel(final PmsProductCategory productCategory) {
        //没有父分类时为一级分类
        if (productCategory.getParentId() == 0) {
            productCategory.setLevel(0);
        } else {
            //有父分类时选择根据父分类leve设置
            final PmsProductCategory parentCategory = productCategoryMapper.selectByPrimaryKey(productCategory.getParentId());
            if (parentCategory != null) {
                productCategory.setLevel(parentCategory.getLevel() + 1);
            } else {
                productCategory.setLevel(0);
            }
        }
    }
}
