package net.arver.mall.mapper;

import java.util.List;
import net.arver.mall.model.SmsCouponProductRelation;
import net.arver.mall.model.SmsCouponProductRelationExample;
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

public interface SmsCouponProductRelationMapper {
    @SelectProvider(type=SmsCouponProductRelationSqlProvider.class, method="countByExample")
    long countByExample(SmsCouponProductRelationExample example);

    @DeleteProvider(type=SmsCouponProductRelationSqlProvider.class, method="deleteByExample")
    int deleteByExample(SmsCouponProductRelationExample example);

    @Delete({
        "delete from sms_coupon_product_relation",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into sms_coupon_product_relation (coupon_id, product_id, ",
        "product_name, product_sn)",
        "values (#{couponId,jdbcType=BIGINT}, #{productId,jdbcType=BIGINT}, ",
        "#{productName,jdbcType=VARCHAR}, #{productSn,jdbcType=VARCHAR})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(SmsCouponProductRelation record);

    @InsertProvider(type=SmsCouponProductRelationSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insertSelective(SmsCouponProductRelation record);

    @SelectProvider(type=SmsCouponProductRelationSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="coupon_id", property="couponId", jdbcType=JdbcType.BIGINT),
        @Result(column="product_id", property="productId", jdbcType=JdbcType.BIGINT),
        @Result(column="product_name", property="productName", jdbcType=JdbcType.VARCHAR),
        @Result(column="product_sn", property="productSn", jdbcType=JdbcType.VARCHAR)
    })
    List<SmsCouponProductRelation> selectByExample(SmsCouponProductRelationExample example);

    @Select({
        "select",
        "id, coupon_id, product_id, product_name, product_sn",
        "from sms_coupon_product_relation",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="coupon_id", property="couponId", jdbcType=JdbcType.BIGINT),
        @Result(column="product_id", property="productId", jdbcType=JdbcType.BIGINT),
        @Result(column="product_name", property="productName", jdbcType=JdbcType.VARCHAR),
        @Result(column="product_sn", property="productSn", jdbcType=JdbcType.VARCHAR)
    })
    SmsCouponProductRelation selectByPrimaryKey(Long id);

    @UpdateProvider(type=SmsCouponProductRelationSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") SmsCouponProductRelation record, @Param("example") SmsCouponProductRelationExample example);

    @UpdateProvider(type=SmsCouponProductRelationSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") SmsCouponProductRelation record, @Param("example") SmsCouponProductRelationExample example);

    @UpdateProvider(type=SmsCouponProductRelationSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(SmsCouponProductRelation record);

    @Update({
        "update sms_coupon_product_relation",
        "set coupon_id = #{couponId,jdbcType=BIGINT},",
          "product_id = #{productId,jdbcType=BIGINT},",
          "product_name = #{productName,jdbcType=VARCHAR},",
          "product_sn = #{productSn,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(SmsCouponProductRelation record);
}