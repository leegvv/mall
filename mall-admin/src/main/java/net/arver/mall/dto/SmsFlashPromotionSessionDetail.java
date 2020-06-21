package net.arver.mall.dto;

import io.swagger.annotations.ApiModelProperty;
import net.arver.mall.model.SmsFlashPromotionSession;

/**
 * 包含商品数量的场次信息
 */
public class SmsFlashPromotionSessionDetail extends SmsFlashPromotionSession {

    @ApiModelProperty("商品数量")
    private Long productCount;

    public Long getProductCount() {
        return productCount;
    }

    public void setProductCount(final Long productCount) {
        this.productCount = productCount;
    }
}
