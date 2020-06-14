package net.arver.mall.dto;

import io.swagger.annotations.ApiModelProperty;
import net.arver.mall.validator.FlagValidator;

import javax.validation.constraints.NotEmpty;

/**
 * 商品属性参数
 */
public class PmsProductAttributeParam {

    @ApiModelProperty("属性分类ID")
    @NotEmpty(message = "属性分类不能为空")
    private Long productAttributeCategoryId;

    @ApiModelProperty("属性名称")
    @NotEmpty(message = "属性名称不能为空")
    private String name;

    @ApiModelProperty("属性选择类型：0->唯一；1:->单选；2：->多选")
    @FlagValidator({"0", "1", "2"})
    private Integer selectType;

    @ApiModelProperty("属性输入方式：0->手工录入；2：->从列表中选取")
    @FlagValidator({"0", "1"})
    private Integer inputType;

    @ApiModelProperty("可选值列表，以逗号隔开")
    private String inputList;

    @ApiModelProperty("排序")
    private Integer sort;

    @ApiModelProperty("分类筛选样式：0->普通；1->颜色")
    @FlagValidator({"0", "1"})
    private Integer filterType;

    @ApiModelProperty("检索类型：0->不需要进行检索；1->关键字检索；2->范围检索")
    @FlagValidator({"0", "1", "2"})
    private Integer searchType;

    @ApiModelProperty("相同属性产品是否关联：0->不关联； 1-> 关联")
    @FlagValidator({"0", "1"})
    private Integer relatedStatus;

    @ApiModelProperty("是否支持手机新增：0->不支持；1->支持")
    @FlagValidator({"0", "1"})
    private Integer handAddStatus;

    @ApiModelProperty("属性的类型：0->规格；1->参数")
    @FlagValidator({"0", "1"})
    private Integer type;

    public Long getProductAttributeCategoryId() {
        return productAttributeCategoryId;
    }

    public void setProductAttributeCategoryId(final Long productAttributeCategoryId) {
        this.productAttributeCategoryId = productAttributeCategoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Integer getSelectType() {
        return selectType;
    }

    public void setSelectType(final Integer selectType) {
        this.selectType = selectType;
    }

    public Integer getInputType() {
        return inputType;
    }

    public void setInputType(final Integer inputType) {
        this.inputType = inputType;
    }

    public String getInputList() {
        return inputList;
    }

    public void setInputList(final String inputList) {
        this.inputList = inputList;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(final Integer sort) {
        this.sort = sort;
    }

    public Integer getFilterType() {
        return filterType;
    }

    public void setFilterType(final Integer filterType) {
        this.filterType = filterType;
    }

    public Integer getSearchType() {
        return searchType;
    }

    public void setSearchType(final Integer searchType) {
        this.searchType = searchType;
    }

    public Integer getRelatedStatus() {
        return relatedStatus;
    }

    public void setRelatedStatus(final Integer relatedStatus) {
        this.relatedStatus = relatedStatus;
    }

    public Integer getHandAddStatus() {
        return handAddStatus;
    }

    public void setHandAddStatus(final Integer handAddStatus) {
        this.handAddStatus = handAddStatus;
    }

    public Integer getType() {
        return type;
    }

    public void setType(final Integer type) {
        this.type = type;
    }
}
