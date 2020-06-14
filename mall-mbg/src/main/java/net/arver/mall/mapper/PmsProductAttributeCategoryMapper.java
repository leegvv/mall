package net.arver.mall.mapper;

import java.util.List;
import net.arver.mall.model.PmsProductAttributeCategory;
import net.arver.mall.model.PmsProductAttributeCategoryExample;
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

public interface PmsProductAttributeCategoryMapper {
    @SelectProvider(type=PmsProductAttributeCategorySqlProvider.class, method="countByExample")
    long countByExample(PmsProductAttributeCategoryExample example);

    @DeleteProvider(type=PmsProductAttributeCategorySqlProvider.class, method="deleteByExample")
    int deleteByExample(PmsProductAttributeCategoryExample example);

    @Delete({
        "delete from pms_product_attribute_category",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into pms_product_attribute_category (name, attribute_count, ",
        "param_count)",
        "values (#{name,jdbcType=VARCHAR}, #{attributeCount,jdbcType=INTEGER}, ",
        "#{paramCount,jdbcType=INTEGER})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(PmsProductAttributeCategory record);

    @InsertProvider(type=PmsProductAttributeCategorySqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insertSelective(PmsProductAttributeCategory record);

    @SelectProvider(type=PmsProductAttributeCategorySqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="attribute_count", property="attributeCount", jdbcType=JdbcType.INTEGER),
        @Result(column="param_count", property="paramCount", jdbcType=JdbcType.INTEGER)
    })
    List<PmsProductAttributeCategory> selectByExample(PmsProductAttributeCategoryExample example);

    @Select({
        "select",
        "id, name, attribute_count, param_count",
        "from pms_product_attribute_category",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="attribute_count", property="attributeCount", jdbcType=JdbcType.INTEGER),
        @Result(column="param_count", property="paramCount", jdbcType=JdbcType.INTEGER)
    })
    PmsProductAttributeCategory selectByPrimaryKey(Long id);

    @UpdateProvider(type=PmsProductAttributeCategorySqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") PmsProductAttributeCategory record, @Param("example") PmsProductAttributeCategoryExample example);

    @UpdateProvider(type=PmsProductAttributeCategorySqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") PmsProductAttributeCategory record, @Param("example") PmsProductAttributeCategoryExample example);

    @UpdateProvider(type=PmsProductAttributeCategorySqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(PmsProductAttributeCategory record);

    @Update({
        "update pms_product_attribute_category",
        "set name = #{name,jdbcType=VARCHAR},",
          "attribute_count = #{attributeCount,jdbcType=INTEGER},",
          "param_count = #{paramCount,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(PmsProductAttributeCategory record);
}