package net.arver.mall.mapper;

import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import java.util.Map;
import net.arver.mall.model.SmsCouponHistory;
import net.arver.mall.model.SmsCouponHistoryExample.Criteria;
import net.arver.mall.model.SmsCouponHistoryExample.Criterion;
import net.arver.mall.model.SmsCouponHistoryExample;
import org.apache.ibatis.jdbc.SQL;

public class SmsCouponHistorySqlProvider {

    public String countByExample(SmsCouponHistoryExample example) {
        SQL sql = new SQL();
        sql.SELECT("count(*)").FROM("sms_coupon_history");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String deleteByExample(SmsCouponHistoryExample example) {
        SQL sql = new SQL();
        sql.DELETE_FROM("sms_coupon_history");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String insertSelective(SmsCouponHistory record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("sms_coupon_history");
        
        if (record.getCouponId() != null) {
            sql.VALUES("coupon_id", "#{couponId,jdbcType=BIGINT}");
        }
        
        if (record.getMemberId() != null) {
            sql.VALUES("member_id", "#{memberId,jdbcType=BIGINT}");
        }
        
        if (record.getCouponCode() != null) {
            sql.VALUES("coupon_code", "#{couponCode,jdbcType=VARCHAR}");
        }
        
        if (record.getMemberNickname() != null) {
            sql.VALUES("member_nickname", "#{memberNickname,jdbcType=VARCHAR}");
        }
        
        if (record.getGetType() != null) {
            sql.VALUES("get_type", "#{getType,jdbcType=INTEGER}");
        }
        
        if (record.getCreateTime() != null) {
            sql.VALUES("create_time", "#{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUseStatus() != null) {
            sql.VALUES("use_status", "#{useStatus,jdbcType=INTEGER}");
        }
        
        if (record.getUseTime() != null) {
            sql.VALUES("use_time", "#{useTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getOrderId() != null) {
            sql.VALUES("order_id", "#{orderId,jdbcType=BIGINT}");
        }
        
        if (record.getOrderSn() != null) {
            sql.VALUES("order_sn", "#{orderSn,jdbcType=VARCHAR}");
        }
        
        return sql.toString();
    }

    public String selectByExample(SmsCouponHistoryExample example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("id");
        } else {
            sql.SELECT("id");
        }
        sql.SELECT("coupon_id");
        sql.SELECT("member_id");
        sql.SELECT("coupon_code");
        sql.SELECT("member_nickname");
        sql.SELECT("get_type");
        sql.SELECT("create_time");
        sql.SELECT("use_status");
        sql.SELECT("use_time");
        sql.SELECT("order_id");
        sql.SELECT("order_sn");
        sql.FROM("sms_coupon_history");
        applyWhere(sql, example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            sql.ORDER_BY(example.getOrderByClause());
        }
        
        return sql.toString();
    }

    public String updateByExampleSelective(Map<String, Object> parameter) {
        SmsCouponHistory record = (SmsCouponHistory) parameter.get("record");
        SmsCouponHistoryExample example = (SmsCouponHistoryExample) parameter.get("example");
        
        SQL sql = new SQL();
        sql.UPDATE("sms_coupon_history");
        
        if (record.getId() != null) {
            sql.SET("id = #{record.id,jdbcType=BIGINT}");
        }
        
        if (record.getCouponId() != null) {
            sql.SET("coupon_id = #{record.couponId,jdbcType=BIGINT}");
        }
        
        if (record.getMemberId() != null) {
            sql.SET("member_id = #{record.memberId,jdbcType=BIGINT}");
        }
        
        if (record.getCouponCode() != null) {
            sql.SET("coupon_code = #{record.couponCode,jdbcType=VARCHAR}");
        }
        
        if (record.getMemberNickname() != null) {
            sql.SET("member_nickname = #{record.memberNickname,jdbcType=VARCHAR}");
        }
        
        if (record.getGetType() != null) {
            sql.SET("get_type = #{record.getType,jdbcType=INTEGER}");
        }
        
        if (record.getCreateTime() != null) {
            sql.SET("create_time = #{record.createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUseStatus() != null) {
            sql.SET("use_status = #{record.useStatus,jdbcType=INTEGER}");
        }
        
        if (record.getUseTime() != null) {
            sql.SET("use_time = #{record.useTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getOrderId() != null) {
            sql.SET("order_id = #{record.orderId,jdbcType=BIGINT}");
        }
        
        if (record.getOrderSn() != null) {
            sql.SET("order_sn = #{record.orderSn,jdbcType=VARCHAR}");
        }
        
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByExample(Map<String, Object> parameter) {
        SQL sql = new SQL();
        sql.UPDATE("sms_coupon_history");
        
        sql.SET("id = #{record.id,jdbcType=BIGINT}");
        sql.SET("coupon_id = #{record.couponId,jdbcType=BIGINT}");
        sql.SET("member_id = #{record.memberId,jdbcType=BIGINT}");
        sql.SET("coupon_code = #{record.couponCode,jdbcType=VARCHAR}");
        sql.SET("member_nickname = #{record.memberNickname,jdbcType=VARCHAR}");
        sql.SET("get_type = #{record.getType,jdbcType=INTEGER}");
        sql.SET("create_time = #{record.createTime,jdbcType=TIMESTAMP}");
        sql.SET("use_status = #{record.useStatus,jdbcType=INTEGER}");
        sql.SET("use_time = #{record.useTime,jdbcType=TIMESTAMP}");
        sql.SET("order_id = #{record.orderId,jdbcType=BIGINT}");
        sql.SET("order_sn = #{record.orderSn,jdbcType=VARCHAR}");
        
        SmsCouponHistoryExample example = (SmsCouponHistoryExample) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(SmsCouponHistory record) {
        SQL sql = new SQL();
        sql.UPDATE("sms_coupon_history");
        
        if (record.getCouponId() != null) {
            sql.SET("coupon_id = #{couponId,jdbcType=BIGINT}");
        }
        
        if (record.getMemberId() != null) {
            sql.SET("member_id = #{memberId,jdbcType=BIGINT}");
        }
        
        if (record.getCouponCode() != null) {
            sql.SET("coupon_code = #{couponCode,jdbcType=VARCHAR}");
        }
        
        if (record.getMemberNickname() != null) {
            sql.SET("member_nickname = #{memberNickname,jdbcType=VARCHAR}");
        }
        
        if (record.getGetType() != null) {
            sql.SET("get_type = #{getType,jdbcType=INTEGER}");
        }
        
        if (record.getCreateTime() != null) {
            sql.SET("create_time = #{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUseStatus() != null) {
            sql.SET("use_status = #{useStatus,jdbcType=INTEGER}");
        }
        
        if (record.getUseTime() != null) {
            sql.SET("use_time = #{useTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getOrderId() != null) {
            sql.SET("order_id = #{orderId,jdbcType=BIGINT}");
        }
        
        if (record.getOrderSn() != null) {
            sql.SET("order_sn = #{orderSn,jdbcType=VARCHAR}");
        }
        
        sql.WHERE("id = #{id,jdbcType=BIGINT}");
        
        return sql.toString();
    }

    protected void applyWhere(SQL sql, SmsCouponHistoryExample example, boolean includeExamplePhrase) {
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