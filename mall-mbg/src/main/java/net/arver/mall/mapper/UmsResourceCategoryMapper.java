package net.arver.mall.mapper;

import java.util.List;
import net.arver.mall.model.UmsResourceCategory;
import net.arver.mall.model.UmsResourceCategoryExample;
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

public interface UmsResourceCategoryMapper {
    @SelectProvider(type=UmsResourceCategorySqlProvider.class, method="countByExample")
    long countByExample(UmsResourceCategoryExample example);

    @DeleteProvider(type=UmsResourceCategorySqlProvider.class, method="deleteByExample")
    int deleteByExample(UmsResourceCategoryExample example);

    @Delete({
        "delete from ums_resource_category",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into ums_resource_category (create_time, name, ",
        "sort)",
        "values (#{createTime,jdbcType=TIMESTAMP}, #{name,jdbcType=VARCHAR}, ",
        "#{sort,jdbcType=INTEGER})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(UmsResourceCategory record);

    @InsertProvider(type=UmsResourceCategorySqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insertSelective(UmsResourceCategory record);

    @SelectProvider(type=UmsResourceCategorySqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="sort", property="sort", jdbcType=JdbcType.INTEGER)
    })
    List<UmsResourceCategory> selectByExample(UmsResourceCategoryExample example);

    @Select({
        "select",
        "id, create_time, name, sort",
        "from ums_resource_category",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="sort", property="sort", jdbcType=JdbcType.INTEGER)
    })
    UmsResourceCategory selectByPrimaryKey(Long id);

    @UpdateProvider(type=UmsResourceCategorySqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") UmsResourceCategory record, @Param("example") UmsResourceCategoryExample example);

    @UpdateProvider(type=UmsResourceCategorySqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") UmsResourceCategory record, @Param("example") UmsResourceCategoryExample example);

    @UpdateProvider(type=UmsResourceCategorySqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(UmsResourceCategory record);

    @Update({
        "update ums_resource_category",
        "set create_time = #{createTime,jdbcType=TIMESTAMP},",
          "name = #{name,jdbcType=VARCHAR},",
          "sort = #{sort,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(UmsResourceCategory record);
}