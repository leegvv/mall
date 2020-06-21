package net.arver.mall.mapper;

import java.util.List;
import net.arver.mall.model.SmsCouponProductCategoryRelation;
import net.arver.mall.model.SmsCouponProductCategoryRelationExample;
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

public interface SmsCouponProductCategoryRelationMapper {
    @SelectProvider(type=SmsCouponProductCategoryRelationSqlProvider.class, method="countByExample")
    long countByExample(SmsCouponProductCategoryRelationExample example);

    @DeleteProvider(type=SmsCouponProductCategoryRelationSqlProvider.class, method="deleteByExample")
    int deleteByExample(SmsCouponProductCategoryRelationExample example);

    @Delete({
        "delete from sms_coupon_product_category_relation",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into sms_coupon_product_category_relation (coupon_id, product_category_id, ",
        "product_category_name, parent_category_name)",
        "values (#{couponId,jdbcType=BIGINT}, #{productCategoryId,jdbcType=BIGINT}, ",
        "#{productCategoryName,jdbcType=VARCHAR}, #{parentCategoryName,jdbcType=VARCHAR})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(SmsCouponProductCategoryRelation record);

    @InsertProvider(type=SmsCouponProductCategoryRelationSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insertSelective(SmsCouponProductCategoryRelation record);

    @SelectProvider(type=SmsCouponProductCategoryRelationSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="coupon_id", property="couponId", jdbcType=JdbcType.BIGINT),
        @Result(column="product_category_id", property="productCategoryId", jdbcType=JdbcType.BIGINT),
        @Result(column="product_category_name", property="productCategoryName", jdbcType=JdbcType.VARCHAR),
        @Result(column="parent_category_name", property="parentCategoryName", jdbcType=JdbcType.VARCHAR)
    })
    List<SmsCouponProductCategoryRelation> selectByExample(SmsCouponProductCategoryRelationExample example);

    @Select({
        "select",
        "id, coupon_id, product_category_id, product_category_name, parent_category_name",
        "from sms_coupon_product_category_relation",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="coupon_id", property="couponId", jdbcType=JdbcType.BIGINT),
        @Result(column="product_category_id", property="productCategoryId", jdbcType=JdbcType.BIGINT),
        @Result(column="product_category_name", property="productCategoryName", jdbcType=JdbcType.VARCHAR),
        @Result(column="parent_category_name", property="parentCategoryName", jdbcType=JdbcType.VARCHAR)
    })
    SmsCouponProductCategoryRelation selectByPrimaryKey(Long id);

    @UpdateProvider(type=SmsCouponProductCategoryRelationSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") SmsCouponProductCategoryRelation record, @Param("example") SmsCouponProductCategoryRelationExample example);

    @UpdateProvider(type=SmsCouponProductCategoryRelationSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") SmsCouponProductCategoryRelation record, @Param("example") SmsCouponProductCategoryRelationExample example);

    @UpdateProvider(type=SmsCouponProductCategoryRelationSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(SmsCouponProductCategoryRelation record);

    @Update({
        "update sms_coupon_product_category_relation",
        "set coupon_id = #{couponId,jdbcType=BIGINT},",
          "product_category_id = #{productCategoryId,jdbcType=BIGINT},",
          "product_category_name = #{productCategoryName,jdbcType=VARCHAR},",
          "parent_category_name = #{parentCategoryName,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(SmsCouponProductCategoryRelation record);
}