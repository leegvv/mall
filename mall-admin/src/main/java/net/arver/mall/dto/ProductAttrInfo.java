package net.arver.mall.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * 商品分类对应属性信息
 */
public class ProductAttrInfo {

    @ApiModelProperty("商品属性ID")
    private Long attributeId;

    @ApiModelProperty("商品属性分类ID")
    private Long attributeCategoryId;

    public Long getAttributeId() {
        return attributeId;
    }

    public void setAttributeId(final Long attributeId) {
        this.attributeId = attributeId;
    }

    public Long getAttributeCategoryId() {
        return attributeCategoryId;
    }

    public void setAttributeCategoryId(final Long attributeCategoryId) {
        this.attributeCategoryId = attributeCategoryId;
    }
}
