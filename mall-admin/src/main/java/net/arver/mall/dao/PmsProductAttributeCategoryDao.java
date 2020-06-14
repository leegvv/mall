package net.arver.mall.dao;

import net.arver.mall.dto.PmsProductAttributeCategoryItem;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

public interface PmsProductAttributeCategoryDao {

    /**
     * 获取包含属性的商品属性分类
     * @return
     */
    @Select("select * from pms_product_attribute_category")
    @Results({
        @Result(column="id", property="id", jdbcType= JdbcType.BIGINT, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="attribute_count", property="attributeCount", jdbcType=JdbcType.INTEGER),
        @Result(column="param_count", property="paramCount", jdbcType=JdbcType.INTEGER),
        @Result(property="productAttributeList",javaType = List.class, column = "product_attribute_category_id",
            many = @Many(select = "net.arver.mall.dao.PmsProductAttributeDao.getProductAttributesByCategoryId"))
    })
    List<PmsProductAttributeCategoryItem> getListWithAttr();
}
