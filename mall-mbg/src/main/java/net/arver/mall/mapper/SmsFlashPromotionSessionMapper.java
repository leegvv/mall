package net.arver.mall.mapper;

import java.util.List;
import net.arver.mall.model.SmsFlashPromotionSession;
import net.arver.mall.model.SmsFlashPromotionSessionExample;
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

public interface SmsFlashPromotionSessionMapper {
    @SelectProvider(type=SmsFlashPromotionSessionSqlProvider.class, method="countByExample")
    long countByExample(SmsFlashPromotionSessionExample example);

    @DeleteProvider(type=SmsFlashPromotionSessionSqlProvider.class, method="deleteByExample")
    int deleteByExample(SmsFlashPromotionSessionExample example);

    @Delete({
        "delete from sms_flash_promotion_session",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into sms_flash_promotion_session (name, start_time, ",
        "end_time, status, create_time)",
        "values (#{name,jdbcType=VARCHAR}, #{startTime,jdbcType=TIME}, ",
        "#{endTime,jdbcType=TIME}, #{status,jdbcType=INTEGER}, #{createTime,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(SmsFlashPromotionSession record);

    @InsertProvider(type=SmsFlashPromotionSessionSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insertSelective(SmsFlashPromotionSession record);

    @SelectProvider(type=SmsFlashPromotionSessionSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="start_time", property="startTime", jdbcType=JdbcType.TIME),
        @Result(column="end_time", property="endTime", jdbcType=JdbcType.TIME),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<SmsFlashPromotionSession> selectByExample(SmsFlashPromotionSessionExample example);

    @Select({
        "select",
        "id, name, start_time, end_time, status, create_time",
        "from sms_flash_promotion_session",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="start_time", property="startTime", jdbcType=JdbcType.TIME),
        @Result(column="end_time", property="endTime", jdbcType=JdbcType.TIME),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP)
    })
    SmsFlashPromotionSession selectByPrimaryKey(Long id);

    @UpdateProvider(type=SmsFlashPromotionSessionSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") SmsFlashPromotionSession record, @Param("example") SmsFlashPromotionSessionExample example);

    @UpdateProvider(type=SmsFlashPromotionSessionSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") SmsFlashPromotionSession record, @Param("example") SmsFlashPromotionSessionExample example);

    @UpdateProvider(type=SmsFlashPromotionSessionSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(SmsFlashPromotionSession record);

    @Update({
        "update sms_flash_promotion_session",
        "set name = #{name,jdbcType=VARCHAR},",
          "start_time = #{startTime,jdbcType=TIME},",
          "end_time = #{endTime,jdbcType=TIME},",
          "status = #{status,jdbcType=INTEGER},",
          "create_time = #{createTime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(SmsFlashPromotionSession record);
}