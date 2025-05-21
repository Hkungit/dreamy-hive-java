package com.dreamy.hive.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dreamy.hive.common.Result;
import com.dreamy.hive.entity.Attribute;
import com.dreamy.hive.entity.Category;
import com.dreamy.hive.mapper.AttributeMapper;
import com.dreamy.hive.service.AttributeService;
import com.dreamy.hive.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品属性服务实现类
 */
@Slf4j
@Service
public class AttributeServiceImpl extends ServiceImpl<AttributeMapper, Attribute> implements AttributeService {

    @Autowired
    private CategoryService categoryService;

    @Override
    public Result<List<Attribute>> getAttributesByCategory(Long categoryId) {
        try {
            // 检查分类是否存在
            Category category = categoryService.getById(categoryId);
            if (category == null) {
                return Result.fail("分类不存在");
            }
            
            // 查询该分类下的所有属性
            LambdaQueryWrapper<Attribute> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(Attribute::getCategoryId, categoryId)
                    .orderByAsc(Attribute::getSort);
            
            List<Attribute> attributeList = this.list(queryWrapper);
            return Result.success(attributeList);
        } catch (Exception e) {
            log.error("获取分类属性列表失败", e);
            return Result.fail("获取分类属性列表失败: " + e.getMessage());
        }
    }

    @Override
    public Result<?> addAttribute(Attribute attribute) {
        try {
            // 检查分类是否存在
            Category category = categoryService.getById(attribute.getCategoryId());
            if (category == null) {
                return Result.fail("分类不存在");
            }
            
            // 设置默认值
            if (attribute.getSort() == null) {
                attribute.setSort(0);
            }
            
            if (attribute.getIsFilter() == null) {
                attribute.setIsFilter(0);
            }
            
            // 保存属性
            boolean success = this.save(attribute);
            if (success) {
                return Result.success(attribute);
            } else {
                return Result.fail("添加属性失败");
            }
        } catch (Exception e) {
            log.error("添加属性失败", e);
            return Result.fail("添加属性失败: " + e.getMessage());
        }
    }

    @Override
    public Result<?> updateAttribute(Long id, Attribute attribute) {
        try {
            // 检查属性是否存在
            Attribute existingAttribute = this.getById(id);
            if (existingAttribute == null) {
                return Result.fail("属性不存在");
            }
            
            // 检查分类是否存在
            Category category = categoryService.getById(attribute.getCategoryId());
            if (category == null) {
                return Result.fail("分类不存在");
            }
            
            // 设置ID
            attribute.setId(id);
            
            // 更新属性
            boolean success = this.updateById(attribute);
            if (success) {
                return Result.success();
            } else {
                return Result.fail("更新属性失败");
            }
        } catch (Exception e) {
            log.error("更新属性失败", e);
            return Result.fail("更新属性失败: " + e.getMessage());
        }
    }

    @Override
    public Result<?> deleteAttribute(Long id) {
        try {
            // 检查属性是否存在
            Attribute existingAttribute = this.getById(id);
            if (existingAttribute == null) {
                return Result.fail("属性不存在");
            }
            
            // 删除属性
            boolean success = this.removeById(id);
            if (success) {
                return Result.success();
            } else {
                return Result.fail("删除属性失败");
            }
        } catch (Exception e) {
            log.error("删除属性失败", e);
            return Result.fail("删除属性失败: " + e.getMessage());
        }
    }
} 