package net.arver.mall.mapper;

import java.util.List;
import net.arver.mall.model.SmsCoupon;
import net.arver.mall.model.SmsCouponExample;
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

public interface SmsCouponMapper {
    @SelectProvider(type=SmsCouponSqlProvider.class, method="countByExample")
    long countByExample(SmsCouponExample example);

    @DeleteProvider(type=SmsCouponSqlProvider.class, method="deleteByExample")
    int deleteByExample(SmsCouponExample example);

    @Delete({
        "delete from sms_coupon",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into sms_coupon (type, name, ",
        "platform, count, ",
        "amount, per_limit, ",
        "min_point, start_time, ",
        "end_time, use_type, ",
        "note, publish_count, ",
        "use_count, receive_count, ",
        "enable_time, code, ",
        "member_level)",
        "values (#{type,jdbcType=INTEGER}, #{name,jdbcType=VARCHAR}, ",
        "#{platform,jdbcType=INTEGER}, #{count,jdbcType=INTEGER}, ",
        "#{amount,jdbcType=DECIMAL}, #{perLimit,jdbcType=INTEGER}, ",
        "#{minPoint,jdbcType=DECIMAL}, #{startTime,jdbcType=TIMESTAMP}, ",
        "#{endTime,jdbcType=TIMESTAMP}, #{useType,jdbcType=INTEGER}, ",
        "#{note,jdbcType=VARCHAR}, #{publishCount,jdbcType=INTEGER}, ",
        "#{useCount,jdbcType=INTEGER}, #{receiveCount,jdbcType=INTEGER}, ",
        "#{enableTime,jdbcType=TIMESTAMP}, #{code,jdbcType=VARCHAR}, ",
        "#{memberLevel,jdbcType=INTEGER})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(SmsCoupon record);

    @InsertProvider(type=SmsCouponSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insertSelective(SmsCoupon record);

    @SelectProvider(type=SmsCouponSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="type", property="type", jdbcType=JdbcType.INTEGER),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="platform", property="platform", jdbcType=JdbcType.INTEGER),
        @Result(column="count", property="count", jdbcType=JdbcType.INTEGER),
        @Result(column="amount", property="amount", jdbcType=JdbcType.DECIMAL),
        @Result(column="per_limit", property="perLimit", jdbcType=JdbcType.INTEGER),
        @Result(column="min_point", property="minPoint", jdbcType=JdbcType.DECIMAL),
        @Result(column="start_time", property="startTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="end_time", property="endTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="use_type", property="useType", jdbcType=JdbcType.INTEGER),
        @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR),
        @Result(column="publish_count", property="publishCount", jdbcType=JdbcType.INTEGER),
        @Result(column="use_count", property="useCount", jdbcType=JdbcType.INTEGER),
        @Result(column="receive_count", property="receiveCount", jdbcType=JdbcType.INTEGER),
        @Result(column="enable_time", property="enableTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="code", property="code", jdbcType=JdbcType.VARCHAR),
        @Result(column="member_level", property="memberLevel", jdbcType=JdbcType.INTEGER)
    })
    List<SmsCoupon> selectByExample(SmsCouponExample example);

    @Select({
        "select",
        "id, type, name, platform, count, amount, per_limit, min_point, start_time, end_time, ",
        "use_type, note, publish_count, use_count, receive_count, enable_time, code, ",
        "member_level",
        "from sms_coupon",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="type", property="type", jdbcType=JdbcType.INTEGER),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="platform", property="platform", jdbcType=JdbcType.INTEGER),
        @Result(column="count", property="count", jdbcType=JdbcType.INTEGER),
        @Result(column="amount", property="amount", jdbcType=JdbcType.DECIMAL),
        @Result(column="per_limit", property="perLimit", jdbcType=JdbcType.INTEGER),
        @Result(column="min_point", property="minPoint", jdbcType=JdbcType.DECIMAL),
        @Result(column="start_time", property="startTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="end_time", property="endTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="use_type", property="useType", jdbcType=JdbcType.INTEGER),
        @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR),
        @Result(column="publish_count", property="publishCount", jdbcType=JdbcType.INTEGER),
        @Result(column="use_count", property="useCount", jdbcType=JdbcType.INTEGER),
        @Result(column="receive_count", property="receiveCount", jdbcType=JdbcType.INTEGER),
        @Result(column="enable_time", property="enableTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="code", property="code", jdbcType=JdbcType.VARCHAR),
        @Result(column="member_level", property="memberLevel", jdbcType=JdbcType.INTEGER)
    })
    SmsCoupon selectByPrimaryKey(Long id);

    @UpdateProvider(type=SmsCouponSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") SmsCoupon record, @Param("example") SmsCouponExample example);

    @UpdateProvider(type=SmsCouponSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") SmsCoupon record, @Param("example") SmsCouponExample example);

    @UpdateProvider(type=SmsCouponSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(SmsCoupon record);

    @Update({
        "update sms_coupon",
        "set type = #{type,jdbcType=INTEGER},",
          "name = #{name,jdbcType=VARCHAR},",
          "platform = #{platform,jdbcType=INTEGER},",
          "count = #{count,jdbcType=INTEGER},",
          "amount = #{amount,jdbcType=DECIMAL},",
          "per_limit = #{perLimit,jdbcType=INTEGER},",
          "min_point = #{minPoint,jdbcType=DECIMAL},",
          "start_time = #{startTime,jdbcType=TIMESTAMP},",
          "end_time = #{endTime,jdbcType=TIMESTAMP},",
          "use_type = #{useType,jdbcType=INTEGER},",
          "note = #{note,jdbcType=VARCHAR},",
          "publish_count = #{publishCount,jdbcType=INTEGER},",
          "use_count = #{useCount,jdbcType=INTEGER},",
          "receive_count = #{receiveCount,jdbcType=INTEGER},",
          "enable_time = #{enableTime,jdbcType=TIMESTAMP},",
          "code = #{code,jdbcType=VARCHAR},",
          "member_level = #{memberLevel,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(SmsCoupon record);
}