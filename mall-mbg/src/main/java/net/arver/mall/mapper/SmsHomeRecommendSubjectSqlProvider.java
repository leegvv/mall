package net.arver.mall.mapper;

import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import java.util.Map;
import net.arver.mall.model.SmsHomeRecommendSubject;
import net.arver.mall.model.SmsHomeRecommendSubjectExample.Criteria;
import net.arver.mall.model.SmsHomeRecommendSubjectExample.Criterion;
import net.arver.mall.model.SmsHomeRecommendSubjectExample;
import org.apache.ibatis.jdbc.SQL;

public class SmsHomeRecommendSubjectSqlProvider {

    public String countByExample(SmsHomeRecommendSubjectExample example) {
        SQL sql = new SQL();
        sql.SELECT("count(*)").FROM("sms_home_recommend_subject");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String deleteByExample(SmsHomeRecommendSubjectExample example) {
        SQL sql = new SQL();
        sql.DELETE_FROM("sms_home_recommend_subject");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String insertSelective(SmsHomeRecommendSubject record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("sms_home_recommend_subject");
        
        if (record.getSubjectId() != null) {
            sql.VALUES("subject_id", "#{subjectId,jdbcType=BIGINT}");
        }
        
        if (record.getSubjectName() != null) {
            sql.VALUES("subject_name", "#{subjectName,jdbcType=VARCHAR}");
        }
        
        if (record.getRecommendStatus() != null) {
            sql.VALUES("recommend_status", "#{recommendStatus,jdbcType=INTEGER}");
        }
        
        if (record.getSort() != null) {
            sql.VALUES("sort", "#{sort,jdbcType=INTEGER}");
        }
        
        return sql.toString();
    }

    public String selectByExample(SmsHomeRecommendSubjectExample example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("id");
        } else {
            sql.SELECT("id");
        }
        sql.SELECT("subject_id");
        sql.SELECT("subject_name");
        sql.SELECT("recommend_status");
        sql.SELECT("sort");
        sql.FROM("sms_home_recommend_subject");
        applyWhere(sql, example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            sql.ORDER_BY(example.getOrderByClause());
        }
        
        return sql.toString();
    }

    public String updateByExampleSelective(Map<String, Object> parameter) {
        SmsHomeRecommendSubject record = (SmsHomeRecommendSubject) parameter.get("record");
        SmsHomeRecommendSubjectExample example = (SmsHomeRecommendSubjectExample) parameter.get("example");
        
        SQL sql = new SQL();
        sql.UPDATE("sms_home_recommend_subject");
        
        if (record.getId() != null) {
            sql.SET("id = #{record.id,jdbcType=BIGINT}");
        }
        
        if (record.getSubjectId() != null) {
            sql.SET("subject_id = #{record.subjectId,jdbcType=BIGINT}");
        }
        
        if (record.getSubjectName() != null) {
            sql.SET("subject_name = #{record.subjectName,jdbcType=VARCHAR}");
        }
        
        if (record.getRecommendStatus() != null) {
            sql.SET("recommend_status = #{record.recommendStatus,jdbcType=INTEGER}");
        }
        
        if (record.getSort() != null) {
            sql.SET("sort = #{record.sort,jdbcType=INTEGER}");
        }
        
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByExample(Map<String, Object> parameter) {
        SQL sql = new SQL();
        sql.UPDATE("sms_home_recommend_subject");
        
        sql.SET("id = #{record.id,jdbcType=BIGINT}");
        sql.SET("subject_id = #{record.subjectId,jdbcType=BIGINT}");
        sql.SET("subject_name = #{record.subjectName,jdbcType=VARCHAR}");
        sql.SET("recommend_status = #{record.recommendStatus,jdbcType=INTEGER}");
        sql.SET("sort = #{record.sort,jdbcType=INTEGER}");
        
        SmsHomeRecommendSubjectExample example = (SmsHomeRecommendSubjectExample) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(SmsHomeRecommendSubject record) {
        SQL sql = new SQL();
        sql.UPDATE("sms_home_recommend_subject");
        
        if (record.getSubjectId() != null) {
            sql.SET("subject_id = #{subjectId,jdbcType=BIGINT}");
        }
        
        if (record.getSubjectName() != null) {
            sql.SET("subject_name = #{subjectName,jdbcType=VARCHAR}");
        }
        
        if (record.getRecommendStatus() != null) {
            sql.SET("recommend_status = #{recommendStatus,jdbcType=INTEGER}");
        }
        
        if (record.getSort() != null) {
            sql.SET("sort = #{sort,jdbcType=INTEGER}");
        }
        
        sql.WHERE("id = #{id,jdbcType=BIGINT}");
        
        return sql.toString();
    }

    protected void applyWhere(SQL sql, SmsHomeRecommendSubjectExample example, boolean includeExamplePhrase) {
        if (example == null) {
            return;
        }
        
        String parmPhrase1;
        String parmPhrase1_th;
        String parmPhrase2;
        String parmPhrase2_th;
        String parmPhrase3;
        String parmPhrase3_th;
        if (includeExamplePhrase) {
            parmPhrase1 = "%s #{example.oredCriteria[%d].allCriteria[%d].value}";
            parmPhrase1_th = "%s #{example.oredCriteria[%d].allCriteria[%d].value,typeHandler=%s}";
            parmPhrase2 = "%s #{example.oredCriteria[%d].allCriteria[%d].value} and #{example.oredCriteria[%d].criteria[%d].secondValue}";
            parmPhrase2_th = "%s #{example.oredCriteria[%d].allCriteria[%d].value,typeHandler=%s} and #{example.oredCriteria[%d].criteria[%d].secondValue,typeHandler=%s}";
            parmPhrase3 = "#{example.oredCriteria[%d].allCriteria[%d].value[%d]}";
            parmPhrase3_th = "#{example.oredCriteria[%d].allCriteria[%d].value[%d],typeHandler=%s}";
        } else {
            parmPhrase1 = "%s #{oredCriteria[%d].allCriteria[%d].value}";
            parmPhrase1_th = "%s #{oredCriteria[%d].allCriteria[%d].value,typeHandler=%s}";
            parmPhrase2 = "%s #{oredCriteria[%d].allCriteria[%d].value} and #{oredCriteria[%d].criteria[%d].secondValue}";
            parmPhrase2_th = "%s #{oredCriteria[%d].allCriteria[%d].value,typeHandler=%s} and #{oredCriteria[%d].criteria[%d].secondValue,typeHandler=%s}";
            parmPhrase3 = "#{oredCriteria[%d].allCriteria[%d].value[%d]}";
            parmPhrase3_th = "#{oredCriteria[%d].allCriteria[%d].value[%d],typeHandler=%s}";
        }
        
        StringBuilder sb = new StringBuilder();
        List<Criteria> oredCriteria = example.getOredCriteria();
        boolean firstCriteria = true;
        for (int i = 0; i < oredCriteria.size(); i++) {
            Criteria criteria = oredCriteria.get(i);
            if (criteria.isValid()) {
                if (firstCriteria) {
                    firstCriteria = false;
                } else {
                    sb.append(" or ");
                }
                
                sb.append('(');
                List<Criterion> criterions = criteria.getAllCriteria();
                boolean firstCriterion = true;
                for (int j = 0; j < criterions.size(); j++) {
                    Criterion criterion = criterions.get(j);
                    if (firstCriterion) {
                        firstCriterion = false;
                    } else {
                        sb.append(" and ");
                    }
                    
                    if (criterion.isNoValue()) {
                        sb.append(criterion.getCondition());
                    } else if (criterion.isSingleValue()) {
                        if (criterion.getTypeHandler() == null) {
                            sb.append(String.format(parmPhrase1, criterion.getCondition(), i, j));
                        } else {
                            sb.append(String.format(parmPhrase1_th, criterion.getCondition(), i, j,criterion.getTypeHandler()));
                        }
                    } else if (criterion.isBetweenValue()) {
                        if (criterion.getTypeHandler() == null) {
                            sb.append(String.format(parmPhrase2, criterion.getCondition(), i, j, i, j));
                        } else {
                            sb.append(String.format(parmPhrase2_th, criterion.getCondition(), i, j, criterion.getTypeHandler(), i, j, criterion.getTypeHandler()));
                        }
                    } else if (criterion.isListValue()) {
                        sb.append(criterion.getCondition());
                        sb.append(" (");
                        List<?> listItems = (List<?>) criterion.getValue();
                        boolean comma = false;
                        for (int k = 0; k < listItems.size(); k++) {
                            if (comma) {
                                sb.append(", ");
                            } else {
                                comma = true;
                            }
                            if (criterion.getTypeHandler() == null) {
                                sb.append(String.format(parmPhrase3, i, j, k));
                            } else {
                                sb.append(String.format(parmPhrase3_th, i, j, k, criterion.getTypeHandler()));
                            }
                        }
                        sb.append(')');
                    }
                }
                sb.append(')');
            }
        }
        
        if (sb.length() > 0) {
            sql.WHERE(sb.toString());
        }
    }
}