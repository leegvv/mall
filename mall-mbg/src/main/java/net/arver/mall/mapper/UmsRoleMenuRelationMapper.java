package net.arver.mall.mapper;

import java.util.List;
import net.arver.mall.model.UmsRoleMenuRelation;
import net.arver.mall.model.UmsRoleMenuRelationExample;
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

public interface UmsRoleMenuRelationMapper {
    @SelectProvider(type=UmsRoleMenuRelationSqlProvider.class, method="countByExample")
    long countByExample(UmsRoleMenuRelationExample example);

    @DeleteProvider(type=UmsRoleMenuRelationSqlProvider.class, method="deleteByExample")
    int deleteByExample(UmsRoleMenuRelationExample example);

    @Delete({
        "delete from ums_role_menu_relation",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into ums_role_menu_relation (role_id, menu_id)",
        "values (#{roleId,jdbcType=BIGINT}, #{menuId,jdbcType=BIGINT})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(UmsRoleMenuRelation record);

    @InsertProvider(type=UmsRoleMenuRelationSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insertSelective(UmsRoleMenuRelation record);

    @SelectProvider(type=UmsRoleMenuRelationSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="role_id", property="roleId", jdbcType=JdbcType.BIGINT),
        @Result(column="menu_id", property="menuId", jdbcType=JdbcType.BIGINT)
    })
    List<UmsRoleMenuRelation> selectByExample(UmsRoleMenuRelationExample example);

    @Select({
        "select",
        "id, role_id, menu_id",
        "from ums_role_menu_relation",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="role_id", property="roleId", jdbcType=JdbcType.BIGINT),
        @Result(column="menu_id", property="menuId", jdbcType=JdbcType.BIGINT)
    })
    UmsRoleMenuRelation selectByPrimaryKey(Long id);

    @UpdateProvider(type=UmsRoleMenuRelationSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") UmsRoleMenuRelation record, @Param("example") UmsRoleMenuRelationExample example);

    @UpdateProvider(type=UmsRoleMenuRelationSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") UmsRoleMenuRelation record, @Param("example") UmsRoleMenuRelationExample example);

    @UpdateProvider(type=UmsRoleMenuRelationSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(UmsRoleMenuRelation record);

    @Update({
        "update ums_role_menu_relation",
        "set role_id = #{roleId,jdbcType=BIGINT},",
          "menu_id = #{menuId,jdbcType=BIGINT}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(UmsRoleMenuRelation record);
}