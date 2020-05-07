package net.arver.mall.mapper;

import java.util.List;
import net.arver.mall.model.UmsMenu;
import net.arver.mall.model.UmsMenuExample;
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

public interface UmsMenuMapper {
    @SelectProvider(type=UmsMenuSqlProvider.class, method="countByExample")
    long countByExample(UmsMenuExample example);

    @DeleteProvider(type=UmsMenuSqlProvider.class, method="deleteByExample")
    int deleteByExample(UmsMenuExample example);

    @Delete({
        "delete from ums_menu",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into ums_menu (parent_id, create_time, ",
        "title, level, sort, ",
        "name, icon, hidden)",
        "values (#{parentId,jdbcType=BIGINT}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{title,jdbcType=VARCHAR}, #{level,jdbcType=INTEGER}, #{sort,jdbcType=INTEGER}, ",
        "#{name,jdbcType=VARCHAR}, #{icon,jdbcType=VARCHAR}, #{hidden,jdbcType=INTEGER})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(UmsMenu record);

    @InsertProvider(type=UmsMenuSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insertSelective(UmsMenu record);

    @SelectProvider(type=UmsMenuSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="parent_id", property="parentId", jdbcType=JdbcType.BIGINT),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="level", property="level", jdbcType=JdbcType.INTEGER),
        @Result(column="sort", property="sort", jdbcType=JdbcType.INTEGER),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="icon", property="icon", jdbcType=JdbcType.VARCHAR),
        @Result(column="hidden", property="hidden", jdbcType=JdbcType.INTEGER)
    })
    List<UmsMenu> selectByExample(UmsMenuExample example);

    @Select({
        "select",
        "id, parent_id, create_time, title, level, sort, name, icon, hidden",
        "from ums_menu",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="parent_id", property="parentId", jdbcType=JdbcType.BIGINT),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="level", property="level", jdbcType=JdbcType.INTEGER),
        @Result(column="sort", property="sort", jdbcType=JdbcType.INTEGER),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="icon", property="icon", jdbcType=JdbcType.VARCHAR),
        @Result(column="hidden", property="hidden", jdbcType=JdbcType.INTEGER)
    })
    UmsMenu selectByPrimaryKey(Long id);

    @UpdateProvider(type=UmsMenuSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") UmsMenu record, @Param("example") UmsMenuExample example);

    @UpdateProvider(type=UmsMenuSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") UmsMenu record, @Param("example") UmsMenuExample example);

    @UpdateProvider(type=UmsMenuSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(UmsMenu record);

    @Update({
        "update ums_menu",
        "set parent_id = #{parentId,jdbcType=BIGINT},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "title = #{title,jdbcType=VARCHAR},",
          "level = #{level,jdbcType=INTEGER},",
          "sort = #{sort,jdbcType=INTEGER},",
          "name = #{name,jdbcType=VARCHAR},",
          "icon = #{icon,jdbcType=VARCHAR},",
          "hidden = #{hidden,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(UmsMenu record);
}