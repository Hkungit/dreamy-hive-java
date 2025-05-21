package com.dreamy.hive.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dreamy.hive.common.Result;
import com.dreamy.hive.entity.SkuAttribute;

import java.util.List;

/**
 * SKU属性关联服务接口
 */
public interface SkuAttributeService extends IService<SkuAttribute> {
    
    /**
     * 根据SKU ID获取属性列表
     * @param skuId SKU ID
     * @return 属性列表
     */
    Result<List<SkuAttribute>> getAttributesBySkuId(Long skuId);
    
    /**
     * 批量添加SKU属性
     * @param skuAttributes 属性列表
     * @return 操作结果
     */
    Result<?> batchAddSkuAttributes(List<SkuAttribute> skuAttributes);
    
    /**
     * 删除SKU的所有属性
     * @param skuId SKU ID
     * @return 操作结果
     */
    Result<?> deleteBySkuId(Long skuId);
} 