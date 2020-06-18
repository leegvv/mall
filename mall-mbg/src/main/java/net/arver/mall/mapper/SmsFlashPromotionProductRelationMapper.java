package net.arver.mall.mapper;

import java.util.List;
import net.arver.mall.model.SmsFlashPromotionProductRelation;
import net.arver.mall.model.SmsFlashPromotionProductRelationExample;
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

public interface SmsFlashPromotionProductRelationMapper {
    @SelectProvider(type=SmsFlashPromotionProductRelationSqlProvider.class, method="countByExample")
    long countByExample(SmsFlashPromotionProductRelationExample example);

    @DeleteProvider(type=SmsFlashPromotionProductRelationSqlProvider.class, method="deleteByExample")
    int deleteByExample(SmsFlashPromotionProductRelationExample example);

    @Delete({
        "delete from sms_flash_promotion_product_relation",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into sms_flash_promotion_product_relation (flash_promotion_id, flash_promotion_session_id, ",
        "product_id, flash_promotion_price, ",
        "flash_promotion_count, flash_promotion_limit, ",
        "sort)",
        "values (#{flashPromotionId,jdbcType=BIGINT}, #{flashPromotionSessionId,jdbcType=BIGINT}, ",
        "#{productId,jdbcType=BIGINT}, #{flashPromotionPrice,jdbcType=DECIMAL}, ",
        "#{flashPromotionCount,jdbcType=INTEGER}, #{flashPromotionLimit,jdbcType=INTEGER}, ",
        "#{sort,jdbcType=INTEGER})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(SmsFlashPromotionProductRelation record);

    @InsertProvider(type=SmsFlashPromotionProductRelationSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insertSelective(SmsFlashPromotionProductRelation record);

    @SelectProvider(type=SmsFlashPromotionProductRelationSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="flash_promotion_id", property="flashPromotionId", jdbcType=JdbcType.BIGINT),
        @Result(column="flash_promotion_session_id", property="flashPromotionSessionId", jdbcType=JdbcType.BIGINT),
        @Result(column="product_id", property="productId", jdbcType=JdbcType.BIGINT),
        @Result(column="flash_promotion_price", property="flashPromotionPrice", jdbcType=JdbcType.DECIMAL),
        @Result(column="flash_promotion_count", property="flashPromotionCount", jdbcType=JdbcType.INTEGER),
        @Result(column="flash_promotion_limit", property="flashPromotionLimit", jdbcType=JdbcType.INTEGER),
        @Result(column="sort", property="sort", jdbcType=JdbcType.INTEGER)
    })
    List<SmsFlashPromotionProductRelation> selectByExample(SmsFlashPromotionProductRelationExample example);

    @Select({
        "select",
        "id, flash_promotion_id, flash_promotion_session_id, product_id, flash_promotion_price, ",
        "flash_promotion_count, flash_promotion_limit, sort",
        "from sms_flash_promotion_product_relation",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="flash_promotion_id", property="flashPromotionId", jdbcType=JdbcType.BIGINT),
        @Result(column="flash_promotion_session_id", property="flashPromotionSessionId", jdbcType=JdbcType.BIGINT),
        @Result(column="product_id", property="productId", jdbcType=JdbcType.BIGINT),
        @Result(column="flash_promotion_price", property="flashPromotionPrice", jdbcType=JdbcType.DECIMAL),
        @Result(column="flash_promotion_count", property="flashPromotionCount", jdbcType=JdbcType.INTEGER),
        @Result(column="flash_promotion_limit", property="flashPromotionLimit", jdbcType=JdbcType.INTEGER),
        @Result(column="sort", property="sort", jdbcType=JdbcType.INTEGER)
    })
    SmsFlashPromotionProductRelation selectByPrimaryKey(Long id);

    @UpdateProvider(type=SmsFlashPromotionProductRelationSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") SmsFlashPromotionProductRelation record, @Param("example") SmsFlashPromotionProductRelationExample example);

    @UpdateProvider(type=SmsFlashPromotionProductRelationSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") SmsFlashPromotionProductRelation record, @Param("example") SmsFlashPromotionProductRelationExample example);

    @UpdateProvider(type=SmsFlashPromotionProductRelationSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(SmsFlashPromotionProductRelation record);

    @Update({
        "update sms_flash_promotion_product_relation",
        "set flash_promotion_id = #{flashPromotionId,jdbcType=BIGINT},",
          "flash_promotion_session_id = #{flashPromotionSessionId,jdbcType=BIGINT},",
          "product_id = #{productId,jdbcType=BIGINT},",
          "flash_promotion_price = #{flashPromotionPrice,jdbcType=DECIMAL},",
          "flash_promotion_count = #{flashPromotionCount,jdbcType=INTEGER},",
          "flash_promotion_limit = #{flashPromotionLimit,jdbcType=INTEGER},",
          "sort = #{sort,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(SmsFlashPromotionProductRelation record);
}