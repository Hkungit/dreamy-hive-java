package com.dreamy.hive.controller.v1.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dreamy.hive.annotation.RequiresPermission;
import com.dreamy.hive.annotation.RequiresRole;
import com.dreamy.hive.common.Result;
import com.dreamy.hive.entity.Category;
import com.dreamy.hive.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 管理端商品分类控制器
 */
@RestController
@RequestMapping("/v1/admin/categories")
@Tag(name = "管理端-商品分类", description = "管理端商品分类相关接口")
public class AdminCategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * 获取分类树形结构
     */
    @GetMapping("/tree")
    @Operation(summary = "获取分类树形结构", description = "获取所有分类的树形结构")
    public Result<List<Category>> getCategoryTree() {
        return categoryService.getCategoryTree();
    }

    /**
     * 分页查询分类列表
     */
    @GetMapping
    @Operation(summary = "分页查询分类列表", description = "分页查询分类列表，支持按名称搜索")
    public Result<Page<Category>> listCategoriesByPage(
            @RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(required = false) String name) {
        return categoryService.listCategoriesByPage(pageNum, pageSize, name);
    }

    /**
     * 添加分类
     */
    @PostMapping
    @Operation(summary = "添加分类", description = "添加新的商品分类")
    public Result<?> addCategory(@RequestBody Category category) {
        return categoryService.addCategory(category);
    }

    /**
     * 更新分类
     */
    @PutMapping("/{id}")
    @Operation(summary = "更新分类", description = "根据ID更新商品分类")
    public Result<?> updateCategory(@PathVariable Long id, @RequestBody Category category) {
        return categoryService.updateCategory(id, category);
    }

    /**
     * 删除分类
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "删除分类", description = "根据ID删除商品分类")
    public Result<?> deleteCategory(@PathVariable Long id) {
        return categoryService.deleteCategory(id);
    }

    /**
     * 获取分类详情
     */
    @GetMapping("/{id}")
    @Operation(summary = "获取分类详情", description = "根据ID获取分类详情")
    public Result<Category> getCategoryDetail(@PathVariable Long id) {
        return categoryService.getCategoryDetail(id);
    }
}