package net.arver.mall.mapper;

import java.util.List;
import net.arver.mall.model.UmsAdmin;
import net.arver.mall.model.UmsAdminExample;
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

public interface UmsAdminMapper {
    @SelectProvider(type=UmsAdminSqlProvider.class, method="countByExample")
    long countByExample(UmsAdminExample example);

    @DeleteProvider(type=UmsAdminSqlProvider.class, method="deleteByExample")
    int deleteByExample(UmsAdminExample example);

    @Delete({
        "delete from ums_admin",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into ums_admin (username, password, ",
        "icon, email, nick_name, ",
        "note, create_time, ",
        "login_time, status)",
        "values (#{username,jdbcType=VARCHAR}, #{password,jdbcType=VARCHAR}, ",
        "#{icon,jdbcType=VARCHAR}, #{email,jdbcType=VARCHAR}, #{nickName,jdbcType=VARCHAR}, ",
        "#{note,jdbcType=VARCHAR}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{loginTime,jdbcType=TIMESTAMP}, #{status,jdbcType=INTEGER})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(UmsAdmin record);

    @InsertProvider(type=UmsAdminSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insertSelective(UmsAdmin record);

    @SelectProvider(type=UmsAdminSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="username", property="username", jdbcType=JdbcType.VARCHAR),
        @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
        @Result(column="icon", property="icon", jdbcType=JdbcType.VARCHAR),
        @Result(column="email", property="email", jdbcType=JdbcType.VARCHAR),
        @Result(column="nick_name", property="nickName", jdbcType=JdbcType.VARCHAR),
        @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="login_time", property="loginTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER)
    })
    List<UmsAdmin> selectByExample(UmsAdminExample example);

    @Select({
        "select",
        "id, username, password, icon, email, nick_name, note, create_time, login_time, ",
        "status",
        "from ums_admin",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="username", property="username", jdbcType=JdbcType.VARCHAR),
        @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
        @Result(column="icon", property="icon", jdbcType=JdbcType.VARCHAR),
        @Result(column="email", property="email", jdbcType=JdbcType.VARCHAR),
        @Result(column="nick_name", property="nickName", jdbcType=JdbcType.VARCHAR),
        @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="login_time", property="loginTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER)
    })
    UmsAdmin selectByPrimaryKey(Long id);

    @UpdateProvider(type=UmsAdminSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") UmsAdmin record, @Param("example") UmsAdminExample example);

    @UpdateProvider(type=UmsAdminSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") UmsAdmin record, @Param("example") UmsAdminExample example);

    @UpdateProvider(type=UmsAdminSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(UmsAdmin record);

    @Update({
        "update ums_admin",
        "set username = #{username,jdbcType=VARCHAR},",
          "password = #{password,jdbcType=VARCHAR},",
          "icon = #{icon,jdbcType=VARCHAR},",
          "email = #{email,jdbcType=VARCHAR},",
          "nick_name = #{nickName,jdbcType=VARCHAR},",
          "note = #{note,jdbcType=VARCHAR},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "login_time = #{loginTime,jdbcType=TIMESTAMP},",
          "status = #{status,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(UmsAdmin record);
}