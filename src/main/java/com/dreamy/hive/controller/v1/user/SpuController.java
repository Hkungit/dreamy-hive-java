package com.dreamy.hive.controller.v1.user;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dreamy.hive.common.Result;
import com.dreamy.hive.dto.response.ProductDetailDTO;
import com.dreamy.hive.entity.Spu;
import com.dreamy.hive.service.SpuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户端商品控制器
 */
@RestController
@RequestMapping("/api/v1/products")
@Tag(name = "商品", description = "商品相关接口")
public class SpuController {

    @Autowired
    private SpuService spuService;

    /**
     * 分页查询商品列表
     */
    @GetMapping
    @Operation(summary = "分页查询商品列表", description = "分页查询商品列表，支持按名称、分类搜索")
    public Result<Page<Spu>> listSpusByPage(
            @RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Long categoryId) {
        // 用户端只能查看上架商品
        return spuService.listSpusByPage(pageNum, pageSize, name, categoryId, 1);
    }

    /**
     * 获取商品详情
     */
    @GetMapping("/{id}")
    @Operation(summary = "获取商品详情", description = "根据ID获取商品详情")
    public Result<Spu> getSpuDetail(@PathVariable Long id) {
        return spuService.getSpuDetail(id);
    }

    /**
     * 获取商品详情页信息（包含SKU列表、图片轮播等）
     */
    @GetMapping("/{id}/detail")
    @Operation(summary = "获取商品详情页", description = "获取商品详情页信息，包含SKU列表、图片轮播等")
    public Result<ProductDetailDTO> getProductDetail(@PathVariable Long id) {
        return spuService.getProductDetail(id);
    }

    /**
     * 根据分类获取商品列表
     */
    @GetMapping("/category/{categoryId}")
    @Operation(summary = "获取分类商品", description = "根据分类ID获取商品列表")
    public Result<List<Spu>> getSpusByCategory(@PathVariable Long categoryId) {
        return spuService.getSpusByCategory(categoryId);
    }

    /**
     * 搜索商品
     */
    @GetMapping("/search")
    @Operation(summary = "搜索商品", description = "根据关键词搜索商品")
    public Result<Page<Spu>> searchSpus(
            @RequestParam String keyword,
            @RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(defaultValue = "10") int pageSize) {
        return spuService.searchSpus(keyword, pageNum, pageSize);
    }
}