package net.arver.mall.mapper;

import java.util.List;
import net.arver.mall.model.SmsFlashPromotion;
import net.arver.mall.model.SmsFlashPromotionExample;
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

public interface SmsFlashPromotionMapper {
    @SelectProvider(type=SmsFlashPromotionSqlProvider.class, method="countByExample")
    long countByExample(SmsFlashPromotionExample example);

    @DeleteProvider(type=SmsFlashPromotionSqlProvider.class, method="deleteByExample")
    int deleteByExample(SmsFlashPromotionExample example);

    @Delete({
        "delete from sms_flash_promotion",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into sms_flash_promotion (title, start_date, ",
        "end_date, status, create_time)",
        "values (#{title,jdbcType=VARCHAR}, #{startDate,jdbcType=DATE}, ",
        "#{endDate,jdbcType=DATE}, #{status,jdbcType=INTEGER}, #{createTime,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(SmsFlashPromotion record);

    @InsertProvider(type=SmsFlashPromotionSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insertSelective(SmsFlashPromotion record);

    @SelectProvider(type=SmsFlashPromotionSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="start_date", property="startDate", jdbcType=JdbcType.DATE),
        @Result(column="end_date", property="endDate", jdbcType=JdbcType.DATE),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<SmsFlashPromotion> selectByExample(SmsFlashPromotionExample example);

    @Select({
        "select",
        "id, title, start_date, end_date, status, create_time",
        "from sms_flash_promotion",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="start_date", property="startDate", jdbcType=JdbcType.DATE),
        @Result(column="end_date", property="endDate", jdbcType=JdbcType.DATE),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP)
    })
    SmsFlashPromotion selectByPrimaryKey(Long id);

    @UpdateProvider(type=SmsFlashPromotionSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") SmsFlashPromotion record, @Param("example") SmsFlashPromotionExample example);

    @UpdateProvider(type=SmsFlashPromotionSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") SmsFlashPromotion record, @Param("example") SmsFlashPromotionExample example);

    @UpdateProvider(type=SmsFlashPromotionSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(SmsFlashPromotion record);

    @Update({
        "update sms_flash_promotion",
        "set title = #{title,jdbcType=VARCHAR},",
          "start_date = #{startDate,jdbcType=DATE},",
          "end_date = #{endDate,jdbcType=DATE},",
          "status = #{status,jdbcType=INTEGER},",
          "create_time = #{createTime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(SmsFlashPromotion record);
}