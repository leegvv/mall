package net.arver.mall.dto;

import io.swagger.annotations.ApiModelProperty;
import net.arver.mall.model.PmsProductCategory;

import java.util.List;

public class PmsProductCategoryWithChildrenItem extends PmsProductCategory {

    @ApiModelProperty("子级分类")
    private List<PmsProductCategory> children;

    public List<PmsProductCategory> getChildren() {
        return children;
    }

    public void setChildren(final List<PmsProductCategory> children) {
        this.children = children;
    }
}
