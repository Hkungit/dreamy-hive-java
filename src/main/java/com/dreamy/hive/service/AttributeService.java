package com.dreamy.hive.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dreamy.hive.common.Result;
import com.dreamy.hive.entity.Attribute;

import java.util.List;

/**
 * 商品属性服务接口
 */
public interface AttributeService extends IService<Attribute> {
    
    /**
     * 根据分类ID获取属性列表
     * @param categoryId 分类ID
     * @return 属性列表
     */
    Result<List<Attribute>> getAttributesByCategory(Long categoryId);
    
    /**
     * 添加属性
     * @param attribute 属性信息
     * @return 操作结果
     */
    Result<?> addAttribute(Attribute attribute);
    
    /**
     * 更新属性
     * @param id 属性ID
     * @param attribute 属性信息
     * @return 操作结果
     */
    Result<?> updateAttribute(Long id, Attribute attribute);
    
    /**
     * 删除属性
     * @param id 属性ID
     * @return 操作结果
     */
    Result<?> deleteAttribute(Long id);
} 