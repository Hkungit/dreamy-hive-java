package com.dreamy.hive.controller.v1.admin;

import com.dreamy.hive.annotation.RequiresRole;
import com.dreamy.hive.common.Result;
import com.dreamy.hive.entity.ProductImage;
import com.dreamy.hive.service.FileStorageService;
import com.dreamy.hive.service.ProductImageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 商品图片管理控制器
 */
@Slf4j
@RestController
@RequestMapping("/v1/admin/product-images")
@Tag(name = "管理端-商品图片管理", description = "管理端商品图片相关接口")
public class ProductImageController {

    @Autowired
    private ProductImageService productImageService;

    @Autowired
    private FileStorageService fileStorageService;

    /**
     * 上传商品图片
     */
    @PostMapping("/upload/{type}")
    @Operation(summary = "上传商品图片", description = "上传商品图片，type: 1-主图，2-详情图")
    public Result<String> uploadProductImage(
            @RequestParam("file") MultipartFile file,
            @PathVariable Integer type) {

        // 根据类型确定存储目录
        String directory = type == 1 ? "product/main" : "product/detail";

        // 存储文件
        Result<String> result = fileStorageService.store(file, directory);
        if (!result.isSuccess()) {
            return result;
        }

        return result;
    }

    /**
     * 批量上传商品图片
     */
    @PostMapping("/upload-multiple/{type}")
    @Operation(summary = "批量上传商品图片", description = "批量上传商品图片，type: 1-主图，2-详情图")
    public Result<List<String>> uploadMultipleProductImages(
            @RequestParam("files") MultipartFile[] files,
            @PathVariable Integer type) {

        // 根据类型确定存储目录
        String directory = type == 1 ? "product/main" : "product/detail";

        // 存储文件
        return fileStorageService.storeAll(files, directory);
    }

    /**
     * 添加商品图片
     */
    @PostMapping
    @Operation(summary = "添加商品图片", description = "添加商品图片信息")
    public Result<?> addProductImage(@RequestBody ProductImage productImage) {
        return productImageService.addProductImage(productImage);
    }

    /**
     * 批量添加商品图片
     */
    @PostMapping("/batch")
    @Operation(summary = "批量添加商品图片", description = "批量添加商品图片信息")
    public Result<?> batchAddProductImages(@RequestBody List<ProductImage> productImages) {
        return productImageService.batchAddProductImages(productImages);
    }

    /**
     * 获取商品图片列表
     */
    @GetMapping("/spu/{spuId}")
    @Operation(summary = "获取商品图片列表", description = "根据SPU ID获取商品图片列表")
    public Result<List<ProductImage>> getProductImagesBySpuId(
            @PathVariable Long spuId,
            @RequestParam(required = false) Integer type) {
        return productImageService.getProductImagesBySpuId(spuId, type);
    }

    /**
     * 获取SKU图片列表
     */
    @GetMapping("/sku/{skuId}")
    @Operation(summary = "获取SKU图片列表", description = "根据SKU ID获取SKU图片列表")
    public Result<List<ProductImage>> getProductImagesBySkuId(@PathVariable Long skuId) {
        return productImageService.getProductImagesBySkuId(skuId);
    }

    /**
     * 删除商品图片
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "删除商品图片", description = "根据ID删除商品图片")
    public Result<?> deleteProductImage(@PathVariable Long id) {
        return productImageService.deleteProductImage(id);
    }

    /**
     * 更新商品图片排序
     */
    @PutMapping("/{id}/sort/{sort}")
    @Operation(summary = "更新商品图片排序", description = "根据ID更新商品图片排序")
    public Result<?> updateProductImageSort(
            @PathVariable Long id,
            @PathVariable Integer sort) {
        return productImageService.updateProductImageSort(id, sort);
    }
}