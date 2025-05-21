package com.dreamy.hive.controller.v1.admin;

import com.dreamy.hive.common.Result;
import com.dreamy.hive.entity.Attribute;
import com.dreamy.hive.service.AttributeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商品属性管理控制器
 */
@RestController
@RequestMapping("/api/v1/admin/attributes")
@Tag(name = "属性管理", description = "商品属性管理相关接口")
public class AttributeController {

    @Autowired
    private AttributeService attributeService;

    /**
     * 获取分类下的属性列表
     */
    @GetMapping("/category/{categoryId}")
    @Operation(summary = "获取分类属性", description = "根据分类ID获取属性列表")
    public Result<List<Attribute>> getAttributesByCategory(@PathVariable Long categoryId) {
        return attributeService.getAttributesByCategory(categoryId);
    }

    /**
     * 添加属性
     */
    @PostMapping
    @Operation(summary = "添加属性", description = "添加新的商品属性")
    public Result<?> addAttribute(@RequestBody Attribute attribute) {
        return attributeService.addAttribute(attribute);
    }

    /**
     * 更新属性
     */
    @PutMapping("/{id}")
    @Operation(summary = "更新属性", description = "根据ID更新属性信息")
    public Result<?> updateAttribute(@PathVariable Long id, @RequestBody Attribute attribute) {
        return attributeService.updateAttribute(id, attribute);
    }

    /**
     * 删除属性
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "删除属性", description = "根据ID删除属性")
    public Result<?> deleteAttribute(@PathVariable Long id) {
        return attributeService.deleteAttribute(id);
    }
}