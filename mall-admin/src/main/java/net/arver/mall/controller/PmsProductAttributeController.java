package net.arver.mall.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.arver.mall.common.api.JsonResult;
import net.arver.mall.common.api.PageResult;
import net.arver.mall.dto.PmsProductAttributeParam;
import net.arver.mall.dto.ProductAttrInfo;
import net.arver.mall.model.PmsProductAttribute;
import net.arver.mall.service.PmsProductAttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "PmsProductAttributeController", description = "商品属性管理")
@RestController
@RequestMapping("/productAttribute")
public class PmsProductAttributeController {

    @Autowired
    private PmsProductAttributeService productAttributeService;

    @ApiOperation("根据分类查询属性列表或参数列表")
    @ApiImplicitParams({@ApiImplicitParam(name = "type", value = "0表示属性，1表示参数", required = true, paramType = "query", dataType = "integer")})
    @GetMapping("/list/{cid}")
    public JsonResult<PageResult<PmsProductAttribute>> getList(@PathVariable final Long cid,
                                                         @RequestParam(value = "type") final Integer type,
                                                         @RequestParam(value = "pageNum") final Integer pageNum,
                                                         @RequestParam(value = "pageSize") final Integer pageSize) {
        final List<PmsProductAttribute> list = productAttributeService.list(cid, type, pageSize, pageNum);
        return JsonResult.success(PageResult.restPage(list));
    }

    @ApiOperation("添加商品属性信息")
    @PostMapping("/crate")
    public JsonResult create(@RequestBody final PmsProductAttributeParam productAttributeParam, final BindResult result) {
        final int count = productAttributeService.create(productAttributeParam);
        if (count > 0) {
            return JsonResult.success(count);
        } else {
            return JsonResult.failed();
        }
    }

    @ApiOperation("修改商品属性信息")
    @PostMapping("/update/{id}")
    public JsonResult update(@PathVariable final Long id, @RequestBody final PmsProductAttributeParam productAttributeParam,
                             final BindResult result) {
        final int count = productAttributeService.update(id, productAttributeParam);
        if (count > 0) {
            return JsonResult.success(count);
        } else {
            return JsonResult.failed();
        }
    }

    @ApiOperation("查询单个商品属性")
    @GetMapping("/{id}")
    public JsonResult<PmsProductAttribute> getItem(@PathVariable final Long id) {
        final PmsProductAttribute productAttribute = productAttributeService.getItem(id);
        return JsonResult.success(productAttribute);
    }

    @ApiOperation("批量删除商品属性")
    @PostMapping("/delete")
    public JsonResult delete(@RequestParam("ids") final List<Long> ids) {
        final int count = productAttributeService.delete(ids);
        if (count > 0) {
            return JsonResult.success(count);
        } else {
            return JsonResult.failed();
        }
    }

    @ApiOperation("根据商品分类的id获取商品属性及属性分类")
    @GetMapping("/attrInfo/{productCategoryId}")
    public JsonResult<List<ProductAttrInfo>> getAttrInfo(@PathVariable final Long productCategoryId) {
        final List<ProductAttrInfo> productAttrInfoList = productAttributeService.getProductAttrInfo(productCategoryId);
        return JsonResult.success(productAttrInfoList);
    }



}
