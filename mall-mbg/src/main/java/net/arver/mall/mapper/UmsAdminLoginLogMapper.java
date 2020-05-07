package net.arver.mall.mapper;

import java.util.List;
import net.arver.mall.model.UmsAdminLoginLog;
import net.arver.mall.model.UmsAdminLoginLogExample;
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

public interface UmsAdminLoginLogMapper {
    @SelectProvider(type=UmsAdminLoginLogSqlProvider.class, method="countByExample")
    long countByExample(UmsAdminLoginLogExample example);

    @DeleteProvider(type=UmsAdminLoginLogSqlProvider.class, method="deleteByExample")
    int deleteByExample(UmsAdminLoginLogExample example);

    @Delete({
        "delete from ums_admin_login_log",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into ums_admin_login_log (admin_id, create_time, ",
        "ip, address, user_agent)",
        "values (#{adminId,jdbcType=BIGINT}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{ip,jdbcType=VARCHAR}, #{address,jdbcType=VARCHAR}, #{userAgent,jdbcType=VARCHAR})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(UmsAdminLoginLog record);

    @InsertProvider(type=UmsAdminLoginLogSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insertSelective(UmsAdminLoginLog record);

    @SelectProvider(type=UmsAdminLoginLogSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="admin_id", property="adminId", jdbcType=JdbcType.BIGINT),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="ip", property="ip", jdbcType=JdbcType.VARCHAR),
        @Result(column="address", property="address", jdbcType=JdbcType.VARCHAR),
        @Result(column="user_agent", property="userAgent", jdbcType=JdbcType.VARCHAR)
    })
    List<UmsAdminLoginLog> selectByExample(UmsAdminLoginLogExample example);

    @Select({
        "select",
        "id, admin_id, create_time, ip, address, user_agent",
        "from ums_admin_login_log",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="admin_id", property="adminId", jdbcType=JdbcType.BIGINT),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="ip", property="ip", jdbcType=JdbcType.VARCHAR),
        @Result(column="address", property="address", jdbcType=JdbcType.VARCHAR),
        @Result(column="user_agent", property="userAgent", jdbcType=JdbcType.VARCHAR)
    })
    UmsAdminLoginLog selectByPrimaryKey(Long id);

    @UpdateProvider(type=UmsAdminLoginLogSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") UmsAdminLoginLog record, @Param("example") UmsAdminLoginLogExample example);

    @UpdateProvider(type=UmsAdminLoginLogSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") UmsAdminLoginLog record, @Param("example") UmsAdminLoginLogExample example);

    @UpdateProvider(type=UmsAdminLoginLogSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(UmsAdminLoginLog record);

    @Update({
        "update ums_admin_login_log",
        "set admin_id = #{adminId,jdbcType=BIGINT},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "ip = #{ip,jdbcType=VARCHAR},",
          "address = #{address,jdbcType=VARCHAR},",
          "user_agent = #{userAgent,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(UmsAdminLoginLog record);
}