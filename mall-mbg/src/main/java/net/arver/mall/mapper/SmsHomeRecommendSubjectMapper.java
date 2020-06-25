package net.arver.mall.mapper;

import java.util.List;
import net.arver.mall.model.SmsHomeRecommendSubject;
import net.arver.mall.model.SmsHomeRecommendSubjectExample;
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

public interface SmsHomeRecommendSubjectMapper {
    @SelectProvider(type=SmsHomeRecommendSubjectSqlProvider.class, method="countByExample")
    long countByExample(SmsHomeRecommendSubjectExample example);

    @DeleteProvider(type=SmsHomeRecommendSubjectSqlProvider.class, method="deleteByExample")
    int deleteByExample(SmsHomeRecommendSubjectExample example);

    @Delete({
        "delete from sms_home_recommend_subject",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into sms_home_recommend_subject (subject_id, subject_name, ",
        "recommend_status, sort)",
        "values (#{subjectId,jdbcType=BIGINT}, #{subjectName,jdbcType=VARCHAR}, ",
        "#{recommendStatus,jdbcType=INTEGER}, #{sort,jdbcType=INTEGER})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(SmsHomeRecommendSubject record);

    @InsertProvider(type=SmsHomeRecommendSubjectSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insertSelective(SmsHomeRecommendSubject record);

    @SelectProvider(type=SmsHomeRecommendSubjectSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="subject_id", property="subjectId", jdbcType=JdbcType.BIGINT),
        @Result(column="subject_name", property="subjectName", jdbcType=JdbcType.VARCHAR),
        @Result(column="recommend_status", property="recommendStatus", jdbcType=JdbcType.INTEGER),
        @Result(column="sort", property="sort", jdbcType=JdbcType.INTEGER)
    })
    List<SmsHomeRecommendSubject> selectByExample(SmsHomeRecommendSubjectExample example);

    @Select({
        "select",
        "id, subject_id, subject_name, recommend_status, sort",
        "from sms_home_recommend_subject",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="subject_id", property="subjectId", jdbcType=JdbcType.BIGINT),
        @Result(column="subject_name", property="subjectName", jdbcType=JdbcType.VARCHAR),
        @Result(column="recommend_status", property="recommendStatus", jdbcType=JdbcType.INTEGER),
        @Result(column="sort", property="sort", jdbcType=JdbcType.INTEGER)
    })
    SmsHomeRecommendSubject selectByPrimaryKey(Long id);

    @UpdateProvider(type=SmsHomeRecommendSubjectSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") SmsHomeRecommendSubject record, @Param("example") SmsHomeRecommendSubjectExample example);

    @UpdateProvider(type=SmsHomeRecommendSubjectSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") SmsHomeRecommendSubject record, @Param("example") SmsHomeRecommendSubjectExample example);

    @UpdateProvider(type=SmsHomeRecommendSubjectSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(SmsHomeRecommendSubject record);

    @Update({
        "update sms_home_recommend_subject",
        "set subject_id = #{subjectId,jdbcType=BIGINT},",
          "subject_name = #{subjectName,jdbcType=VARCHAR},",
          "recommend_status = #{recommendStatus,jdbcType=INTEGER},",
          "sort = #{sort,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(SmsHomeRecommendSubject record);
}