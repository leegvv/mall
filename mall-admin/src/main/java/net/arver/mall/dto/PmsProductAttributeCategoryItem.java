package net.arver.mall.dto;

import io.swagger.annotations.ApiModelProperty;
import net.arver.mall.model.PmsProductAttribute;
import net.arver.mall.model.PmsProductAttributeCategory;

import java.util.List;

/**
 * 包含有分类下属性的dto
 */
public class PmsProductAttributeCategoryItem extends PmsProductAttributeCategory {

    @ApiModelProperty(value = "商品属性列表")
    private List<PmsProductAttribute> productAttributeList;

    public List<PmsProductAttribute> getProductAttributeList() {
        return productAttributeList;
    }

    public void setProductAttributeList(final List<PmsProductAttribute> productAttributeList) {
        this.productAttributeList = productAttributeList;
    }
}
