package net.arver.mall.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.arver.mall.common.api.JsonResult;
import net.arver.mall.common.api.PageResult;
import net.arver.mall.dto.SmsFlashPromotionProduct;
import net.arver.mall.model.SmsFlashPromotionProductRelation;
import net.arver.mall.service.SmsFlashPromotionProductRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 限时购和商品关系管理Controller
 */
@Api(tags = "SmsFlashPromotionProductRelationController")
@RestController
@RequestMapping("/flashProductRelation")
public class SmsFlashPromotionProductRelationController {

    @Autowired
    private SmsFlashPromotionProductRelationService relationService;

    @ApiOperation("批量选择商品添加关联")
    @PostMapping("/create")
    public JsonResult create(@RequestBody final List<SmsFlashPromotionProductRelation> relationList) {
        final int count = relationService.create(relationList);
        if (count > 0) {
            return JsonResult.success(count);
        }
        return JsonResult.failed();
    }

    @ApiOperation("修改关联相关信息")
    @PostMapping("/update/{id}")
    public JsonResult update(@PathVariable final Long id, @RequestBody SmsFlashPromotionProductRelation relation) {
        final int count = relationService.update(id, relation);
        if (count > 0) {
            return JsonResult.success(count);
        }
        return JsonResult.failed();
    }

    @ApiOperation("删除关联")
    @GetMapping("/delete/{id}")
    public JsonResult delete(@PathVariable final Long id) {
        final int count = relationService.delete(id);
        if (count > 0) {
            return JsonResult.success(count);
        }
        return JsonResult.failed();
    }

    @ApiOperation("获取管理商品促销信息")
    @GetMapping("/{id}")
    public JsonResult<SmsFlashPromotionProductRelation> getItem(@PathVariable final Long id) {
        final SmsFlashPromotionProductRelation relation = relationService.getItem(id);
        return JsonResult.success(relation);
    }

    @ApiOperation("分页查询不同场次关联及商品信息")
    @GetMapping("/list")
    public JsonResult<PageResult<SmsFlashPromotionProduct>> list(@RequestParam("flashPromotionId") final Long flashPromotionId,
                                                                 @RequestParam("flashPromotionSessionId") final Long flashPromotionSessionId,
                                                                 @RequestParam(value = "pageNum", defaultValue = "1")final Integer pageNum,
                                                                 @RequestParam(value = "pageSize", defaultValue = "5")final Integer pageSize) {
        final List<SmsFlashPromotionProduct> flashPromotionProductList =
            relationService.list(flashPromotionId, flashPromotionSessionId, pageNum, pageSize);
        return JsonResult.success(PageResult.restPage(flashPromotionProductList));
    }


}
