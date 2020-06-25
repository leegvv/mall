package net.arver.mall.mapper;

import java.util.List;
import net.arver.mall.model.SmsHomeBrand;
import net.arver.mall.model.SmsHomeBrandExample;
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

public interface SmsHomeBrandMapper {
    @SelectProvider(type=SmsHomeBrandSqlProvider.class, method="countByExample")
    long countByExample(SmsHomeBrandExample example);

    @DeleteProvider(type=SmsHomeBrandSqlProvider.class, method="deleteByExample")
    int deleteByExample(SmsHomeBrandExample example);

    @Delete({
        "delete from sms_home_brand",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into sms_home_brand (brand_id, brand_name, ",
        "recommend_status, sort)",
        "values (#{brandId,jdbcType=BIGINT}, #{brandName,jdbcType=VARCHAR}, ",
        "#{recommendStatus,jdbcType=INTEGER}, #{sort,jdbcType=INTEGER})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(SmsHomeBrand record);

    @InsertProvider(type=SmsHomeBrandSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insertSelective(SmsHomeBrand record);

    @SelectProvider(type=SmsHomeBrandSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="brand_id", property="brandId", jdbcType=JdbcType.BIGINT),
        @Result(column="brand_name", property="brandName", jdbcType=JdbcType.VARCHAR),
        @Result(column="recommend_status", property="recommendStatus", jdbcType=JdbcType.INTEGER),
        @Result(column="sort", property="sort", jdbcType=JdbcType.INTEGER)
    })
    List<SmsHomeBrand> selectByExample(SmsHomeBrandExample example);

    @Select({
        "select",
        "id, brand_id, brand_name, recommend_status, sort",
        "from sms_home_brand",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="brand_id", property="brandId", jdbcType=JdbcType.BIGINT),
        @Result(column="brand_name", property="brandName", jdbcType=JdbcType.VARCHAR),
        @Result(column="recommend_status", property="recommendStatus", jdbcType=JdbcType.INTEGER),
        @Result(column="sort", property="sort", jdbcType=JdbcType.INTEGER)
    })
    SmsHomeBrand selectByPrimaryKey(Long id);

    @UpdateProvider(type=SmsHomeBrandSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") SmsHomeBrand record, @Param("example") SmsHomeBrandExample example);

    @UpdateProvider(type=SmsHomeBrandSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") SmsHomeBrand record, @Param("example") SmsHomeBrandExample example);

    @UpdateProvider(type=SmsHomeBrandSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(SmsHomeBrand record);

    @Update({
        "update sms_home_brand",
        "set brand_id = #{brandId,jdbcType=BIGINT},",
          "brand_name = #{brandName,jdbcType=VARCHAR},",
          "recommend_status = #{recommendStatus,jdbcType=INTEGER},",
          "sort = #{sort,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(SmsHomeBrand record);
}