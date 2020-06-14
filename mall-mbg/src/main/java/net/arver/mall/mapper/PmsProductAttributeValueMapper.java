package net.arver.mall.mapper;

import java.util.List;
import net.arver.mall.model.PmsProductAttributeValue;
import net.arver.mall.model.PmsProductAttributeValueExample;
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

public interface PmsProductAttributeValueMapper {
    @SelectProvider(type=PmsProductAttributeValueSqlProvider.class, method="countByExample")
    long countByExample(PmsProductAttributeValueExample example);

    @DeleteProvider(type=PmsProductAttributeValueSqlProvider.class, method="deleteByExample")
    int deleteByExample(PmsProductAttributeValueExample example);

    @Delete({
        "delete from pms_product_attribute_value",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into pms_product_attribute_value (product_id, product_attribute_id, ",
        "value)",
        "values (#{productId,jdbcType=BIGINT}, #{productAttributeId,jdbcType=BIGINT}, ",
        "#{value,jdbcType=VARCHAR})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(PmsProductAttributeValue record);

    @InsertProvider(type=PmsProductAttributeValueSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insertSelective(PmsProductAttributeValue record);

    @SelectProvider(type=PmsProductAttributeValueSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="product_id", property="productId", jdbcType=JdbcType.BIGINT),
        @Result(column="product_attribute_id", property="productAttributeId", jdbcType=JdbcType.BIGINT),
        @Result(column="value", property="value", jdbcType=JdbcType.VARCHAR)
    })
    List<PmsProductAttributeValue> selectByExample(PmsProductAttributeValueExample example);

    @Select({
        "select",
        "id, product_id, product_attribute_id, value",
        "from pms_product_attribute_value",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="product_id", property="productId", jdbcType=JdbcType.BIGINT),
        @Result(column="product_attribute_id", property="productAttributeId", jdbcType=JdbcType.BIGINT),
        @Result(column="value", property="value", jdbcType=JdbcType.VARCHAR)
    })
    PmsProductAttributeValue selectByPrimaryKey(Long id);

    @UpdateProvider(type=PmsProductAttributeValueSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") PmsProductAttributeValue record, @Param("example") PmsProductAttributeValueExample example);

    @UpdateProvider(type=PmsProductAttributeValueSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") PmsProductAttributeValue record, @Param("example") PmsProductAttributeValueExample example);

    @UpdateProvider(type=PmsProductAttributeValueSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(PmsProductAttributeValue record);

    @Update({
        "update pms_product_attribute_value",
        "set product_id = #{productId,jdbcType=BIGINT},",
          "product_attribute_id = #{productAttributeId,jdbcType=BIGINT},",
          "value = #{value,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(PmsProductAttributeValue record);
}