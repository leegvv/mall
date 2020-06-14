package net.arver.mall.dto;

import io.swagger.annotations.ApiModelProperty;
import net.arver.mall.validator.FlagValidator;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * 添加更新产品分了的参数
 */
public class PmsProductCategoryParam {

    @ApiModelProperty("父分类的编号")
    private Long parentId;

    @ApiModelProperty(value = "商品分类名称", required = true)
    @NotEmpty(message = "商品分类名称不能为空")
    private String name;

    @ApiModelProperty("分类单位")
    private String productUnit;

    @ApiModelProperty("是否在导航栏显示")
    @FlagValidator(value = {"0", "1"}, message = "状态只能为0或1")
    private Integer navStatus;

    @ApiModelProperty("是否进行显示")
    @FlagValidator(value = {"0", "1"}, message = "状态只能为0或1")
    private Integer showStatus;

    @ApiModelProperty("排序")
    @Min(value = 0, message = "排序最小为0")
    private Integer sort;

    @ApiModelProperty("图标")
    private String icon;

    @ApiModelProperty("关键字")
    private String keywords;

    @ApiModelProperty("描述")
    private String description;

    @ApiModelProperty("产品相关筛选属性集合")
    private List<Long> productAttributeIdList;

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(final Long parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getProductUnit() {
        return productUnit;
    }

    public void setProductUnit(final String productUnit) {
        this.productUnit = productUnit;
    }

    public Integer getNavStatus() {
        return navStatus;
    }

    public void setNavStatus(final Integer navStatus) {
        this.navStatus = navStatus;
    }

    public Integer getShowStatus() {
        return showStatus;
    }

    public void setShowStatus(final Integer showStatus) {
        this.showStatus = showStatus;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(final Integer sort) {
        this.sort = sort;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(final String icon) {
        this.icon = icon;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(final String keywords) {
        this.keywords = keywords;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public List<Long> getProductAttributeIdList() {
        return productAttributeIdList;
    }

    public void setProductAttributeIdList(final List<Long> productAttributeIdList) {
        this.productAttributeIdList = productAttributeIdList;
    }
}
