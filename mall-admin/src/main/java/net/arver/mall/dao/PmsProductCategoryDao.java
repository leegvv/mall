package net.arver.mall.dao;

import net.arver.mall.dto.PmsProductCategoryWithChildrenItem;
import net.arver.mall.provider.PmsProductCategoryDaoSqlProvider;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

/**
 * 商品分类自定义Dao
 */
public interface PmsProductCategoryDao {

    /**
     * 获取商品分类及其子分类
     * @return
     */
    @SelectProvider(type = PmsProductCategoryDaoSqlProvider.class, method = "listWithChildren")
    List<PmsProductCategoryWithChildrenItem> listWithChildren();
}
