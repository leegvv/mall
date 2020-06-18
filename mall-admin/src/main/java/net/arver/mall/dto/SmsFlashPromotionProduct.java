package net.arver.mall.dto;

import io.swagger.annotations.ApiModelProperty;
import net.arver.mall.model.PmsProduct;
import net.arver.mall.model.SmsFlashPromotionProductRelation;

/**
 * 限时购及商品信息封装
 */
public class SmsFlashPromotionProduct extends SmsFlashPromotionProductRelation {

    @ApiModelProperty("关联商品")
    private PmsProduct product;

    public PmsProduct getProduct() {
        return product;
    }

    public void setProduct(final PmsProduct product) {
        this.product = product;
    }
}
