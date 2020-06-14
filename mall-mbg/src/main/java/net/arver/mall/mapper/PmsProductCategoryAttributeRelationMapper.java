package net.arver.mall.mapper;

import java.util.List;
import net.arver.mall.model.PmsProductCategoryAttributeRelation;
import net.arver.mall.model.PmsProductCategoryAttributeRelationExample;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface PmsProductCategoryAttributeRelationMapper {
    @SelectProvider(type=PmsProductCategoryAttributeRelationSqlProvider.class, method="countByExample")
    long countByExample(PmsProductCategoryAttributeRelationExample example);

    @DeleteProvider(type=PmsProductCategoryAttributeRelationSqlProvider.class, method="deleteByExample")
    int deleteByExample(PmsProductCategoryAttributeRelationExample example);

    @Delete({
        "delete from pms_product_category_attribute_relation",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into pms_product_category_attribute_relation (product_category_id, product_attribute_id)",
        "values (#{productCategoryId,jdbcType=BIGINT}, #{productAttributeId,jdbcType=BIGINT})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(PmsProductCategoryAttributeRelation record);

    @InsertProvider(type=PmsProductCategoryAttributeRelationSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insertSelective(PmsProductCategoryAttributeRelation record);

    @SelectProvider(type=PmsProductCategoryAttributeRelationSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="product_category_id", property="productCategoryId", jdbcType=JdbcType.BIGINT),
        @Result(column="product_attribute_id", property="productAttributeId", jdbcType=JdbcType.BIGINT)
    })
    List<PmsProductCategoryAttributeRelation> selectByExample(PmsProductCategoryAttributeRelationExample example);

    @Select({
        "select",
        "id, product_category_id, product_attribute_id",
        "from pms_product_category_attribute_relation",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="product_category_id", property="productCategoryId", jdbcType=JdbcType.BIGINT),
        @Result(column="product_attribute_id", property="productAttributeId", jdbcType=JdbcType.BIGINT)
    })
    PmsProductCategoryAttributeRelation selectByPrimaryKey(Long id);

    @UpdateProvider(type=PmsProductCategoryAttributeRelationSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") PmsProductCategoryAttributeRelation record, @Param("example") PmsProductCategoryAttributeRelationExample example);

    @UpdateProvider(type=PmsProductCategoryAttributeRelationSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") PmsProductCategoryAttributeRelation record, @Param("example") PmsProductCategoryAttributeRelationExample example);

    @UpdateProvider(type=PmsProductCategoryAttributeRelationSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(PmsProductCategoryAttributeRelation record);

    @Update({
        "update pms_product_category_attribute_relation",
        "set product_category_id = #{productCategoryId,jdbcType=BIGINT},",
          "product_attribute_id = #{productAttributeId,jdbcType=BIGINT}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(PmsProductCategoryAttributeRelation record);
}