package com.dreamy.hive.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dreamy.hive.common.Result;
import com.dreamy.hive.entity.Category;
import com.dreamy.hive.entity.Spu;
import com.dreamy.hive.mapper.CategoryMapper;
import com.dreamy.hive.service.CategoryService;
import com.dreamy.hive.service.SpuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    private final SpuService spuService;

    public CategoryServiceImpl(@Lazy SpuService spuService) {
        this.spuService = spuService;
    }

    @Override
    public Result<List<Category>> getCategoryTree() {
        try {
            // 获取所有分类
            List<Category> allCategories = this.list();
            
            // 构建树形结构
            List<Category> rootCategories = buildCategoryTree(allCategories);
            
            return Result.success(rootCategories);
        } catch (Exception e) {
            log.error("获取分类树失败", e);
            return Result.fail("获取分类树失败: " + e.getMessage());
        }
    }

    @Override
    public Result<List<Category>> getChildCategories(Long parentId) {
        try {
            LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(Category::getParentId, parentId)
                    .eq(Category::getStatus, 1)
                    .orderByAsc(Category::getSort);
            
            List<Category> childCategories = this.list(queryWrapper);
            return Result.success(childCategories);
        } catch (Exception e) {
            log.error("获取子分类列表失败", e);
            return Result.fail("获取子分类列表失败: " + e.getMessage());
        }
    }

    @Override
    public Result<Page<Category>> listCategoriesByPage(int pageNum, int pageSize, String name) {
        try {
            Page<Category> page = new Page<>(pageNum, pageSize);
            LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
            
            // 添加查询条件（如果有）
            if (StringUtils.hasText(name)) {
                queryWrapper.like(Category::getName, name);
            }
            
            // 排序
            queryWrapper.orderByAsc(Category::getSort);
            
            // 执行查询
            Page<Category> categoryPage = this.page(page, queryWrapper);
            return Result.success(categoryPage);
        } catch (Exception e) {
            log.error("分页查询分类列表失败", e);
            return Result.fail("分页查询分类列表失败: " + e.getMessage());
        }
    }

    @Override
    public Result<?> addCategory(Category category) {
        try {
            // 检查分类名称是否已存在
            LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(Category::getName, category.getName())
                    .eq(Category::getParentId, category.getParentId());
            
            if (this.count(queryWrapper) > 0) {
                return Result.fail("分类名称已存在");
            }
            
            // 设置默认值
            if (category.getSort() == null) {
                category.setSort(0);
            }
            
            if (category.getStatus() == null) {
                category.setStatus(1); // 默认启用
            }
            
            // 设置分类级别
            if (category.getParentId() == 0) {
                category.setLevel(1); // 一级分类
            } else {
                Category parentCategory = this.getById(category.getParentId());
                if (parentCategory == null) {
                    return Result.fail("父分类不存在");
                }
                category.setLevel(2); // 二级分类
            }
            
            // 保存分类
            boolean success = this.save(category);
            if (success) {
                return Result.success(category);
            } else {
                return Result.fail("添加分类失败");
            }
        } catch (Exception e) {
            log.error("添加分类失败", e);
            return Result.fail("添加分类失败: " + e.getMessage());
        }
    }

    @Override
    public Result<?> updateCategory(Long id, Category category) {
        try {
            // 检查分类是否存在
            Category existingCategory = this.getById(id);
            if (existingCategory == null) {
                return Result.fail("分类不存在");
            }
            
            // 检查分类名称是否已存在（排除自身）
            LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(Category::getName, category.getName())
                    .eq(Category::getParentId, category.getParentId())
                    .ne(Category::getId, id);
            
            if (this.count(queryWrapper) > 0) {
                return Result.fail("分类名称已存在");
            }
            
            // 不允许修改分类级别
            category.setLevel(existingCategory.getLevel());
            
            // 设置ID
            category.setId(id);
            
            // 更新分类
            boolean success = this.updateById(category);
            if (success) {
                return Result.success();
            } else {
                return Result.fail("更新分类失败");
            }
        } catch (Exception e) {
            log.error("更新分类失败", e);
            return Result.fail("更新分类失败: " + e.getMessage());
        }
    }

    @Override
    public Result<?> deleteCategory(Long id) {
        try {
            // 检查是否有子分类
            LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(Category::getParentId, id);
            
            if (this.count(queryWrapper) > 0) {
                return Result.fail("该分类下有子分类，无法删除");
            }
            
            // 检查分类下是否有商品，如果有则不允许删除
            LambdaQueryWrapper<Spu> spuQueryWrapper = new LambdaQueryWrapper<>();
            spuQueryWrapper.eq(Spu::getCategoryId, id);
            
            if (spuService.count(spuQueryWrapper) > 0) {
                return Result.fail("该分类下有商品，无法删除");
            }
            
            // 删除分类
            boolean success = this.removeById(id);
            if (success) {
                return Result.success();
            } else {
                return Result.fail("删除分类失败");
            }
        } catch (Exception e) {
            log.error("删除分类失败", e);
            return Result.fail("删除分类失败: " + e.getMessage());
        }
    }

    @Override
    public Result<Category> getCategoryDetail(Long id) {
        try {
            Category category = this.getById(id);
            if (category == null) {
                return Result.fail("分类不存在");
            }
            return Result.success(category);
        } catch (Exception e) {
            log.error("获取分类详情失败", e);
            return Result.fail("获取分类详情失败: " + e.getMessage());
        }
    }
    
    /**
     * 构建分类树形结构
     * @param allCategories 所有分类
     * @return 树形结构的分类列表
     */
    private List<Category> buildCategoryTree(List<Category> allCategories) {
        // 按父ID分组
        Map<Long, List<Category>> categoryMap = allCategories.stream()
                .collect(Collectors.groupingBy(Category::getParentId));
        
        // 获取一级分类
        List<Category> rootCategories = categoryMap.getOrDefault(0L, new ArrayList<>());
        
        // 递归设置子分类
        rootCategories.forEach(root -> {
            List<Category> children = categoryMap.getOrDefault(root.getId(), new ArrayList<>());
            root.setChildren(children);
        });
        
        return rootCategories;
    }
}