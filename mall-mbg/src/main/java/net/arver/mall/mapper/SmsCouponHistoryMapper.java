package net.arver.mall.mapper;

import java.util.List;
import net.arver.mall.model.SmsCouponHistory;
import net.arver.mall.model.SmsCouponHistoryExample;
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

public interface SmsCouponHistoryMapper {
    @SelectProvider(type=SmsCouponHistorySqlProvider.class, method="countByExample")
    long countByExample(SmsCouponHistoryExample example);

    @DeleteProvider(type=SmsCouponHistorySqlProvider.class, method="deleteByExample")
    int deleteByExample(SmsCouponHistoryExample example);

    @Delete({
        "delete from sms_coupon_history",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into sms_coupon_history (coupon_id, member_id, ",
        "coupon_code, member_nickname, ",
        "get_type, create_time, ",
        "use_status, use_time, ",
        "order_id, order_sn)",
        "values (#{couponId,jdbcType=BIGINT}, #{memberId,jdbcType=BIGINT}, ",
        "#{couponCode,jdbcType=VARCHAR}, #{memberNickname,jdbcType=VARCHAR}, ",
        "#{getType,jdbcType=INTEGER}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{useStatus,jdbcType=INTEGER}, #{useTime,jdbcType=TIMESTAMP}, ",
        "#{orderId,jdbcType=BIGINT}, #{orderSn,jdbcType=VARCHAR})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(SmsCouponHistory record);

    @InsertProvider(type=SmsCouponHistorySqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insertSelective(SmsCouponHistory record);

    @SelectProvider(type=SmsCouponHistorySqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="coupon_id", property="couponId", jdbcType=JdbcType.BIGINT),
        @Result(column="member_id", property="memberId", jdbcType=JdbcType.BIGINT),
        @Result(column="coupon_code", property="couponCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="member_nickname", property="memberNickname", jdbcType=JdbcType.VARCHAR),
        @Result(column="get_type", property="getType", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="use_status", property="useStatus", jdbcType=JdbcType.INTEGER),
        @Result(column="use_time", property="useTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="order_id", property="orderId", jdbcType=JdbcType.BIGINT),
        @Result(column="order_sn", property="orderSn", jdbcType=JdbcType.VARCHAR)
    })
    List<SmsCouponHistory> selectByExample(SmsCouponHistoryExample example);

    @Select({
        "select",
        "id, coupon_id, member_id, coupon_code, member_nickname, get_type, create_time, ",
        "use_status, use_time, order_id, order_sn",
        "from sms_coupon_history",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="coupon_id", property="couponId", jdbcType=JdbcType.BIGINT),
        @Result(column="member_id", property="memberId", jdbcType=JdbcType.BIGINT),
        @Result(column="coupon_code", property="couponCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="member_nickname", property="memberNickname", jdbcType=JdbcType.VARCHAR),
        @Result(column="get_type", property="getType", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="use_status", property="useStatus", jdbcType=JdbcType.INTEGER),
        @Result(column="use_time", property="useTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="order_id", property="orderId", jdbcType=JdbcType.BIGINT),
        @Result(column="order_sn", property="orderSn", jdbcType=JdbcType.VARCHAR)
    })
    SmsCouponHistory selectByPrimaryKey(Long id);

    @UpdateProvider(type=SmsCouponHistorySqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") SmsCouponHistory record, @Param("example") SmsCouponHistoryExample example);

    @UpdateProvider(type=SmsCouponHistorySqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") SmsCouponHistory record, @Param("example") SmsCouponHistoryExample example);

    @UpdateProvider(type=SmsCouponHistorySqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(SmsCouponHistory record);

    @Update({
        "update sms_coupon_history",
        "set coupon_id = #{couponId,jdbcType=BIGINT},",
          "member_id = #{memberId,jdbcType=BIGINT},",
          "coupon_code = #{couponCode,jdbcType=VARCHAR},",
          "member_nickname = #{memberNickname,jdbcType=VARCHAR},",
          "get_type = #{getType,jdbcType=INTEGER},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "use_status = #{useStatus,jdbcType=INTEGER},",
          "use_time = #{useTime,jdbcType=TIMESTAMP},",
          "order_id = #{orderId,jdbcType=BIGINT},",
          "order_sn = #{orderSn,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(SmsCouponHistory record);
}