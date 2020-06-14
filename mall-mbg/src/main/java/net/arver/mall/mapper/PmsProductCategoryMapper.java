package net.arver.mall.mapper;

import java.util.List;
import net.arver.mall.model.PmsProductCategory;
import net.arver.mall.model.PmsProductCategoryExample;
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

public interface PmsProductCategoryMapper {
    @SelectProvider(type=PmsProductCategorySqlProvider.class, method="countByExample")
    long countByExample(PmsProductCategoryExample example);

    @DeleteProvider(type=PmsProductCategorySqlProvider.class, method="deleteByExample")
    int deleteByExample(PmsProductCategoryExample example);

    @Delete({
        "delete from pms_product_category",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into pms_product_category (parent_id, name, ",
        "level, product_count, ",
        "product_unit, nav_status, ",
        "show_status, sort, ",
        "icon, keywords, ",
        "description)",
        "values (#{parentId,jdbcType=BIGINT}, #{name,jdbcType=VARCHAR}, ",
        "#{level,jdbcType=INTEGER}, #{productCount,jdbcType=INTEGER}, ",
        "#{productUnit,jdbcType=VARCHAR}, #{navStatus,jdbcType=INTEGER}, ",
        "#{showStatus,jdbcType=INTEGER}, #{sort,jdbcType=INTEGER}, ",
        "#{icon,jdbcType=VARCHAR}, #{keywords,jdbcType=VARCHAR}, ",
        "#{description,jdbcType=LONGVARCHAR})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(PmsProductCategory record);

    @InsertProvider(type=PmsProductCategorySqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insertSelective(PmsProductCategory record);

    @SelectProvider(type=PmsProductCategorySqlProvider.class, method="selectByExampleWithBLOBs")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="parent_id", property="parentId", jdbcType=JdbcType.BIGINT),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="level", property="level", jdbcType=JdbcType.INTEGER),
        @Result(column="product_count", property="productCount", jdbcType=JdbcType.INTEGER),
        @Result(column="product_unit", property="productUnit", jdbcType=JdbcType.VARCHAR),
        @Result(column="nav_status", property="navStatus", jdbcType=JdbcType.INTEGER),
        @Result(column="show_status", property="showStatus", jdbcType=JdbcType.INTEGER),
        @Result(column="sort", property="sort", jdbcType=JdbcType.INTEGER),
        @Result(column="icon", property="icon", jdbcType=JdbcType.VARCHAR),
        @Result(column="keywords", property="keywords", jdbcType=JdbcType.VARCHAR),
        @Result(column="description", property="description", jdbcType=JdbcType.LONGVARCHAR)
    })
    List<PmsProductCategory> selectByExampleWithBLOBs(PmsProductCategoryExample example);

    @SelectProvider(type=PmsProductCategorySqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="parent_id", property="parentId", jdbcType=JdbcType.BIGINT),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="level", property="level", jdbcType=JdbcType.INTEGER),
        @Result(column="product_count", property="productCount", jdbcType=JdbcType.INTEGER),
        @Result(column="product_unit", property="productUnit", jdbcType=JdbcType.VARCHAR),
        @Result(column="nav_status", property="navStatus", jdbcType=JdbcType.INTEGER),
        @Result(column="show_status", property="showStatus", jdbcType=JdbcType.INTEGER),
        @Result(column="sort", property="sort", jdbcType=JdbcType.INTEGER),
        @Result(column="icon", property="icon", jdbcType=JdbcType.VARCHAR),
        @Result(column="keywords", property="keywords", jdbcType=JdbcType.VARCHAR)
    })
    List<PmsProductCategory> selectByExample(PmsProductCategoryExample example);

    @Select({
        "select",
        "id, parent_id, name, level, product_count, product_unit, nav_status, show_status, ",
        "sort, icon, keywords, description",
        "from pms_product_category",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="parent_id", property="parentId", jdbcType=JdbcType.BIGINT),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="level", property="level", jdbcType=JdbcType.INTEGER),
        @Result(column="product_count", property="productCount", jdbcType=JdbcType.INTEGER),
        @Result(column="product_unit", property="productUnit", jdbcType=JdbcType.VARCHAR),
        @Result(column="nav_status", property="navStatus", jdbcType=JdbcType.INTEGER),
        @Result(column="show_status", property="showStatus", jdbcType=JdbcType.INTEGER),
        @Result(column="sort", property="sort", jdbcType=JdbcType.INTEGER),
        @Result(column="icon", property="icon", jdbcType=JdbcType.VARCHAR),
        @Result(column="keywords", property="keywords", jdbcType=JdbcType.VARCHAR),
        @Result(column="description", property="description", jdbcType=JdbcType.LONGVARCHAR)
    })
    PmsProductCategory selectByPrimaryKey(Long id);

    @UpdateProvider(type=PmsProductCategorySqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") PmsProductCategory record, @Param("example") PmsProductCategoryExample example);

    @UpdateProvider(type=PmsProductCategorySqlProvider.class, method="updateByExampleWithBLOBs")
    int updateByExampleWithBLOBs(@Param("record") PmsProductCategory record, @Param("example") PmsProductCategoryExample example);

    @UpdateProvider(type=PmsProductCategorySqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") PmsProductCategory record, @Param("example") PmsProductCategoryExample example);

    @UpdateProvider(type=PmsProductCategorySqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(PmsProductCategory record);

    @Update({
        "update pms_product_category",
        "set parent_id = #{parentId,jdbcType=BIGINT},",
          "name = #{name,jdbcType=VARCHAR},",
          "level = #{level,jdbcType=INTEGER},",
          "product_count = #{productCount,jdbcType=INTEGER},",
          "product_unit = #{productUnit,jdbcType=VARCHAR},",
          "nav_status = #{navStatus,jdbcType=INTEGER},",
          "show_status = #{showStatus,jdbcType=INTEGER},",
          "sort = #{sort,jdbcType=INTEGER},",
          "icon = #{icon,jdbcType=VARCHAR},",
          "keywords = #{keywords,jdbcType=VARCHAR},",
          "description = #{description,jdbcType=LONGVARCHAR}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKeyWithBLOBs(PmsProductCategory record);

    @Update({
        "update pms_product_category",
        "set parent_id = #{parentId,jdbcType=BIGINT},",
          "name = #{name,jdbcType=VARCHAR},",
          "level = #{level,jdbcType=INTEGER},",
          "product_count = #{productCount,jdbcType=INTEGER},",
          "product_unit = #{productUnit,jdbcType=VARCHAR},",
          "nav_status = #{navStatus,jdbcType=INTEGER},",
          "show_status = #{showStatus,jdbcType=INTEGER},",
          "sort = #{sort,jdbcType=INTEGER},",
          "icon = #{icon,jdbcType=VARCHAR},",
          "keywords = #{keywords,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(PmsProductCategory record);
}