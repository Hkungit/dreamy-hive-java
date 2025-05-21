package com.dreamy.hive.controller.v1.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dreamy.hive.common.Result;
import com.dreamy.hive.entity.Spu;
import com.dreamy.hive.service.SpuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 管理端商品SPU控制器
 */
@RestController
@RequestMapping("/v1/admin/products")
@Tag(name = "管理端-商品管理", description = "管理端商品相关接口")
public class AdminSpuController {

    @Autowired
    private SpuService spuService;

    /**
     * 分页查询商品列表
     */
    @GetMapping
    @Operation(summary = "分页查询商品列表", description = "分页查询商品列表，支持按名称、分类、状态搜索")
    public Result<Page<Spu>> listSpusByPage(
            @RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) Integer status) {
        return spuService.listSpusByPage(pageNum, pageSize, name, categoryId, status);
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
     * 添加商品
     */
    @PostMapping
    @Operation(summary = "添加商品", description = "添加新的商品")
    public Result<?> addSpu(@RequestBody Spu spu) {
        return spuService.addSpu(spu);
    }

    /**
     * 更新商品
     */
    @PutMapping("/{id}")
    @Operation(summary = "更新商品", description = "根据ID更新商品")
    public Result<?> updateSpu(@PathVariable Long id, @RequestBody Spu spu) {
        return spuService.updateSpu(id, spu);
    }

    /**
     * 删除商品
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "删除商品", description = "根据ID删除商品")
    public Result<?> deleteSpu(@PathVariable Long id) {
        return spuService.deleteSpu(id);
    }

    /**
     * 更新商品状态（上下架）
     */
    @PutMapping("/{id}/status")
    @Operation(summary = "更新商品状态", description = "更新商品上下架状态")
    public Result<?> updateSpuStatus(@PathVariable Long id, @RequestParam Integer status) {
        return spuService.updateSpuStatus(id, status);
    }
}