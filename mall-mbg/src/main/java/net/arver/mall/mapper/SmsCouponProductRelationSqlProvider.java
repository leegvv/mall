package net.arver.mall.mapper;

import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import java.util.Map;
import net.arver.mall.model.SmsCouponProductRelation;
import net.arver.mall.model.SmsCouponProductRelationExample.Criteria;
import net.arver.mall.model.SmsCouponProductRelationExample.Criterion;
import net.arver.mall.model.SmsCouponProductRelationExample;
import org.apache.ibatis.jdbc.SQL;

public class SmsCouponProductRelationSqlProvider {

    public String countByExample(SmsCouponProductRelationExample example) {
        SQL sql = new SQL();
        sql.SELECT("count(*)").FROM("sms_coupon_product_relation");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String deleteByExample(SmsCouponProductRelationExample example) {
        SQL sql = new SQL();
        sql.DELETE_FROM("sms_coupon_product_relation");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String insertSelective(SmsCouponProductRelation record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("sms_coupon_product_relation");
        
        if (record.getCouponId() != null) {
            sql.VALUES("coupon_id", "#{couponId,jdbcType=BIGINT}");
        }
        
        if (record.getProductId() != null) {
            sql.VALUES("product_id", "#{productId,jdbcType=BIGINT}");
        }
        
        if (record.getProductName() != null) {
            sql.VALUES("product_name", "#{productName,jdbcType=VARCHAR}");
        }
        
        if (record.getProductSn() != null) {
            sql.VALUES("product_sn", "#{productSn,jdbcType=VARCHAR}");
        }
        
        return sql.toString();
    }

    public String selectByExample(SmsCouponProductRelationExample example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("id");
        } else {
            sql.SELECT("id");
        }
        sql.SELECT("coupon_id");
        sql.SELECT("product_id");
        sql.SELECT("product_name");
        sql.SELECT("product_sn");
        sql.FROM("sms_coupon_product_relation");
        applyWhere(sql, example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            sql.ORDER_BY(example.getOrderByClause());
        }
        
        return sql.toString();
    }

    public String updateByExampleSelective(Map<String, Object> parameter) {
        SmsCouponProductRelation record = (SmsCouponProductRelation) parameter.get("record");
        SmsCouponProductRelationExample example = (SmsCouponProductRelationExample) parameter.get("example");
        
        SQL sql = new SQL();
        sql.UPDATE("sms_coupon_product_relation");
        
        if (record.getId() != null) {
            sql.SET("id = #{record.id,jdbcType=BIGINT}");
        }
        
        if (record.getCouponId() != null) {
            sql.SET("coupon_id = #{record.couponId,jdbcType=BIGINT}");
        }
        
        if (record.getProductId() != null) {
            sql.SET("product_id = #{record.productId,jdbcType=BIGINT}");
        }
        
        if (record.getProductName() != null) {
            sql.SET("product_name = #{record.productName,jdbcType=VARCHAR}");
        }
        
        if (record.getProductSn() != null) {
            sql.SET("product_sn = #{record.productSn,jdbcType=VARCHAR}");
        }
        
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByExample(Map<String, Object> parameter) {
        SQL sql = new SQL();
        sql.UPDATE("sms_coupon_product_relation");
        
        sql.SET("id = #{record.id,jdbcType=BIGINT}");
        sql.SET("coupon_id = #{record.couponId,jdbcType=BIGINT}");
        sql.SET("product_id = #{record.productId,jdbcType=BIGINT}");
        sql.SET("product_name = #{record.productName,jdbcType=VARCHAR}");
        sql.SET("product_sn = #{record.productSn,jdbcType=VARCHAR}");
        
        SmsCouponProductRelationExample example = (SmsCouponProductRelationExample) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(SmsCouponProductRelation record) {
        SQL sql = new SQL();
        sql.UPDATE("sms_coupon_product_relation");
        
        if (record.getCouponId() != null) {
            sql.SET("coupon_id = #{couponId,jdbcType=BIGINT}");
        }
        
        if (record.getProductId() != null) {
            sql.SET("product_id = #{productId,jdbcType=BIGINT}");
        }
        
        if (record.getProductName() != null) {
            sql.SET("product_name = #{productName,jdbcType=VARCHAR}");
        }
        
        if (record.getProductSn() != null) {
            sql.SET("product_sn = #{productSn,jdbcType=VARCHAR}");
        }
        
        sql.WHERE("id = #{id,jdbcType=BIGINT}");
        
        return sql.toString();
    }

    protected void applyWhere(SQL sql, SmsCouponProductRelationExample example, boolean includeExamplePhrase) {
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