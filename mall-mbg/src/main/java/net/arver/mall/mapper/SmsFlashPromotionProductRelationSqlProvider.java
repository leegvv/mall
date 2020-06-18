package net.arver.mall.mapper;

import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import java.util.Map;
import net.arver.mall.model.SmsFlashPromotionProductRelation;
import net.arver.mall.model.SmsFlashPromotionProductRelationExample.Criteria;
import net.arver.mall.model.SmsFlashPromotionProductRelationExample.Criterion;
import net.arver.mall.model.SmsFlashPromotionProductRelationExample;
import org.apache.ibatis.jdbc.SQL;

public class SmsFlashPromotionProductRelationSqlProvider {

    public String countByExample(SmsFlashPromotionProductRelationExample example) {
        SQL sql = new SQL();
        sql.SELECT("count(*)").FROM("sms_flash_promotion_product_relation");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String deleteByExample(SmsFlashPromotionProductRelationExample example) {
        SQL sql = new SQL();
        sql.DELETE_FROM("sms_flash_promotion_product_relation");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String insertSelective(SmsFlashPromotionProductRelation record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("sms_flash_promotion_product_relation");
        
        if (record.getFlashPromotionId() != null) {
            sql.VALUES("flash_promotion_id", "#{flashPromotionId,jdbcType=BIGINT}");
        }
        
        if (record.getFlashPromotionSessionId() != null) {
            sql.VALUES("flash_promotion_session_id", "#{flashPromotionSessionId,jdbcType=BIGINT}");
        }
        
        if (record.getProductId() != null) {
            sql.VALUES("product_id", "#{productId,jdbcType=BIGINT}");
        }
        
        if (record.getFlashPromotionPrice() != null) {
            sql.VALUES("flash_promotion_price", "#{flashPromotionPrice,jdbcType=DECIMAL}");
        }
        
        if (record.getFlashPromotionCount() != null) {
            sql.VALUES("flash_promotion_count", "#{flashPromotionCount,jdbcType=INTEGER}");
        }
        
        if (record.getFlashPromotionLimit() != null) {
            sql.VALUES("flash_promotion_limit", "#{flashPromotionLimit,jdbcType=INTEGER}");
        }
        
        if (record.getSort() != null) {
            sql.VALUES("sort", "#{sort,jdbcType=INTEGER}");
        }
        
        return sql.toString();
    }

    public String selectByExample(SmsFlashPromotionProductRelationExample example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("id");
        } else {
            sql.SELECT("id");
        }
        sql.SELECT("flash_promotion_id");
        sql.SELECT("flash_promotion_session_id");
        sql.SELECT("product_id");
        sql.SELECT("flash_promotion_price");
        sql.SELECT("flash_promotion_count");
        sql.SELECT("flash_promotion_limit");
        sql.SELECT("sort");
        sql.FROM("sms_flash_promotion_product_relation");
        applyWhere(sql, example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            sql.ORDER_BY(example.getOrderByClause());
        }
        
        return sql.toString();
    }

    public String updateByExampleSelective(Map<String, Object> parameter) {
        SmsFlashPromotionProductRelation record = (SmsFlashPromotionProductRelation) parameter.get("record");
        SmsFlashPromotionProductRelationExample example = (SmsFlashPromotionProductRelationExample) parameter.get("example");
        
        SQL sql = new SQL();
        sql.UPDATE("sms_flash_promotion_product_relation");
        
        if (record.getId() != null) {
            sql.SET("id = #{record.id,jdbcType=BIGINT}");
        }
        
        if (record.getFlashPromotionId() != null) {
            sql.SET("flash_promotion_id = #{record.flashPromotionId,jdbcType=BIGINT}");
        }
        
        if (record.getFlashPromotionSessionId() != null) {
            sql.SET("flash_promotion_session_id = #{record.flashPromotionSessionId,jdbcType=BIGINT}");
        }
        
        if (record.getProductId() != null) {
            sql.SET("product_id = #{record.productId,jdbcType=BIGINT}");
        }
        
        if (record.getFlashPromotionPrice() != null) {
            sql.SET("flash_promotion_price = #{record.flashPromotionPrice,jdbcType=DECIMAL}");
        }
        
        if (record.getFlashPromotionCount() != null) {
            sql.SET("flash_promotion_count = #{record.flashPromotionCount,jdbcType=INTEGER}");
        }
        
        if (record.getFlashPromotionLimit() != null) {
            sql.SET("flash_promotion_limit = #{record.flashPromotionLimit,jdbcType=INTEGER}");
        }
        
        if (record.getSort() != null) {
            sql.SET("sort = #{record.sort,jdbcType=INTEGER}");
        }
        
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByExample(Map<String, Object> parameter) {
        SQL sql = new SQL();
        sql.UPDATE("sms_flash_promotion_product_relation");
        
        sql.SET("id = #{record.id,jdbcType=BIGINT}");
        sql.SET("flash_promotion_id = #{record.flashPromotionId,jdbcType=BIGINT}");
        sql.SET("flash_promotion_session_id = #{record.flashPromotionSessionId,jdbcType=BIGINT}");
        sql.SET("product_id = #{record.productId,jdbcType=BIGINT}");
        sql.SET("flash_promotion_price = #{record.flashPromotionPrice,jdbcType=DECIMAL}");
        sql.SET("flash_promotion_count = #{record.flashPromotionCount,jdbcType=INTEGER}");
        sql.SET("flash_promotion_limit = #{record.flashPromotionLimit,jdbcType=INTEGER}");
        sql.SET("sort = #{record.sort,jdbcType=INTEGER}");
        
        SmsFlashPromotionProductRelationExample example = (SmsFlashPromotionProductRelationExample) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(SmsFlashPromotionProductRelation record) {
        SQL sql = new SQL();
        sql.UPDATE("sms_flash_promotion_product_relation");
        
        if (record.getFlashPromotionId() != null) {
            sql.SET("flash_promotion_id = #{flashPromotionId,jdbcType=BIGINT}");
        }
        
        if (record.getFlashPromotionSessionId() != null) {
            sql.SET("flash_promotion_session_id = #{flashPromotionSessionId,jdbcType=BIGINT}");
        }
        
        if (record.getProductId() != null) {
            sql.SET("product_id = #{productId,jdbcType=BIGINT}");
        }
        
        if (record.getFlashPromotionPrice() != null) {
            sql.SET("flash_promotion_price = #{flashPromotionPrice,jdbcType=DECIMAL}");
        }
        
        if (record.getFlashPromotionCount() != null) {
            sql.SET("flash_promotion_count = #{flashPromotionCount,jdbcType=INTEGER}");
        }
        
        if (record.getFlashPromotionLimit() != null) {
            sql.SET("flash_promotion_limit = #{flashPromotionLimit,jdbcType=INTEGER}");
        }
        
        if (record.getSort() != null) {
            sql.SET("sort = #{sort,jdbcType=INTEGER}");
        }
        
        sql.WHERE("id = #{id,jdbcType=BIGINT}");
        
        return sql.toString();
    }

    protected void applyWhere(SQL sql, SmsFlashPromotionProductRelationExample example, boolean includeExamplePhrase) {
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