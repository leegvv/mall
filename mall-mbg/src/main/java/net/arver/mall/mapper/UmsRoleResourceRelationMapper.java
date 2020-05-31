package net.arver.mall.mapper;

import java.util.List;
import net.arver.mall.model.UmsRoleResourceRelation;
import net.arver.mall.model.UmsRoleResourceRelationExample;
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

public interface UmsRoleResourceRelationMapper {
    @SelectProvider(type=UmsRoleResourceRelationSqlProvider.class, method="countByExample")
    long countByExample(UmsRoleResourceRelationExample example);

    @DeleteProvider(type=UmsRoleResourceRelationSqlProvider.class, method="deleteByExample")
    int deleteByExample(UmsRoleResourceRelationExample example);

    @Delete({
        "delete from ums_role_resource_relation",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into ums_role_resource_relation (role_id, resource_id)",
        "values (#{roleId,jdbcType=BIGINT}, #{resourceId,jdbcType=BIGINT})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(UmsRoleResourceRelation record);

    @InsertProvider(type=UmsRoleResourceRelationSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insertSelective(UmsRoleResourceRelation record);

    @SelectProvider(type=UmsRoleResourceRelationSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="role_id", property="roleId", jdbcType=JdbcType.BIGINT),
        @Result(column="resource_id", property="resourceId", jdbcType=JdbcType.BIGINT)
    })
    List<UmsRoleResourceRelation> selectByExample(UmsRoleResourceRelationExample example);

    @Select({
        "select",
        "id, role_id, resource_id",
        "from ums_role_resource_relation",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="role_id", property="roleId", jdbcType=JdbcType.BIGINT),
        @Result(column="resource_id", property="resourceId", jdbcType=JdbcType.BIGINT)
    })
    UmsRoleResourceRelation selectByPrimaryKey(Long id);

    @UpdateProvider(type=UmsRoleResourceRelationSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") UmsRoleResourceRelation record, @Param("example") UmsRoleResourceRelationExample example);

    @UpdateProvider(type=UmsRoleResourceRelationSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") UmsRoleResourceRelation record, @Param("example") UmsRoleResourceRelationExample example);

    @UpdateProvider(type=UmsRoleResourceRelationSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(UmsRoleResourceRelation record);

    @Update({
        "update ums_role_resource_relation",
        "set role_id = #{roleId,jdbcType=BIGINT},",
          "resource_id = #{resourceId,jdbcType=BIGINT}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(UmsRoleResourceRelation record);
}