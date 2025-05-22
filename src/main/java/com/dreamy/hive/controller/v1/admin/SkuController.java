package com.dreamy.hive.controller.v1.admin;

import com.dreamy.hive.annotation.RequiresPermission;
import com.dreamy.hive.annotation.RequiresRole;
import com.dreamy.hive.common.Result;
import com.dreamy.hive.dto.request.SkuWithAttributesDTO;
import com.dreamy.hive.entity.Sku;
import com.dreamy.hive.service.SkuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商品SKU管理控制器
 */
@RestController
@RequestMapping("/v1/admin/skus")
@Tag(name = "管理端-SKU管理", description = "管理端SKU相关接口")
public class SkuController {

    @Autowired
    private SkuService skuService;

    /**
     * 获取SPU下的SKU列表
     */
    @GetMapping("/spu/{spuId}")
    @Operation(summary = "获取SPU下的SKU列表", description = "根据SPU ID获取SKU列表")
    public Result<List<Sku>> getSkusBySpuId(@PathVariable Long spuId) {
        return skuService.getSkusBySpuId(spuId);
    }

    /**
     * 获取SKU详情
     */
    @GetMapping("/{id}")
    @Operation(summary = "获取SKU详情", description = "根据ID获取SKU详情")
    public Result<Sku> getSkuDetail(@PathVariable Long id) {
        return skuService.getSkuDetail(id);
    }

    /**
     * 获取SKU及其属性
     */
    @GetMapping("/{id}/attributes")
    @Operation(summary = "获取SKU及其属性", description = "根据ID获取SKU及其属性信息")
    public Result<SkuWithAttributesDTO> getSkuWithAttributes(@PathVariable Long id) {
        return skuService.getSkuWithAttributes(id);
    }

    /**
     * 添加SKU
     */
    @PostMapping
    @Operation(summary = "添加SKU", description = "添加新的商品SKU")
    public Result<?> addSku(@RequestBody Sku sku) {
        return skuService.addSku(sku);
    }

    /**
     * 添加SKU及其属性
     */
    @PostMapping("/with-attributes")
    @Operation(summary = "添加SKU及其属性", description = "添加新的商品SKU及其属性")
    public Result<?> addSkuWithAttributes(@RequestBody SkuWithAttributesDTO skuWithAttributes) {
        return skuService.addSkuWithAttributes(skuWithAttributes);
    }

    /**
     * 更新SKU
     */
    @PutMapping("/{id}")
    @Operation(summary = "更新SKU", description = "根据ID更新SKU信息")
    public Result<?> updateSku(@PathVariable Long id, @RequestBody Sku sku) {
        return skuService.updateSku(id, sku);
    }

    /**
     * 更新SKU及其属性
     */
    @PutMapping("/{id}/with-attributes")
    @Operation(summary = "更新SKU及其属性", description = "根据ID更新SKU及其属性信息")
    public Result<?> updateSkuWithAttributes(@PathVariable Long id, @RequestBody SkuWithAttributesDTO skuWithAttributes) {
        return skuService.updateSkuWithAttributes(id, skuWithAttributes);
    }

    /**
     * 删除SKU
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "删除SKU", description = "根据ID删除SKU")
    public Result<?> deleteSku(@PathVariable Long id) {
        return skuService.deleteSku(id);
    }

    /**
     * 更新SKU状态
     */
    @PutMapping("/{id}/status/{status}")
    @Operation(summary = "更新SKU状态", description = "根据ID更新SKU状态")
    public Result<?> updateSkuStatus(@PathVariable Long id, @PathVariable Integer status) {
        return skuService.updateSkuStatus(id, status);
    }
}