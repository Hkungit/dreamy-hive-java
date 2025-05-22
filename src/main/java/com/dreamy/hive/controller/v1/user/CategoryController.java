package com.dreamy.hive.controller.v1.user;

import com.dreamy.hive.common.Result;
import com.dreamy.hive.entity.Category;
import com.dreamy.hive.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商品分类控制器
 */
@RestController
@RequestMapping("/v1/categories")
@Tag(name = "商品分类", description = "商品分类相关接口")
public class CategoryController {

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
     * 根据父级ID获取子分类列表
     */
    @GetMapping("/children/{parentId}")
    @Operation(summary = "获取子分类", description = "根据父级ID获取子分类列表")
    public Result<List<Category>> getChildCategories(@PathVariable Long parentId) {
        return categoryService.getChildCategories(parentId);
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