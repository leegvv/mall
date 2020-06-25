package net.arver.mall.mapper;

import java.util.List;
import net.arver.mall.model.SmsHomeAdvertise;
import net.arver.mall.model.SmsHomeAdvertiseExample;
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

public interface SmsHomeAdvertiseMapper {
    @SelectProvider(type=SmsHomeAdvertiseSqlProvider.class, method="countByExample")
    long countByExample(SmsHomeAdvertiseExample example);

    @DeleteProvider(type=SmsHomeAdvertiseSqlProvider.class, method="deleteByExample")
    int deleteByExample(SmsHomeAdvertiseExample example);

    @Delete({
        "delete from sms_home_advertise",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into sms_home_advertise (name, type, ",
        "pic, start_time, ",
        "end_time, status, ",
        "click_count, order_count, ",
        "url, note, sort)",
        "values (#{name,jdbcType=VARCHAR}, #{type,jdbcType=INTEGER}, ",
        "#{pic,jdbcType=VARCHAR}, #{startTime,jdbcType=TIMESTAMP}, ",
        "#{endTime,jdbcType=TIMESTAMP}, #{status,jdbcType=INTEGER}, ",
        "#{clickCount,jdbcType=INTEGER}, #{orderCount,jdbcType=INTEGER}, ",
        "#{url,jdbcType=VARCHAR}, #{note,jdbcType=VARCHAR}, #{sort,jdbcType=INTEGER})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(SmsHomeAdvertise record);

    @InsertProvider(type=SmsHomeAdvertiseSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insertSelective(SmsHomeAdvertise record);

    @SelectProvider(type=SmsHomeAdvertiseSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="type", property="type", jdbcType=JdbcType.INTEGER),
        @Result(column="pic", property="pic", jdbcType=JdbcType.VARCHAR),
        @Result(column="start_time", property="startTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="end_time", property="endTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="click_count", property="clickCount", jdbcType=JdbcType.INTEGER),
        @Result(column="order_count", property="orderCount", jdbcType=JdbcType.INTEGER),
        @Result(column="url", property="url", jdbcType=JdbcType.VARCHAR),
        @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR),
        @Result(column="sort", property="sort", jdbcType=JdbcType.INTEGER)
    })
    List<SmsHomeAdvertise> selectByExample(SmsHomeAdvertiseExample example);

    @Select({
        "select",
        "id, name, type, pic, start_time, end_time, status, click_count, order_count, ",
        "url, note, sort",
        "from sms_home_advertise",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="type", property="type", jdbcType=JdbcType.INTEGER),
        @Result(column="pic", property="pic", jdbcType=JdbcType.VARCHAR),
        @Result(column="start_time", property="startTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="end_time", property="endTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="click_count", property="clickCount", jdbcType=JdbcType.INTEGER),
        @Result(column="order_count", property="orderCount", jdbcType=JdbcType.INTEGER),
        @Result(column="url", property="url", jdbcType=JdbcType.VARCHAR),
        @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR),
        @Result(column="sort", property="sort", jdbcType=JdbcType.INTEGER)
    })
    SmsHomeAdvertise selectByPrimaryKey(Long id);

    @UpdateProvider(type=SmsHomeAdvertiseSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") SmsHomeAdvertise record, @Param("example") SmsHomeAdvertiseExample example);

    @UpdateProvider(type=SmsHomeAdvertiseSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") SmsHomeAdvertise record, @Param("example") SmsHomeAdvertiseExample example);

    @UpdateProvider(type=SmsHomeAdvertiseSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(SmsHomeAdvertise record);

    @Update({
        "update sms_home_advertise",
        "set name = #{name,jdbcType=VARCHAR},",
          "type = #{type,jdbcType=INTEGER},",
          "pic = #{pic,jdbcType=VARCHAR},",
          "start_time = #{startTime,jdbcType=TIMESTAMP},",
          "end_time = #{endTime,jdbcType=TIMESTAMP},",
          "status = #{status,jdbcType=INTEGER},",
          "click_count = #{clickCount,jdbcType=INTEGER},",
          "order_count = #{orderCount,jdbcType=INTEGER},",
          "url = #{url,jdbcType=VARCHAR},",
          "note = #{note,jdbcType=VARCHAR},",
          "sort = #{sort,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(SmsHomeAdvertise record);
}