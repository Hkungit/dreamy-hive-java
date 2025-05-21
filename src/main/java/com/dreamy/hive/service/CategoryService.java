package com.dreamy.hive.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dreamy.hive.common.Result;
import com.dreamy.hive.entity.Category;

import java.util.List;

public interface CategoryService extends IService<Category> {
    /**
     * 获取分类树形结构
     * @return 分类树
     */
    Result<List<Category>> getCategoryTree();
    
    /**
     * 根据父级ID获取子分类列表
     * @param parentId 父级ID
     * @return 子分类列表
     */
    Result<List<Category>> getChildCategories(Long parentId);
    
    /**
     * 分页查询分类列表
     * @param pageNum 页码
     * @param pageSize 每页大小
     * @param name 分类名称（可选）
     * @return 分页结果
     */
    Result<Page<Category>> listCategoriesByPage(int pageNum, int pageSize, String name);
    
    /**
     * 添加分类
     * @param category 分类信息
     * @return 操作结果
     */
    Result<?> addCategory(Category category);
    
    /**
     * 更新分类
     * @param id 分类ID
     * @param category 分类信息
     * @return 操作结果
     */
    Result<?> updateCategory(Long id, Category category);
    
    /**
     * 删除分类
     * @param id 分类ID
     * @return 操作结果
     */
    Result<?> deleteCategory(Long id);
    
    /**
     * 获取分类详情
     * @param id 分类ID
     * @return 分类详情
     */
    Result<Category> getCategoryDetail(Long id);
}