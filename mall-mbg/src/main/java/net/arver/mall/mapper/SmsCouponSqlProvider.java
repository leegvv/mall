package net.arver.mall.mapper;

import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import java.util.Map;
import net.arver.mall.model.SmsCoupon;
import net.arver.mall.model.SmsCouponExample.Criteria;
import net.arver.mall.model.SmsCouponExample.Criterion;
import net.arver.mall.model.SmsCouponExample;
import org.apache.ibatis.jdbc.SQL;

public class SmsCouponSqlProvider {

    public String countByExample(SmsCouponExample example) {
        SQL sql = new SQL();
        sql.SELECT("count(*)").FROM("sms_coupon");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String deleteByExample(SmsCouponExample example) {
        SQL sql = new SQL();
        sql.DELETE_FROM("sms_coupon");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String insertSelective(SmsCoupon record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("sms_coupon");
        
        if (record.getType() != null) {
            sql.VALUES("type", "#{type,jdbcType=INTEGER}");
        }
        
        if (record.getName() != null) {
            sql.VALUES("name", "#{name,jdbcType=VARCHAR}");
        }
        
        if (record.getPlatform() != null) {
            sql.VALUES("platform", "#{platform,jdbcType=INTEGER}");
        }
        
        if (record.getCount() != null) {
            sql.VALUES("count", "#{count,jdbcType=INTEGER}");
        }
        
        if (record.getAmount() != null) {
            sql.VALUES("amount", "#{amount,jdbcType=DECIMAL}");
        }
        
        if (record.getPerLimit() != null) {
            sql.VALUES("per_limit", "#{perLimit,jdbcType=INTEGER}");
        }
        
        if (record.getMinPoint() != null) {
            sql.VALUES("min_point", "#{minPoint,jdbcType=DECIMAL}");
        }
        
        if (record.getStartTime() != null) {
            sql.VALUES("start_time", "#{startTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getEndTime() != null) {
            sql.VALUES("end_time", "#{endTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUseType() != null) {
            sql.VALUES("use_type", "#{useType,jdbcType=INTEGER}");
        }
        
        if (record.getNote() != null) {
            sql.VALUES("note", "#{note,jdbcType=VARCHAR}");
        }
        
        if (record.getPublishCount() != null) {
            sql.VALUES("publish_count", "#{publishCount,jdbcType=INTEGER}");
        }
        
        if (record.getUseCount() != null) {
            sql.VALUES("use_count", "#{useCount,jdbcType=INTEGER}");
        }
        
        if (record.getReceiveCount() != null) {
            sql.VALUES("receive_count", "#{receiveCount,jdbcType=INTEGER}");
        }
        
        if (record.getEnableTime() != null) {
            sql.VALUES("enable_time", "#{enableTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getCode() != null) {
            sql.VALUES("code", "#{code,jdbcType=VARCHAR}");
        }
        
        if (record.getMemberLevel() != null) {
            sql.VALUES("member_level", "#{memberLevel,jdbcType=INTEGER}");
        }
        
        return sql.toString();
    }

    public String selectByExample(SmsCouponExample example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("id");
        } else {
            sql.SELECT("id");
        }
        sql.SELECT("type");
        sql.SELECT("name");
        sql.SELECT("platform");
        sql.SELECT("count");
        sql.SELECT("amount");
        sql.SELECT("per_limit");
        sql.SELECT("min_point");
        sql.SELECT("start_time");
        sql.SELECT("end_time");
        sql.SELECT("use_type");
        sql.SELECT("note");
        sql.SELECT("publish_count");
        sql.SELECT("use_count");
        sql.SELECT("receive_count");
        sql.SELECT("enable_time");
        sql.SELECT("code");
        sql.SELECT("member_level");
        sql.FROM("sms_coupon");
        applyWhere(sql, example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            sql.ORDER_BY(example.getOrderByClause());
        }
        
        return sql.toString();
    }

    public String updateByExampleSelective(Map<String, Object> parameter) {
        SmsCoupon record = (SmsCoupon) parameter.get("record");
        SmsCouponExample example = (SmsCouponExample) parameter.get("example");
        
        SQL sql = new SQL();
        sql.UPDATE("sms_coupon");
        
        if (record.getId() != null) {
            sql.SET("id = #{record.id,jdbcType=BIGINT}");
        }
        
        if (record.getType() != null) {
            sql.SET("type = #{record.type,jdbcType=INTEGER}");
        }
        
        if (record.getName() != null) {
            sql.SET("name = #{record.name,jdbcType=VARCHAR}");
        }
        
        if (record.getPlatform() != null) {
            sql.SET("platform = #{record.platform,jdbcType=INTEGER}");
        }
        
        if (record.getCount() != null) {
            sql.SET("count = #{record.count,jdbcType=INTEGER}");
        }
        
        if (record.getAmount() != null) {
            sql.SET("amount = #{record.amount,jdbcType=DECIMAL}");
        }
        
        if (record.getPerLimit() != null) {
            sql.SET("per_limit = #{record.perLimit,jdbcType=INTEGER}");
        }
        
        if (record.getMinPoint() != null) {
            sql.SET("min_point = #{record.minPoint,jdbcType=DECIMAL}");
        }
        
        if (record.getStartTime() != null) {
            sql.SET("start_time = #{record.startTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getEndTime() != null) {
            sql.SET("end_time = #{record.endTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUseType() != null) {
            sql.SET("use_type = #{record.useType,jdbcType=INTEGER}");
        }
        
        if (record.getNote() != null) {
            sql.SET("note = #{record.note,jdbcType=VARCHAR}");
        }
        
        if (record.getPublishCount() != null) {
            sql.SET("publish_count = #{record.publishCount,jdbcType=INTEGER}");
        }
        
        if (record.getUseCount() != null) {
            sql.SET("use_count = #{record.useCount,jdbcType=INTEGER}");
        }
        
        if (record.getReceiveCount() != null) {
            sql.SET("receive_count = #{record.receiveCount,jdbcType=INTEGER}");
        }
        
        if (record.getEnableTime() != null) {
            sql.SET("enable_time = #{record.enableTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getCode() != null) {
            sql.SET("code = #{record.code,jdbcType=VARCHAR}");
        }
        
        if (record.getMemberLevel() != null) {
            sql.SET("member_level = #{record.memberLevel,jdbcType=INTEGER}");
        }
        
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByExample(Map<String, Object> parameter) {
        SQL sql = new SQL();
        sql.UPDATE("sms_coupon");
        
        sql.SET("id = #{record.id,jdbcType=BIGINT}");
        sql.SET("type = #{record.type,jdbcType=INTEGER}");
        sql.SET("name = #{record.name,jdbcType=VARCHAR}");
        sql.SET("platform = #{record.platform,jdbcType=INTEGER}");
        sql.SET("count = #{record.count,jdbcType=INTEGER}");
        sql.SET("amount = #{record.amount,jdbcType=DECIMAL}");
        sql.SET("per_limit = #{record.perLimit,jdbcType=INTEGER}");
        sql.SET("min_point = #{record.minPoint,jdbcType=DECIMAL}");
        sql.SET("start_time = #{record.startTime,jdbcType=TIMESTAMP}");
        sql.SET("end_time = #{record.endTime,jdbcType=TIMESTAMP}");
        sql.SET("use_type = #{record.useType,jdbcType=INTEGER}");
        sql.SET("note = #{record.note,jdbcType=VARCHAR}");
        sql.SET("publish_count = #{record.publishCount,jdbcType=INTEGER}");
        sql.SET("use_count = #{record.useCount,jdbcType=INTEGER}");
        sql.SET("receive_count = #{record.receiveCount,jdbcType=INTEGER}");
        sql.SET("enable_time = #{record.enableTime,jdbcType=TIMESTAMP}");
        sql.SET("code = #{record.code,jdbcType=VARCHAR}");
        sql.SET("member_level = #{record.memberLevel,jdbcType=INTEGER}");
        
        SmsCouponExample example = (SmsCouponExample) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(SmsCoupon record) {
        SQL sql = new SQL();
        sql.UPDATE("sms_coupon");
        
        if (record.getType() != null) {
            sql.SET("type = #{type,jdbcType=INTEGER}");
        }
        
        if (record.getName() != null) {
            sql.SET("name = #{name,jdbcType=VARCHAR}");
        }
        
        if (record.getPlatform() != null) {
            sql.SET("platform = #{platform,jdbcType=INTEGER}");
        }
        
        if (record.getCount() != null) {
            sql.SET("count = #{count,jdbcType=INTEGER}");
        }
        
        if (record.getAmount() != null) {
            sql.SET("amount = #{amount,jdbcType=DECIMAL}");
        }
        
        if (record.getPerLimit() != null) {
            sql.SET("per_limit = #{perLimit,jdbcType=INTEGER}");
        }
        
        if (record.getMinPoint() != null) {
            sql.SET("min_point = #{minPoint,jdbcType=DECIMAL}");
        }
        
        if (record.getStartTime() != null) {
            sql.SET("start_time = #{startTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getEndTime() != null) {
            sql.SET("end_time = #{endTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUseType() != null) {
            sql.SET("use_type = #{useType,jdbcType=INTEGER}");
        }
        
        if (record.getNote() != null) {
            sql.SET("note = #{note,jdbcType=VARCHAR}");
        }
        
        if (record.getPublishCount() != null) {
            sql.SET("publish_count = #{publishCount,jdbcType=INTEGER}");
        }
        
        if (record.getUseCount() != null) {
            sql.SET("use_count = #{useCount,jdbcType=INTEGER}");
        }
        
        if (record.getReceiveCount() != null) {
            sql.SET("receive_count = #{receiveCount,jdbcType=INTEGER}");
        }
        
        if (record.getEnableTime() != null) {
            sql.SET("enable_time = #{enableTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getCode() != null) {
            sql.SET("code = #{code,jdbcType=VARCHAR}");
        }
        
        if (record.getMemberLevel() != null) {
            sql.SET("member_level = #{memberLevel,jdbcType=INTEGER}");
        }
        
        sql.WHERE("id = #{id,jdbcType=BIGINT}");
        
        return sql.toString();
    }

    protected void applyWhere(SQL sql, SmsCouponExample example, boolean includeExamplePhrase) {
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