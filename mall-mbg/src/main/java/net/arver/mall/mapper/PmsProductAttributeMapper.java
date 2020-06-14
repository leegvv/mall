package net.arver.mall.mapper;

import java.util.List;
import net.arver.mall.model.PmsProductAttribute;
import net.arver.mall.model.PmsProductAttributeExample;
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

public interface PmsProductAttributeMapper {
    @SelectProvider(type=PmsProductAttributeSqlProvider.class, method="countByExample")
    long countByExample(PmsProductAttributeExample example);

    @DeleteProvider(type=PmsProductAttributeSqlProvider.class, method="deleteByExample")
    int deleteByExample(PmsProductAttributeExample example);

    @Delete({
        "delete from pms_product_attribute",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into pms_product_attribute (product_attribute_category_id, name, ",
        "select_type, input_type, ",
        "input_list, sort, ",
        "filter_type, search_type, ",
        "related_status, hand_add_status, ",
        "type)",
        "values (#{productAttributeCategoryId,jdbcType=BIGINT}, #{name,jdbcType=VARCHAR}, ",
        "#{selectType,jdbcType=INTEGER}, #{inputType,jdbcType=INTEGER}, ",
        "#{inputList,jdbcType=VARCHAR}, #{sort,jdbcType=INTEGER}, ",
        "#{filterType,jdbcType=INTEGER}, #{searchType,jdbcType=INTEGER}, ",
        "#{relatedStatus,jdbcType=INTEGER}, #{handAddStatus,jdbcType=INTEGER}, ",
        "#{type,jdbcType=INTEGER})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(PmsProductAttribute record);

    @InsertProvider(type=PmsProductAttributeSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insertSelective(PmsProductAttribute record);

    @SelectProvider(type=PmsProductAttributeSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="product_attribute_category_id", property="productAttributeCategoryId", jdbcType=JdbcType.BIGINT),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="select_type", property="selectType", jdbcType=JdbcType.INTEGER),
        @Result(column="input_type", property="inputType", jdbcType=JdbcType.INTEGER),
        @Result(column="input_list", property="inputList", jdbcType=JdbcType.VARCHAR),
        @Result(column="sort", property="sort", jdbcType=JdbcType.INTEGER),
        @Result(column="filter_type", property="filterType", jdbcType=JdbcType.INTEGER),
        @Result(column="search_type", property="searchType", jdbcType=JdbcType.INTEGER),
        @Result(column="related_status", property="relatedStatus", jdbcType=JdbcType.INTEGER),
        @Result(column="hand_add_status", property="handAddStatus", jdbcType=JdbcType.INTEGER),
        @Result(column="type", property="type", jdbcType=JdbcType.INTEGER)
    })
    List<PmsProductAttribute> selectByExample(PmsProductAttributeExample example);

    @Select({
        "select",
        "id, product_attribute_category_id, name, select_type, input_type, input_list, ",
        "sort, filter_type, search_type, related_status, hand_add_status, type",
        "from pms_product_attribute",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="product_attribute_category_id", property="productAttributeCategoryId", jdbcType=JdbcType.BIGINT),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="select_type", property="selectType", jdbcType=JdbcType.INTEGER),
        @Result(column="input_type", property="inputType", jdbcType=JdbcType.INTEGER),
        @Result(column="input_list", property="inputList", jdbcType=JdbcType.VARCHAR),
        @Result(column="sort", property="sort", jdbcType=JdbcType.INTEGER),
        @Result(column="filter_type", property="filterType", jdbcType=JdbcType.INTEGER),
        @Result(column="search_type", property="searchType", jdbcType=JdbcType.INTEGER),
        @Result(column="related_status", property="relatedStatus", jdbcType=JdbcType.INTEGER),
        @Result(column="hand_add_status", property="handAddStatus", jdbcType=JdbcType.INTEGER),
        @Result(column="type", property="type", jdbcType=JdbcType.INTEGER)
    })
    PmsProductAttribute selectByPrimaryKey(Long id);

    @UpdateProvider(type=PmsProductAttributeSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") PmsProductAttribute record, @Param("example") PmsProductAttributeExample example);

    @UpdateProvider(type=PmsProductAttributeSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") PmsProductAttribute record, @Param("example") PmsProductAttributeExample example);

    @UpdateProvider(type=PmsProductAttributeSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(PmsProductAttribute record);

    @Update({
        "update pms_product_attribute",
        "set product_attribute_category_id = #{productAttributeCategoryId,jdbcType=BIGINT},",
          "name = #{name,jdbcType=VARCHAR},",
          "select_type = #{selectType,jdbcType=INTEGER},",
          "input_type = #{inputType,jdbcType=INTEGER},",
          "input_list = #{inputList,jdbcType=VARCHAR},",
          "sort = #{sort,jdbcType=INTEGER},",
          "filter_type = #{filterType,jdbcType=INTEGER},",
          "search_type = #{searchType,jdbcType=INTEGER},",
          "related_status = #{relatedStatus,jdbcType=INTEGER},",
          "hand_add_status = #{handAddStatus,jdbcType=INTEGER},",
          "type = #{type,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(PmsProductAttribute record);
}