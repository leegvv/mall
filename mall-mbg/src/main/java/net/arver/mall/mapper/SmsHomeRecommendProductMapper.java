package net.arver.mall.mapper;

import java.util.List;
import net.arver.mall.model.SmsHomeRecommendProduct;
import net.arver.mall.model.SmsHomeRecommendProductExample;
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

public interface SmsHomeRecommendProductMapper {
    @SelectProvider(type=SmsHomeRecommendProductSqlProvider.class, method="countByExample")
    long countByExample(SmsHomeRecommendProductExample example);

    @DeleteProvider(type=SmsHomeRecommendProductSqlProvider.class, method="deleteByExample")
    int deleteByExample(SmsHomeRecommendProductExample example);

    @Delete({
        "delete from sms_home_recommend_product",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into sms_home_recommend_product (product_id, product_name, ",
        "recommend_status, sort)",
        "values (#{productId,jdbcType=BIGINT}, #{productName,jdbcType=VARCHAR}, ",
        "#{recommendStatus,jdbcType=INTEGER}, #{sort,jdbcType=INTEGER})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(SmsHomeRecommendProduct record);

    @InsertProvider(type=SmsHomeRecommendProductSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insertSelective(SmsHomeRecommendProduct record);

    @SelectProvider(type=SmsHomeRecommendProductSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="product_id", property="productId", jdbcType=JdbcType.BIGINT),
        @Result(column="product_name", property="productName", jdbcType=JdbcType.VARCHAR),
        @Result(column="recommend_status", property="recommendStatus", jdbcType=JdbcType.INTEGER),
        @Result(column="sort", property="sort", jdbcType=JdbcType.INTEGER)
    })
    List<SmsHomeRecommendProduct> selectByExample(SmsHomeRecommendProductExample example);

    @Select({
        "select",
        "id, product_id, product_name, recommend_status, sort",
        "from sms_home_recommend_product",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="product_id", property="productId", jdbcType=JdbcType.BIGINT),
        @Result(column="product_name", property="productName", jdbcType=JdbcType.VARCHAR),
        @Result(column="recommend_status", property="recommendStatus", jdbcType=JdbcType.INTEGER),
        @Result(column="sort", property="sort", jdbcType=JdbcType.INTEGER)
    })
    SmsHomeRecommendProduct selectByPrimaryKey(Long id);

    @UpdateProvider(type=SmsHomeRecommendProductSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") SmsHomeRecommendProduct record, @Param("example") SmsHomeRecommendProductExample example);

    @UpdateProvider(type=SmsHomeRecommendProductSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") SmsHomeRecommendProduct record, @Param("example") SmsHomeRecommendProductExample example);

    @UpdateProvider(type=SmsHomeRecommendProductSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(SmsHomeRecommendProduct record);

    @Update({
        "update sms_home_recommend_product",
        "set product_id = #{productId,jdbcType=BIGINT},",
          "product_name = #{productName,jdbcType=VARCHAR},",
          "recommend_status = #{recommendStatus,jdbcType=INTEGER},",
          "sort = #{sort,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(SmsHomeRecommendProduct record);
}